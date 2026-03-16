package Homeworks.ProblemSolving;

import java.io.*;

public class TicketOfficesOverlap {
    private static final int DAY = 24 * 60 * 60;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] diff = new int[DAY + 1];

        for (int i = 0; i < n; i++) {
            int h1 = fs.nextInt();
            int m1 = fs.nextInt();
            int s1 = fs.nextInt();
            int h2 = fs.nextInt();
            int m2 = fs.nextInt();
            int s2 = fs.nextInt();

            int open = h1 * 3600 + m1 * 60 + s1;
            int close = h2 * 3600 + m2 * 60 + s2;

            if (open == close) {
                addRange(diff, 0, DAY);
            } else if (open < close) {
                addRange(diff, open, close);
            } else {
                addRange(diff, open, DAY);
                addRange(diff, 0, close);
            }
        }

        int cur = 0;
        long ans = 0;
        for (int sec = 0; sec < DAY; sec++) {
            cur += diff[sec];
            if (cur == n) ans++;
        }

        System.out.println(ans);
    }

    private static void addRange(int[] diff, int l, int r) {
        diff[l]++;
        diff[r]--;
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