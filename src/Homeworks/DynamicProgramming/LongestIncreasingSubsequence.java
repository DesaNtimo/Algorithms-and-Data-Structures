package Homeworks.DynamicProgramming;

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n <= 0) return;

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] tailIndices = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int len = 0;
        for (int i = 0; i < n; i++) {
            int left = 0, right = len - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (a[tailIndices[mid]] < a[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            int pos = left;
            tailIndices[pos] = i;
            if (pos > 0) {
                parent[i] = tailIndices[pos - 1];
            }
            if (pos == len) {
                len++;
            }
        }

        int curr = tailIndices[len - 1];
        int[] lis = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            lis[i] = a[curr];
            curr = parent[curr];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");
        for (int x : lis) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
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
            while (c <= ' ') c = readByte();
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return (int) (res * sign);
        }
    }
}