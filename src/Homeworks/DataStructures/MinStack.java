package Homeworks.DataStructures;

import java.io.*;

public class MinStack {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        long[] val = new long[n];
        long[] mn = new long[n];
        int top = 0;

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int type = fs.nextInt();
            if (type == 1) {
                long x = fs.nextLong();
                val[top] = x;
                mn[top] = (top == 0) ? x : Math.min(mn[top - 1], x);
                top++;
            } else if (type == 2) {
                top--;
            } else {
                out.append(mn[top - 1]).append('\n');
            }
        }

        System.out.print(out);
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException { return (int) nextLong(); }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}
