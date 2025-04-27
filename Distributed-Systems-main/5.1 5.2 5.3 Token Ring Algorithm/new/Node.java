import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Distributed Token Ring Mutual Exclusion Node
 *
 * Usage:
 *   javac Node.java
 *   java Node <id> <listenPort> <nextHost> <nextPort>
 *
 * Example for two machines:
 *   Machine A: java Node 0 5000 B_IP 5001
 *   Machine B: java Node 1 5001 A_IP 5000
 */
public class Node {
    private final int id;
    private final int listenPort;
    private final String nextHost;
    private final int nextPort;

    private ServerSocket serverSocket;
    private Socket prevSocket;
    private Socket nextSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private final AtomicBoolean wantCS = new AtomicBoolean(false);

    public Node(int id, int listenPort, String nextHost, int nextPort) {
        this.id = id;
        this.listenPort = listenPort;
        this.nextHost = nextHost;
        this.nextPort = nextPort;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.err.println("Usage: java Node <id> <listenPort> <nextHost> <nextPort>");
            System.exit(1);
        }
        int id = Integer.parseInt(args[0]);
        int listenPort = Integer.parseInt(args[1]);
        String nextHost = args[2];
        int nextPort = Integer.parseInt(args[3]);

        Node node = new Node(id, listenPort, nextHost, nextPort);
        node.start();
    }

    public void start() throws Exception {
        // 1) Start server to accept connection from predecessor
        serverSocket = new ServerSocket(listenPort);
        System.out.println("Node " + id + " listening on port " + listenPort);

        // Thread to accept prev connection
        Thread acceptThread = new Thread(() -> {
            try {
                prevSocket = serverSocket.accept();
                in = new ObjectInputStream(prevSocket.getInputStream());
                System.out.println("Node " + id + " connected from predecessor");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        acceptThread.start();

        // 2) Connect to next neighbor (retry until available)
        while (true) {
            try {
                nextSocket = new Socket(nextHost, nextPort);
                out = new ObjectOutputStream(nextSocket.getOutputStream());
                System.out.println("Node " + id + " connected to next at " + nextHost + ":" + nextPort);
                break;
            } catch (IOException e) {
                System.out.println("Node " + id + " retrying connection to " + nextHost + ":" + nextPort);
                Thread.sleep(1000);
            }
        }

        // Wait until accept completes
        acceptThread.join();

        // 3) Start console thread to listen for CS requests
        new Thread(this::consoleListener).start();

        // 4) If this is node 0, initialize token
        if (id == 0) {
            System.out.println("Node 0 initializing token and entering main loop");
            out.writeObject(new Token());
            out.flush();
        }

        // 5) Main receive-forward loop
        while (true) {
            Token token = (Token) in.readObject();
            System.out.println("Node " + id + " received token");

            if (wantCS.getAndSet(false)) {
                enterCriticalSection();
            }

            // Forward token
            out.writeObject(token);
            out.flush();
            System.out.println("Node " + id + " forwarded token");
        }
    }

    private void consoleListener() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Node " + id + " > Type 'r' + Enter to request CS: ");
            String line = sc.nextLine().trim();
            if ("r".equalsIgnoreCase(line)) {
                wantCS.set(true);
                System.out.println("Node " + id + " will enter CS when it gets the token");
            }
        }
    }

    private void enterCriticalSection() throws InterruptedException {
        System.out.println("Node " + id + " entering Critical Section");
        // Simulate some work in CS
        Thread.sleep(2000);
        System.out.println("Node " + id + " exiting Critical Section");
    }

    // Inner Token class; unique empty object
    private static class Token implements Serializable {
        private static final long serialVersionUID = 1L;
    }
}
