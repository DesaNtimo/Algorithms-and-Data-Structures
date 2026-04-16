package Homeworks.SegmentTree;

import java.io.*;

public class SegmentMinimums {
    static long[] minTree;
    static int[] cntTree;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        minTree = new long[4 * n];
        cntTree = new int[4 * n];
        for (int i = 0; i < n; i++) {
            update(1, 0, n - 1, i, sc.nextLong());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                update(1, 0, n - 1, sc.nextInt(), sc.nextLong());
            } else {
                long[] res = query(1, 0, n - 1, sc.nextInt(), sc.nextInt() - 1);
                sb.append(res[0]).append(" ").append(res[1]).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void update(int node, int start, int end, int idx, long val) {
        if (start == end) {
            minTree[node] = val;
            cntTree[node] = 1;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(2 * node, start, mid, idx, val);
            else update(2 * node + 1, mid + 1, end, idx, val);

            if (minTree[2 * node] < minTree[2 * node + 1]) {
                minTree[node] = minTree[2 * node];
                cntTree[node] = cntTree[2 * node];
            } else if (minTree[2 * node] > minTree[2 * node + 1]) {
                minTree[node] = minTree[2 * node + 1];
                cntTree[node] = cntTree[2 * node + 1];
            } else {
                minTree[node] = minTree[2 * node];
                cntTree[node] = cntTree[2 * node] + cntTree[2 * node + 1];
            }
        }
    }

    private static long[] query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return new long[]{Long.MAX_VALUE, 0};
        if (l <= start && end <= r) return new long[]{minTree[node], cntTree[node]};
        int mid = (start + end) / 2;
        long[] p1 = query(2 * node, start, mid, l, r);
        long[] p2 = query(2 * node + 1, mid + 1, end, l, r);
        if (p1[0] < p2[0]) return p1;
        if (p1[0] > p2[0]) return p2;
        return new long[]{p1[0], p1[1] + p2[1]};
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