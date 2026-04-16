package Homeworks.SegmentTree;

import java.io.*;
import java.util.*;

public class EnemyIsWeak {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sorted[i] = a[i];
        }

        Arrays.sort(sorted);
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }

        int[] comp = new int[n];
        for (int i = 0; i < n; i++) {
            comp[i] = Arrays.binarySearch(sorted, 0, uniqueCount, a[i]) + 1;
        }

        long[] greaterLeft = new long[n];
        int[] fenwickLeft = new int[uniqueCount + 1];
        for (int i = 0; i < n; i++) {
            greaterLeft[i] = i - query(fenwickLeft, comp[i]);
            add(fenwickLeft, comp[i]);
        }

        long[] smallerRight = new long[n];
        int[] fenwickRight = new int[uniqueCount + 1];
        for (int i = n - 1; i >= 0; i--) {
            smallerRight[i] = query(fenwickRight, comp[i] - 1);
            add(fenwickRight, comp[i]);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += greaterLeft[i] * smallerRight[i];
        }

        System.out.println(result);
    }

    private static void add(int[] fenwick, int idx) {
        for (; idx < fenwick.length; idx += idx & -idx) {
            fenwick[idx] += 1;
        }
    }

    private static int query(int[] fenwick, int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum += fenwick[idx];
        }
        return sum;
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        public FastScanner(InputStream in) { this.in = in; }
        private int readByte() throws IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        public int nextInt() throws IOException {
            int c = readByte();
            while (c <= ' ' && c != -1) c = readByte();
            if (c == -1) return -1;
            int sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            int res = 0;
            while (c > ' ') { res = res * 10 + (c - '0'); c = readByte(); }
            return res * sign;
        }
    }
}