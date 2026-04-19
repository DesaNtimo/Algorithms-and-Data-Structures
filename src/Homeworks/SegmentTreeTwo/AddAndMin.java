package Homeworks.SegmentTreeTwo;

import java.io.*;

public class AddAndMin {
    static long[] tree, lazy;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        tree = new long[4 * n];
        lazy = new long[4 * n];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                update(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1, sc.nextLong());
            } else {
                sb.append(query(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void push(int node) {
        if (lazy[node] != 0) {
            lazy[2 * node] += lazy[node];
            tree[2 * node] += lazy[node];
            lazy[2 * node + 1] += lazy[node];
            tree[2 * node + 1] += lazy[node];
            lazy[node] = 0;
        }
    }

    static void update(int node, int start, int end, int l, int r, long v) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            lazy[node] += v;
            tree[node] += v;
            return;
        }
        push(node);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r, v);
        update(2 * node + 1, mid + 1, end, l, r, v);
        tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
    }

    static long query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return Long.MAX_VALUE;
        if (l <= start && end <= r) return tree[node];
        push(node);
        int mid = (start + end) / 2;
        return Math.min(query(2 * node, start, mid, l, r), query(2 * node + 1, mid + 1, end, l, r));
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

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
            while (c <= ' ' && c != -1) c = readByte();
            if (c == -1) return -1;
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res * sign;
        }

        public long nextLong() throws IOException {
            int c = readByte();
            while (c <= ' ' && c != -1) c = readByte();
            if (c == -1) return -1;
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            long res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res * sign;
        }
    }
}