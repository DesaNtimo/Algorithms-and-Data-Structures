package Homeworks.DataStructures;

import java.io.*;
import java.util.Arrays;

public class Astrocity {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] q = new int[n + 5];
        int[] pos = new int[100000 + 5];
        Arrays.fill(pos, -1);

        int head = 0, tail = -1;

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int type = fs.nextInt();
            if (type == 1) {
                int id = fs.nextInt();
                q[++tail] = id;
                pos[id] = tail;
            } else if (type == 2) {
                int id = q[head];
                pos[id] = -1;
                head++;
            } else if (type == 3) {
                int id = q[tail];
                pos[id] = -1;
                tail--;
            } else if (type == 4) {
                int id = fs.nextInt();
                out.append(pos[id] - head).append('\n');
            } else { // type == 5
                out.append(q[head]).append('\n');
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
            do { c = readByte(); } while (c <= ' ');
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