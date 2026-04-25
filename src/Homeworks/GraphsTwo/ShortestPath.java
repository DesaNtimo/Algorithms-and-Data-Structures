package Homeworks.GraphsTwo;

import java.io.*;
import java.util.*;

public class ShortestPath {
    static class Node implements Comparable<Node> {
        int v;
        long d;

        Node(int v, long d) {
            this.v = v;
            this.d = d;
        }

        public int compareTo(Node o) {
            return Long.compare(this.d, o.d);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n == -1) return;
        int m = sc.nextInt();

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] to = new int[2 * m], weight = new int[2 * m], nxt = new int[2 * m];
        int edgeCount = 0;

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            to[edgeCount] = v;
            weight[edgeCount] = w;
            nxt[edgeCount] = head[u];
            head[u] = edgeCount++;
            to[edgeCount] = u;
            weight[edgeCount] = w;
            nxt[edgeCount] = head[v];
            head[v] = edgeCount++;
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.v;
            if (curr.d > dist[u]) continue;

            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (dist[u] + weight[e] < dist[v]) {
                    dist[v] = dist[u] + weight[e];
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 1; i <= n; i++) out.print(dist[i] + (i == n ? "" : " "));
        out.println();
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
    }
}