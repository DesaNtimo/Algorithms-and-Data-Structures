package Homeworks.Trees;

import java.io.*;

public class LowestCommonAncestorSolver {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] parent = new int[n];
        int[] depth = new int[n];
        parent[0] = 0;
        depth[0] = 0;

        for (int v = 1; v < n; v++) {
            parent[v] = fs.nextInt();
            depth[v] = depth[parent[v]] + 1;
        }

        int log = 1;
        while ((1 << log) <= n) log++;

        int[][] up = new int[log][n];
        System.arraycopy(parent, 0, up[0], 0, n);

        for (int j = 1; j < log; j++) {
            for (int v = 0; v < n; v++) {
                up[j][v] = up[j - 1][up[j - 1][v]];
            }
        }

        int m = fs.nextInt();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            out.append(lca(u, v, depth, up)).append('\n');
        }

        System.out.print(out);
    }

    private static int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];
        for (int j = 0; j < up.length; j++) {
            if (((diff >> j) & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
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