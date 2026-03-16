package Homeworks.ProblemSolving;

import java.io.*;

public class ArrayPartitionMinimax {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        long[] a = new long[n];
        long lo = 0;
        long hi = 0;

        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
            lo = Math.max(lo, a[i]);
            hi += a[i];
        }

        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            if (canSplit(a, k, mid)) hi = mid;
            else lo = mid + 1;
        }

        System.out.println(lo);
    }

    private static boolean canSplit(long[] a, int k, long limit) {
        int parts = 1;
        long sum = 0;

        for (long x : a) {
            if (sum + x <= limit) {
                sum += x;
            } else {
                parts++;
                sum = x;
            }
        }

        return parts <= k;
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