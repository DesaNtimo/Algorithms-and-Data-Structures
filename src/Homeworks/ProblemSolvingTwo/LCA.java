package Homeworks.ProblemSolvingTwo;

import java.io.*;

public class LCA {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        if (n == -1) return;

        int[][] up = new int[n][18];
        int[] depth = new int[n];

        for (int i = 1; i < n; i++) {
            int p = sc.nextInt();
            up[i][0] = p;
            depth[i] = depth[p] + 1;
            for (int j = 1; j < 18; j++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (depth[u] < depth[v]) {
                int t = u;
                u = v;
                v = t;
            }

            int diff = depth[u] - depth[v];
            for (int j = 0; j < 18; j++) {
                if (((diff >> j) & 1) == 1) {
                    u = up[u][j];
                }
            }

            if (u != v) {
                for (int j = 17; j >= 0; j--) {
                    if (up[u][j] != up[v][j]) {
                        u = up[u][j];
                        v = up[v][j];
                    }
                }
                u = up[u][0];
            }
            out.println(u);
        }
        out.flush();
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buf = new byte[1024 * 16];
        private int head, tail;

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buf, 0, buf.length);
                if (tail <= 0) return -1;
            }
            return buf[head++];
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }
    }
}