package Homeworks.SegmentTree;

import java.io.*;
import java.util.*;

public class NumberOfLIS {
    static final int MOD = 1000000007;

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

        int[] lenTree = new int[4 * uniqueCount];
        int[] cntTree = new int[4 * uniqueCount];

        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(sorted, 0, uniqueCount, a[i]);
            int[] q = query(lenTree, cntTree, 1, 0, uniqueCount - 1, 0, idx - 1);
            int newLen = q[0] + 1;
            int newCnt = Math.max(q[1], 1);
            update(lenTree, cntTree, 1, 0, uniqueCount - 1, idx, newLen, newCnt);
        }

        System.out.println(cntTree[1]);
    }

    private static void update(int[] lenTree, int[] cntTree, int node, int start, int end, int idx, int len, int cnt) {
        if (start == end) {
            if (len > lenTree[node]) {
                lenTree[node] = len;
                cntTree[node] = cnt;
            } else if (len == lenTree[node]) {
                cntTree[node] = (cntTree[node] + cnt) % MOD;
            }
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(lenTree, cntTree, 2 * node, start, mid, idx, len, cnt);
            else update(lenTree, cntTree, 2 * node + 1, mid + 1, end, idx, len, cnt);

            if (lenTree[2 * node] > lenTree[2 * node + 1]) {
                lenTree[node] = lenTree[2 * node];
                cntTree[node] = cntTree[2 * node];
            } else if (lenTree[2 * node] < lenTree[2 * node + 1]) {
                lenTree[node] = lenTree[2 * node + 1];
                cntTree[node] = cntTree[2 * node + 1];
            } else {
                lenTree[node] = lenTree[2 * node];
                cntTree[node] = (cntTree[2 * node] + cntTree[2 * node + 1]) % MOD;
            }
        }
    }

    private static int[] query(int[] lenTree, int[] cntTree, int node, int start, int end, int l, int r) {
        if (r < start || end < l) return new int[]{0, 0};
        if (l <= start && end <= r) return new int[]{lenTree[node], cntTree[node]};
        int mid = (start + end) / 2;
        int[] p1 = query(lenTree, cntTree, 2 * node, start, mid, l, r);
        int[] p2 = query(lenTree, cntTree, 2 * node + 1, mid + 1, end, l, r);
        if (p1[0] > p2[0]) return p1;
        if (p1[0] < p2[0]) return p2;
        return new int[]{p1[0], (p1[1] + p2[1]) % MOD};
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