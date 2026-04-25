package Homeworks.GraphsTwo;

import java.io.*;

public class DisjointSetUnion {
    static int[] parent, min, max, size;

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
            min[a] = Math.min(min[a], min[b]);
            max[a] = Math.max(max[a], max[b]);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n == -1) return;
        int m = sc.nextInt();

        parent = new int[n + 1];
        min = new int[n + 1];
        max = new int[n + 1];
        size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = min[i] = max[i] = i;
            size[i] = 1;
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < m; i++) {
            String op = sc.nextString();
            if (op.charAt(0) == 'u') {
                union(sc.nextInt(), sc.nextInt());
            } else {
                int root = find(sc.nextInt());
                out.println(min[root] + " " + max[root] + " " + size[root]);
            }
        }
        out.flush();
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

        String nextString() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }
}