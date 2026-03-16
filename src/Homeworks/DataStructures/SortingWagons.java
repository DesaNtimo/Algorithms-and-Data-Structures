package Homeworks.DataStructures;

import java.io.*;

public class SortingWagons {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = fs.nextInt();

        int[] st = new int[n];
        int top = 0;
        int need = 1;

        int[] opType = new int[2 * n + 5];
        int[] opCnt  = new int[2 * n + 5];
        int ops = 0;

        for (int v : p) {
            st[top++] = v;
            ops = addOp(opType, opCnt, ops, 1, 1);

            while (top > 0 && st[top - 1] == need) {
                top--;
                need++;
                ops = addOp(opType, opCnt, ops, 2, 1);
            }
        }

        while (top > 0 && st[top - 1] == need) {
            top--;
            need++;
            ops = addOp(opType, opCnt, ops, 2, 1);
        }

        if (need != n + 1) {
            System.out.println(0);
            return;
        }

        StringBuilder out = new StringBuilder();
        out.append(ops).append('\n');
        for (int i = 0; i < ops; i++) {
            out.append(opType[i]).append(' ').append(opCnt[i]).append('\n');
        }
        System.out.print(out);
    }

    private static int addOp(int[] t, int[] c, int ops, int type, int k) {
        if (ops > 0 && t[ops - 1] == type) {
            c[ops - 1] += k;
            return ops;
        }
        t[ops] = type;
        c[ops] = k;
        return ops + 1;
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException { return (int) nextLong(); }

        long nextLong() throws IOException {
            int c;
            do { c = readByte(); } while (c <= ' ');
            long sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}
