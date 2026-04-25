package Homeworks.ProblemSolvingTwo;

import java.io.*;

public class TimberCutting {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int l = sc.nextInt();
        if (l == -1) return;
        int n = sc.nextInt();

        int[] a = new int[n + 2];
        a[0] = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        a[n + 1] = l;

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len <= n + 1; len++) {
            for (int i = 0; i <= n + 1 - len; i++) {
                int j = i + len;
                int min = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j];
                    if (cost < min) {
                        min = cost;
                    }
                }
                dp[i][j] = min + a[j] - a[i];
            }
        }

        System.out.println(dp[0][n + 1]);
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buf = new byte[1024 * 16];
        private int head, tail;

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buf, 0, buf.length);
                if (tail <= 0) return -1;
            }
            return buf[head++];
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            int res = 0;
            while (c > 32) {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }
    }
}