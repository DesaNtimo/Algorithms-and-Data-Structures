package Homeworks.SegmentTree;

import java.io.*;

public class Sage {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] tree = new long[4 * n];
        for (int i = 0; i < n; i++) {
            update(tree, 1, 0, n - 1, i, sc.nextLong());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                update(tree, 1, 0, n - 1, sc.nextInt(), sc.nextLong());
            } else {
                sb.append(query(tree, 1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1)).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void update(long[] tree, int node, int start, int end, int idx, long val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(tree, 2 * node, start, mid, idx, val);
            else update(tree, 2 * node + 1, mid + 1, end, idx, val);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    private static long query(long[] tree, int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return query(tree, 2 * node, start, mid, l, r) + query(tree, 2 * node + 1, mid + 1, end, l, r);
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