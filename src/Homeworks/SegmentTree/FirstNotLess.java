package Homeworks.SegmentTree;

import java.io.*;

public class FirstNotLess {
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
                long x = sc.nextLong();
                int l = sc.nextInt();
                sb.append(query(tree, 1, 0, n - 1, l, x)).append("\n");
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
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }
    }

    private static int query(long[] tree, int node, int start, int end, int l, long x) {
        if (tree[node] < x || end < l) return -1;
        if (start == end) return start;
        int mid = (start + end) / 2;
        int res = query(tree, 2 * node, start, mid, l, x);
        if (res == -1) {
            res = query(tree, 2 * node + 1, mid + 1, end, l, x);
        }
        return res;
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