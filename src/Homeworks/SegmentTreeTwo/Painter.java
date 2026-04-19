package Homeworks.SegmentTreeTwo;

import java.io.*;

public class Painter {
    static final int MAX_CORD = 1100000;
    static final int OFFSET = 500000;
    static int[] count, len, set;
    static boolean[] lBlack, rBlack;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n <= 0) return;

        count = new int[4 * MAX_CORD];
        len = new int[4 * MAX_CORD];
        set = new int[4 * MAX_CORD];
        lBlack = new boolean[4 * MAX_CORD];
        rBlack = new boolean[4 * MAX_CORD];
        for (int i = 0; i < 4 * MAX_CORD; i++) set[i] = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = sc.nextChar();
            int x = sc.nextInt() + OFFSET;
            int l = sc.nextInt();
            int color = (c == 'B') ? 1 : 0;
            if (l > 0) {
                update(1, 0, MAX_CORD - 1, x, x + l - 1, color);
            }
            sb.append(count[1]).append(" ").append(len[1]).append("\n");
        }
        System.out.print(sb);
    }

    static void apply(int node, int start, int end, int color) {
        set[node] = color;
        if (color == 1) {
            len[node] = end - start + 1;
            count[node] = 1;
            lBlack[node] = rBlack[node] = true;
        } else {
            len[node] = 0;
            count[node] = 0;
            lBlack[node] = rBlack[node] = false;
        }
    }

    static void push(int node, int start, int end) {
        if (set[node] != -1) {
            int mid = (start + end) / 2;
            apply(2 * node, start, mid, set[node]);
            apply(2 * node + 1, mid + 1, end, set[node]);
            set[node] = -1;
        }
    }

    static void merge(int node) {
        int left = 2 * node, right = 2 * node + 1;
        len[node] = len[left] + len[right];
        count[node] = count[left] + count[right];
        if (rBlack[left] && lBlack[right]) count[node]--;
        lBlack[node] = lBlack[left];
        rBlack[node] = rBlack[right];
    }

    static void update(int node, int start, int end, int l, int r, int color) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            apply(node, start, end, color);
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r, color);
        update(2 * node + 1, mid + 1, end, l, r, color);
        merge(node);
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

        public char nextChar() throws IOException {
            int c = readByte();
            while (c <= ' ' && c != -1) c = readByte();
            return (char) c;
        }
    }
}