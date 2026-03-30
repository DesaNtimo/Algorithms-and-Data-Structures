package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class Alchemy {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        String mStr = sc.next();
        if (mStr == null) return;
        int m = Integer.parseInt(mStr);

        Map<String, Integer> nameToId = new HashMap<>();
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String uName = sc.next();
            sc.next(); // Читаем "->" и игнорируем
            String vName = sc.next();

            int uId = getId(nameToId, graph, uName);
            int vId = getId(nameToId, graph, vName);

            graph.get(uId).add(vId);
        }

        String startName = sc.next();
        String endName = sc.next();

        if (!nameToId.containsKey(startName) || !nameToId.containsKey(endName)) {
            assert startName != null;
            System.out.println(startName.equals(endName) ? 0 : -1);
            return;
        }

        int startId = nameToId.get(startName);
        int endId = nameToId.get(endName);

        // BFS для поиска кратчайшего пути
        int[] dist = new int[nameToId.size()];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();

        dist[startId] = 0;
        queue.add(startId);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == endId) break;

            for (int v : graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        System.out.println(dist[endId]);
    }

    private static int getId(Map<String, Integer> map, List<List<Integer>> graph, String name) {
        if (!map.containsKey(name)) {
            map.put(name, map.size());
            graph.add(new ArrayList<>());
        }
        return map.get(name);
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }
    }
}