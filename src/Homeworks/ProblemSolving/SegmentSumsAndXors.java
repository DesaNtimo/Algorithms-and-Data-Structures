package Homeworks.ProblemSolving;

import java.io.*;

public class SegmentSumsAndXors {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long[] prefSum = new long[n + 1];
        int[] prefXor = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int x = fs.nextInt();
            prefSum[i] = prefSum[i - 1] + x;
            prefXor[i] = prefXor[i - 1] ^ x;
        }

        int m = fs.nextInt();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int type = fs.nextInt();
            int l = fs.nextInt();
            int r = fs.nextInt();

            if (type == 1) {
                out.append(prefSum[r] - prefSum[l - 1]).append('\n');
            } else {
                out.append(prefXor[r] ^ prefXor[l - 1]).append('\n');
            }
        }

        System.out.print(out);
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}