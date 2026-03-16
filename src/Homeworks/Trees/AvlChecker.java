package Homeworks.Trees;

import java.io.*;

public class AvlChecker {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int root = fs.nextInt();

        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            left[i] = fs.nextInt();
            right[i] = fs.nextInt();
        }

        if (!isBST(n, root, left, right)) {
            System.out.println(0);
            return;
        }

        if (!isBalanced(n, root, left, right)) {
            System.out.println(0);
            return;
        }

        System.out.println(1);
    }

    private static boolean isBST(int n, int root, int[] left, int[] right) {
        int[] stackNode = new int[n];
        long[] stackLow = new long[n];
        long[] stackHigh = new long[n];
        int top = 0;

        stackNode[top] = root;
        stackLow[top] = -1L;
        stackHigh[top] = n;
        top++;

        while (top > 0) {
            top--;
            int v = stackNode[top];
            long low = stackLow[top];
            long high = stackHigh[top];

            if (!(low < v && v < high)) return false;

            if (left[v] != -1) {
                stackNode[top] = left[v];
                stackLow[top] = low;
                stackHigh[top] = v;
                top++;
            }

            if (right[v] != -1) {
                stackNode[top] = right[v];
                stackLow[top] = v;
                stackHigh[top] = high;
                top++;
            }
        }

        return true;
    }

    private static boolean isBalanced(int n, int root, int[] left, int[] right) {
        int[] stack = new int[n];
        int[] order = new int[n];
        int sz = 0;
        int ord = 0;

        stack[sz++] = root;
        while (sz > 0) {
            int v = stack[--sz];
            order[ord++] = v;

            if (left[v] != -1) stack[sz++] = left[v];
            if (right[v] != -1) stack[sz++] = right[v];
        }

        int[] height = new int[n];

        for (int i = ord - 1; i >= 0; i--) {
            int v = order[i];

            int hl = (left[v] == -1) ? -1 : height[left[v]];
            int hr = (right[v] == -1) ? -1 : height[right[v]];

            if (Math.abs(hl - hr) > 1) return false;

            height[v] = Math.max(hl, hr) + 1;
        }

        return true;
    }

    private static final class FastScanner {
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

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}