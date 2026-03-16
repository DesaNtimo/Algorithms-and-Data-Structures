package Homeworks.DataStructures;

import java.io.*;

public class MinimumSegment {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int k = fs.nextInt();

        int[] dqIdx = new int[n];
        int[] dqVal = new int[n];
        int head = 0, tail = -1;

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = fs.nextInt();

            while (head <= tail && dqVal[tail] >= x) tail--;
            dqIdx[++tail] = i;
            dqVal[tail] = x;

            int leftBound = i - k + 1;
            while (head <= tail && dqIdx[head] < leftBound) head++;

            if (i >= k - 1) {
                out.append(dqVal[head]).append('\n');
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
