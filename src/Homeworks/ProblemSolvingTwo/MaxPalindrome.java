package Homeworks.ProblemSolvingTwo;

import java.io.*;

public class MaxPalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) return;
        s = s.trim();
        int n = s.length();
        if (n == 0) return;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[0][n - 1]);

        char[] res = new char[dp[0][n - 1]];
        int l = 0, r = n - 1;
        int leftIdx = 0, rightIdx = res.length - 1;

        while (l <= r) {
            if (l == r) {
                res[leftIdx] = s.charAt(l);
                break;
            }
            if (s.charAt(l) == s.charAt(r)) {
                res[leftIdx++] = s.charAt(l);
                res[rightIdx--] = s.charAt(r);
                l++;
                r--;
            } else if (dp[l + 1][r] > dp[l][r - 1]) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(new String(res));
    }
}