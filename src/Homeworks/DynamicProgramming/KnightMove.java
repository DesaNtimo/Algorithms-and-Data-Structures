package Homeworks.DynamicProgramming;

import java.io.*;

public class KnightMove {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] dp = new long[n + 1][m + 1];
        dp[1][1] = 1;

        for (int k = 2; k <= n + m; k++) {
            for (int i = 1; i <= n; i++) {
                int j = k - i;
                if (j >= 1 && j <= m) {
                    if (i - 2 >= 1 && j - 1 >= 1) dp[i][j] += dp[i - 2][j - 1];
                    if (i - 1 >= 1 && j - 2 >= 1) dp[i][j] += dp[i - 1][j - 2];
                    if (i + 1 <= n && j - 2 >= 1) dp[i][j] += dp[i + 1][j - 2];
                    if (i - 2 >= 1 && j + 1 <= m) dp[i][j] += dp[i - 2][j + 1];
                }
            }
        }

        System.out.println(dp[n][m]);
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
            while (c <= ' ' && c != -1) c = readByte();
            if (c == -1) return -1;
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res;
        }
    }
}