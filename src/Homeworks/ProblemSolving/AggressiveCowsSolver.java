package Homeworks.ProblemSolving;

import java.io.*;

public class AggressiveCowsSolver {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        long lo = 0;
        long hi = a[n - 1] - a[0] + 1; // [lo, hi)

        while (hi - lo > 1) {
            long mid = (lo + hi) >>> 1;
            if (canPlace(a, k, mid)) lo = mid;
            else hi = mid;
        }

        System.out.println(lo);
    }

    private static boolean canPlace(long[] a, int k, long dist) {
        int placed = 1;
        long last = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] - last >= dist) {
                placed++;
                last = a[i];
                if (placed >= k) return true;
            }
        }

        return placed >= k;
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