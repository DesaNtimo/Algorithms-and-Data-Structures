package Homeworks.ProblemSolving;

import java.io.*;

public class MatrixPrefixSums {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        long[][] pref = new long[n + 1][m + 1];

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                long val = fs.nextLong();
                pref[y][x] = val + pref[y - 1][x] + pref[y][x - 1] - pref[y - 1][x - 1];
            }
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < k; i++) {
            int y1 = fs.nextInt();
            int x1 = fs.nextInt();
            int y2 = fs.nextInt();
            int x2 = fs.nextInt();

            long ans = pref[y2][x2]
                    - pref[y1 - 1][x2]
                    - pref[y2][x1 - 1]
                    + pref[y1 - 1][x1 - 1];

            out.append(ans).append('\n');
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