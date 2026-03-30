package Homeworks.DynamicProgramming;

import java.io.*;

public class RegularBracketSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.trim().isEmpty()) {
            System.out.println();
            return;
        }
        s = s.trim();
        int n = s.length();

        int[][] dp = new int[n][n];
        int[][] split = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l <= n - len; l++) {
                int r = l + len - 1;

                if (match(s.charAt(l), s.charAt(r))) {
                    dp[l][r] = (l + 1 <= r - 1 ? dp[l + 1][r - 1] : 0) + 2;
                    split[l][r] = -1;
                }

                for (int k = l; k < r; k++) {
                    if (dp[l][k] + dp[k + 1][r] > dp[l][r]) {
                        dp[l][r] = dp[l][k] + dp[k + 1][r];
                        split[l][r] = k;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        reconstruct(0, n - 1, s, dp, split, sb);
        System.out.println(sb);
    }

    private static boolean match(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }

    private static void reconstruct(int l, int r, String s, int[][] dp, int[][] split, StringBuilder sb) {
        if (l > r || dp[l][r] == 0) return;
        if (split[l][r] == -1) {
            sb.append(s.charAt(l));
            reconstruct(l + 1, r - 1, s, dp, split, sb);
            sb.append(s.charAt(r));
        } else {
            int k = split[l][r];
            reconstruct(l, k, s, dp, split, sb);
            reconstruct(k + 1, r, s, dp, split, sb);
        }
    }
}