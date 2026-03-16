package Homeworks.Trees;

import java.io.*;

public class HeapSorter {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        buildHeap(a);

        for (int end = n - 1; end > 0; end--) {
            int t = a[0];
            a[0] = a[end];
            a[end] = t;
            siftDown(a, end, 0);
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        out.append('\n');

        System.out.print(out);
    }

    private static void buildHeap(int[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, a.length, i);
        }
    }

    private static void siftDown(int[] heap, int size, int v) {
        while (true) {
            int left = v * 2 + 1;
            int right = v * 2 + 2;
            int best = v;

            if (left < size && heap[left] > heap[best]) best = left;
            if (right < size && heap[right] > heap[best]) best = right;

            if (best == v) break;

            int t = heap[v];
            heap[v] = heap[best];
            heap[best] = t;
            v = best;
        }
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