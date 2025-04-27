import mpi.*;

public class ArrMul {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int root = 0;

        // 1 element per process
        int[] sendBuf = null;
        int[] recvBuf = new int[1];

        if (rank == root) {
            // create an array whose length == number of processes
            sendBuf = new int[size];
            System.out.println("Root: initializing array of length " + size);
            for (int i = 0; i < size; i++) {
                sendBuf[i] = i + 1;       // just example values 1..size
                System.out.println("  sendBuf[" + i + "] = " + sendBuf[i]);
            }
        }

        // scatter one element to each process
        MPI.COMM_WORLD.Scatter(
                sendBuf, 0, 1, MPI.INT,
                recvBuf, 0, 1, MPI.INT,
                root
        );

        // each process just “multiplies” its single element (trivial):
        // if you had more than one element per rank, you’d loop here
        int intermediateProduct = recvBuf[0];
        System.out.println("Process " + rank + " intermediate product: " + intermediateProduct);

        // if you _do_ want to collect them and multiply again at the root:
        int[] gatherBuf = null;
        if (rank == root) {
            gatherBuf = new int[size];
        }
        MPI.COMM_WORLD.Gather(
                new int[]{intermediateProduct}, 0, 1, MPI.INT,
                gatherBuf, 0, 1, MPI.INT,
                root
        );

        if (rank == root) {
            int finalProduct = 1;
            for (int i = 0; i < size; i++) {
                finalProduct *= gatherBuf[i];
            }
            System.out.println("Root final product of all intermediates: " + finalProduct);
        }

        MPI.Finalize();
    }
}
