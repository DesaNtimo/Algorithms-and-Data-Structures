package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class ConnectedComponents {
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
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, graph, visited, comp);
                Collections.sort(comp);
                components.add(comp);
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(components.size()).append("\n");
        for (List<Integer> comp : components) {
            out.append(comp.size()).append("\n");
            for (int v : comp) {
                out.append(v).append(" ");
            }
            out.append("\n");
        }
        System.out.print(out);
    }

    private static void dfs(int u, List<List<Integer>> graph, boolean[] visited, List<Integer> comp) {
        visited[u] = true;
        comp.add(u);
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                dfs(v, graph, visited, comp);
            }
        }
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