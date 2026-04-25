package Homeworks.GraphsTwo;

import java.io.*;

public class PrintedCircuitBoard {
    static int[] parent;

    static int find(int v) {
        return v == parent[v] ? v : (parent[v] = find(parent[v]));
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n == -1) return;
        int m = sc.nextInt();

        int total = n * m;
        parent = new int[total];
        for (int i = 0; i < total; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = sc.nextInt();
                int u = i * m + j;
                if ((val == 1 || val == 3) && i + 1 < n) union(u, (i + 1) * m + j);
                if ((val == 2 || val == 3) && j + 1 < m) union(u, i * m + (j + 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        long cost = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (union(i * m + j, (i + 1) * m + j)) {
                    count++;
                    cost += 1;
                    sb.append((i + 1)).append(" ").append((j + 1)).append(" 1\n");
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (union(i * m + j, i * m + (j + 1))) {
                    count++;
                    cost += 2;
                    sb.append((i + 1)).append(" ").append((j + 1)).append(" 2\n");
                }
            }
        }

        System.out.println(count + " " + cost);
        System.out.print(sb);
    }

    static class FastScanner {
        InputStream in;
        byte[] b = new byte[1 << 16];
        int h = 0, t = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (h >= t) {
                h = 0;
                t = in.read(b, 0, b.length);
                if (t <= 0) return -1;
            }
            return b[h++];
        }

        int nextInt() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return -1;
            int r = 0;
            while (c > ' ') {
                r = r * 10 + (c - '0');
                c = read();
            }
            return r;
        }
    }
}