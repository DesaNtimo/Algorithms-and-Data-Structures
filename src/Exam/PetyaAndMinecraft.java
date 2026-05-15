package Exam;

import java.io.*;

public class PetyaAndMinecraft {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        if (n == -1) return;

        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        a[n] = 0;

        int[] stack = new int[n + 1];
        int top = -1;
        long maxArea = 0;

        for (int i = 0; i <= n; i++) {
            while (top >= 0 && a[stack[top]] > a[i]) {
                long h = a[stack[top--]];
                long w = (top == -1) ? i : (i - stack[top] - 1);
                if (h * w > maxArea) {
                    maxArea = h * w;
                }
            }
            stack[++top] = i;
        }

        System.out.println(maxArea);
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