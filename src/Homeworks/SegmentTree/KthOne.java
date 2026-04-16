package Homeworks.SegmentTree;

import java.io.*;

public class KthOne {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tree = new int[4 * n];
        for (int i = 0; i < n; i++) {
            if (sc.nextInt() == 1) {
                update(tree, 1, 0, n - 1, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                update(tree, 1, 0, n - 1, sc.nextInt());
            } else {
                sb.append(find(tree, 1, 0, n - 1, sc.nextInt())).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void update(int[] tree, int node, int start, int end, int idx) {
        if (start == end) {
            tree[node] ^= 1;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(tree, 2 * node, start, mid, idx);
            else update(tree, 2 * node + 1, mid + 1, end, idx);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    private static int find(int[] tree, int node, int start, int end, int k) {
        if (start == end) return start;
        int mid = (start + end) / 2;
        if (tree[2 * node] > k) {
            return find(tree, 2 * node, start, mid, k);
        } else {
            return find(tree, 2 * node + 1, mid + 1, end, k - tree[2 * node]);
        }
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
    }
}