package Homeworks.Trees;

import java.io.*;
import java.util.Arrays;

public class TreePrimitives {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] head = new int[n];
        int[] to = new int[n - 1];
        int[] next = new int[n - 1];
        Arrays.fill(head, -1);

        int[] depth = new int[n];
        int edgePtr = 0;

        for (int v = 1; v < n; v++) {
            int p = fs.nextInt();
            depth[v] = depth[p] + 1;

            to[edgePtr] = v;
            next[edgePtr] = head[p];
            head[p] = edgePtr++;
        }

        int height = 0;
        for (int d : depth) height = Math.max(height, d);

        int[] down = new int[n];
        int diameter = 0;

        for (int v = n - 1; v >= 0; v--) {
            int best1 = 0;
            int best2 = 0;

            for (int e = head[v]; e != -1; e = next[e]) {
                int child = to[e];
                int cur = down[child] + 1;

                if (cur > best1) {
                    best2 = best1;
                    best1 = cur;
                } else if (cur > best2) {
                    best2 = cur;
                }
            }

            down[v] = best1;
            diameter = Math.max(diameter, best1 + best2);
        }

        StringBuilder out = new StringBuilder();
        out.append(height).append(' ').append(diameter).append('\n');
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(depth[i]);
        }
        out.append('\n');

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