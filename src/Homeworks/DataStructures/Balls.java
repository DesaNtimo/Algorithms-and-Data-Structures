package Homeworks.DataStructures;

import java.io.*;

public class Balls {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] gColor = new int[n];
        int[] gCnt = new int[n];
        int g = 0;

        for (int i = 0; i < n; i++) {
            int c = fs.nextInt();
            if (g > 0 && gColor[g - 1] == c) {
                gCnt[g - 1]++;
            } else {
                gColor[g] = c;
                gCnt[g] = 1;
                g++;
            }
        }

        int[] sColor = new int[g];
        int[] sCnt = new int[g];
        int top = 0;
        long destroyed = 0;

        for (int i = 0; i < g; i++) {
            int c = gColor[i];
            int k = gCnt[i];

            if (top > 0 && sColor[top - 1] == c) {
                sCnt[top - 1] += k;
            } else {
                sColor[top] = c;
                sCnt[top] = k;
                top++;
            }

            boolean changed = true;
            while (changed) {
                changed = false;

                if (top > 0 && sCnt[top - 1] >= 3) {
                    destroyed += sCnt[top - 1];
                    top--;
                    changed = true;
                }

                if (top >= 2 && sColor[top - 1] == sColor[top - 2]) {
                    sCnt[top - 2] += sCnt[top - 1];
                    top--;
                    changed = true;
                }
            }
        }

        System.out.println(destroyed);
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
