package Homeworks.SegmentTreeTwo;

import java.io.*;

public class AssignAddSum {
    static long[] tree, addTag, setTag;
    static final long NO_SET = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        tree = new long[4 * n];
        addTag = new long[4 * n];
        setTag = new long[4 * n];
        for (int i = 0; i < 4 * n; i++) setTag[i] = NO_SET;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                set(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1, sc.nextLong());
            } else if (type == 2) {
                add(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1, sc.nextLong());
            } else {
                sb.append(query(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static void push(int node, int start, int end) {
        int mid = (start + end) / 2;
        int left = 2 * node, right = 2 * node + 1;
        if (setTag[node] != NO_SET) {
            applySet(left, start, mid, setTag[node]);
            applySet(right, mid + 1, end, setTag[node]);
            setTag[node] = NO_SET;
        }
        if (addTag[node] != 0) {
            applyAdd(left, start, mid, addTag[node]);
            applyAdd(right, mid + 1, end, addTag[node]);
            addTag[node] = 0;
        }
    }

    static void applySet(int node, int start, int end, long v) {
        setTag[node] = v;
        addTag[node] = 0;
        tree[node] = v * (end - start + 1);
    }

    static void applyAdd(int node, int start, int end, long v) {
        if (setTag[node] != NO_SET) setTag[node] += v;
        else addTag[node] += v;
        tree[node] += v * (end - start + 1);
    }

    static void set(int node, int start, int end, int l, int r, long v) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            applySet(node, start, end, v);
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        set(2 * node, start, mid, l, r, v);
        set(2 * node + 1, mid + 1, end, l, r, v);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static void add(int node, int start, int end, int l, int r, long v) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            applyAdd(node, start, end, v);
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        add(2 * node, start, mid, l, r, v);
        add(2 * node + 1, mid + 1, end, l, r, v);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static long query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        push(node, start, end);
        int mid = (start + end) / 2;
        return query(2 * node, start, mid, l, r) + query(2 * node + 1, mid + 1, end, l, r);
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