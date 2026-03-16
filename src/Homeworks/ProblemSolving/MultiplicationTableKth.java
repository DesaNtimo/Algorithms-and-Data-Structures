package Homeworks.ProblemSolving;

import java.io.*;

public class MultiplicationTableKth {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long n = fs.nextLong();
        long k = fs.nextLong();

        long lo = 0;
        long hi = n * n;

        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            if (countLE(n, mid) >= k) hi = mid;
            else lo = mid + 1;
        }

        System.out.println(lo);
    }

    private static long countLE(long n, long x) {
        long cnt = 0;
        for (long i = 1; i <= n; i++) {
            cnt += Math.min(n, x / i);
        }
        return cnt;
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