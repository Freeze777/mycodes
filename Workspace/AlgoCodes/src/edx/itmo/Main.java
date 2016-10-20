package edx.itmo;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("aplusb.in"));
        } else {
            return new Scanner(System.in);
        }
    }
    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("aplusb.out");
        } else {
            return new PrintWriter(System.out);
        }
    }

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            out.println(in.nextInt() + in.nextInt());
        }
    }
}