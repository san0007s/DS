package ReverseStringFile;

import modelReverse.ReversePOA;  // Import the POA class generated from IDL

public class ReverseImpl extends ReversePOA {
    ReverseImpl() {
        super();
        System.out.println("super object is printed");
    }

    // Implementing the reverse method
    public String reverseString(String s) {
        StringBuffer s1 = new StringBuffer(s);  // Create StringBuffer
        s1.reverse();                            // Reverse the string
        return "Server send: " + s1.toString();  // Return the reversed string
    }
}
