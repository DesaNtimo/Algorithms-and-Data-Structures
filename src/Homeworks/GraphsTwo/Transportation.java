package Homeworks.GraphsTwo;

import java.io.*;
import java.util.*;

public class Transportation {
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

    static int n, m;
    static int[] head, to, weight, limit, nxt;

    static boolean canReach(long requiredWeight) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.v;
            if (u == n && dist[u] <= 1440) return true;
            if (curr.d > dist[u]) continue;

            for (int e = head[u]; e != -1; e = nxt[e]) {
                if (limit[e] < requiredWeight) continue;
                int v = to[e];
                if (dist[u] + weight[e] < dist[v]) {
                    dist[v] = dist[u] + weight[e];
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist[n] <= 1440;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        n = sc.nextInt();
        if (n == -1) return;
        m = sc.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * m];
        weight = new int[2 * m];
        limit = new int[2 * m];
        nxt = new int[2 * m];
        int edgeCount = 0;

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt(), l = sc.nextInt();
            to[edgeCount] = v;
            weight[edgeCount] = w;
            limit[edgeCount] = l;
            nxt[edgeCount] = head[u];
            head[u] = edgeCount++;
            to[edgeCount] = u;
            weight[edgeCount] = w;
            limit[edgeCount] = l;
            nxt[edgeCount] = head[v];
            head[v] = edgeCount++;
        }

        long low = 0, high = 10000000, ans = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (canReach(3000000L + mid * 100L)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(ans);
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