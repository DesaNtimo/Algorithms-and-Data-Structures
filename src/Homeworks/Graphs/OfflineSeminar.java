package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class OfflineSeminar {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] dist = new long[n + 1][n + 1];
        long INF = Long.MAX_VALUE / 3;

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // Алгоритм Флойда-Уоршелла
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int bestCity = -1;
        long minOfMaxes = Long.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            long maxDistFromI = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] > maxDistFromI) {
                    maxDistFromI = dist[i][j];
                }
            }
            if (maxDistFromI < minOfMaxes) {
                minOfMaxes = maxDistFromI;
                bestCity = i;
            }
        }

        System.out.println(bestCity);
    }

    private static class FastScanner {
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

        public int nextInt() throws IOException {
            int c = readByte();
            while (c <= ' ') c = readByte();
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res;
        }

        public long nextLong() throws IOException {
            int c = readByte();
            while (c <= ' ') c = readByte();
            long res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res;
        }
    }
}