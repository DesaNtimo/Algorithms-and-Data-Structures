package Homeworks.DataStructures;

import java.io.*;

public class GoodDays {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        long[] a = new long[n];
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
            pref[i + 1] = pref[i] + a[i];
        }

        int[] left = new int[n];
        int[] right = new int[n];
        int[] st = new int[n];
        int top = 0;

        for (int i = 0; i < n; i++) {
            while (top > 0 && a[st[top - 1]] >= a[i]) top--;
            left[i] = (top == 0) ? -1 : st[top - 1];
            st[top++] = i;
        }

        top = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && a[st[top - 1]] >= a[i]) top--;
            right[i] = (top == 0) ? n : st[top - 1];
            st[top++] = i;
        }

        long best = 0;
        for (int i = 0; i < n; i++) {
            int L = left[i] + 1;
            int R = right[i] - 1;
            long sum = pref[R + 1] - pref[L];
            long prod = sum * a[i];
            if (prod > best) best = prod;
        }

        System.out.println(best);
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
