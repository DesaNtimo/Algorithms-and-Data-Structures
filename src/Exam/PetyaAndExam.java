package Exam;

import java.io.*;

public class PetyaAndExam {
    private static int[] tree;
    private static int[] lazy;

    private static void build(int node, int l, int r, int[] a) {
        if (l == r) {
            int val = a[l];
            for (int b = 0; b < 20; b++) {
                if (((val >> b) & 1) == 1) {
                    tree[node * 20 + b] = 1;
                }
            }
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid, a);
        build(2 * node + 1, mid + 1, r, a);
        for (int b = 0; b < 20; b++) {
            tree[node * 20 + b] = tree[(2 * node) * 20 + b] + tree[(2 * node + 1) * 20 + b];
        }
    }

    private static void apply(int node, int l, int r, int val) {
        lazy[node] ^= val;
        for (int b = 0; b < 20; b++) {
            if (((val >> b) & 1) == 1) {
                tree[node * 20 + b] = (r - l + 1) - tree[node * 20 + b];
            }
        }
    }

    private static void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private static void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        if (ql <= mid) update(2 * node, l, mid, ql, qr, val);
        if (qr > mid) update(2 * node + 1, mid + 1, r, ql, qr, val);
        for (int b = 0; b < 20; b++) {
            tree[node * 20 + b] = tree[(2 * node) * 20 + b] + tree[(2 * node + 1) * 20 + b];
        }
    }

    private static long query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            long sum = 0;
            for (int b = 0; b < 20; b++) {
                sum += (long) tree[node * 20 + b] << b;
            }
            return sum;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        long res = 0;
        if (ql <= mid) res += query(2 * node, l, mid, ql, qr);
        if (qr > mid) res += query(2 * node + 1, mid + 1, r, ql, qr);
        return res;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        if (n == -1) return;

        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        tree = new int[(4 * n + 1) * 20];
        lazy = new int[4 * n + 1];

        build(1, 1, n, a);

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (type == 1) {
                out.println(query(1, 1, n, l, r));
            } else {
                update(1, 1, n, l, r, sc.nextInt());
            }
        }
        out.flush();
    }

    private static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buf = new byte[1 << 16];
        private int h = 0, t = 0;

        private int read() throws IOException {
            if (h >= t) {
                h = 0;
                t = in.read(buf, 0, buf.length);
                if (t <= 0) return -1;
            }
            return buf[h++];
        }

        public int nextInt() throws IOException {
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