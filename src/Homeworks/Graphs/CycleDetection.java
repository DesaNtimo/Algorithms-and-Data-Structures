package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class CycleDetection {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v); // Граф ориентированный, ребро только в одну сторону
        }

        // Массив цветов: 0 - белый, 1 - серый, 2 - черный
        int[] color = new int[n + 1];
        boolean hasCycle = false;

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (dfs(i, graph, color)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        System.out.println(hasCycle ? 1 : 0);
    }

    private static boolean dfs(int u, List<List<Integer>> graph, int[] color) {
        color[u] = 1; // Красим в серый (зашли в вершину)

        for (int v : graph.get(u)) {
            if (color[v] == 1) {
                return true; // Нашли ребро в серую вершину — это цикл!
            }
            if (color[v] == 0) {
                if (dfs(v, graph, color)) {
                    return true;
                }
            }
        }

        color[u] = 2; // Красим в черный (полностью обошли все пути из нее)
        return false;
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
    }
}