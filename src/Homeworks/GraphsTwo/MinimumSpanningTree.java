package Homeworks.GraphsTwo;

import java.io.*;
import java.util.*;

public class MinimumSpanningTree {
    static int[] parent;

    static int find(int v) {
        return v == parent[v] ? v : (parent[v] = find(parent[v]));
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n == -1) return;
        int m = sc.nextInt();

        long[] edges = new long[m];
        for (int i = 0; i < m; i++) {
            long u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            // Упаковка: 28 бит вес | 18 бит u | 18 бит v (N,M <= 200,000; 2^18 = 262144)
            edges[i] = (w << 36) | (u << 18) | v;
        }

        Arrays.sort(edges);

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        long totalWeight = 0;
        int edgesAdded = 0;

        for (int i = 0; i < m; i++) {
            long edge = edges[i];
            int w = (int) (edge >>> 36);
            int u = (int) ((edge >>> 18) & 0x3FFFF);
            int v = (int) (edge & 0x3FFFF);

            int pu = find(u), pv = find(v);
            if (pu != pv) {
                parent[pv] = pu;
                totalWeight += w;
                if (++edgesAdded == n - 1) break;
            }
        }
        System.out.println(totalWeight);
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