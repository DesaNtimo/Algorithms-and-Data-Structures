package Homeworks.ProblemSolvingTwo;

import java.io.*;

public class StringCompression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) return;
        s = s.trim();
        int n = s.length();
        if (n == 0) return;

        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.substring(i, j + 1);

                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }

                for (int p = 1; p <= len / 2; p++) {
                    if (len % p == 0) {
                        boolean match = true;
                        for (int k = 0; k < len; k++) {
                            if (s.charAt(i + k) != s.charAt(i + k % p)) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            String compressed = (len / p) + "(" + dp[i][i + p - 1] + ")";
                            if (compressed.length() < dp[i][j].length()) {
                                dp[i][j] = compressed;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}