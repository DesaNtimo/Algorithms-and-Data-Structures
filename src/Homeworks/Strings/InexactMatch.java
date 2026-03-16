package Homeworks.Strings;

import java.io.*;
import java.util.*;

public class InexactMatch {
    static long[] pPow, hashT, hashP;
    static long p = 31, mod = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();

        int m = pattern.length();
        int n = text.length();
        if (m > n) {
            System.out.println("0\n");
            return;
        }

        pPow = new long[n + 1];
        pPow[0] = 1;
        for (int i = 1; i < pPow.length; i++) pPow[i] = (pPow[i - 1] * p) % mod;

        hashT = buildHash(text);
        hashP = buildHash(pattern);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            int lcp = getLcp(i, m);
            if (lcp == m) {
                ans.add(i + 1);
            } else {
                // Проверяем, совпадают ли хвосты после одного несовпадения
                int remaining = m - lcp - 1;
                if (remaining == 0 || getHash(hashT, i + lcp + 1, remaining) == getHash(hashP, lcp + 1, remaining)) {
                    ans.add(i + 1);
                }
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(ans.size()).append("\n");
        for (int pos : ans) out.append(pos).append(" ");
        System.out.println(out.toString().trim());
    }

    static long[] buildHash(String s) {
        long[] h = new long[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            h[i + 1] = (h[i] * p + s.charAt(i)) % mod;
        }
        return h;
    }

    static long getHash(long[] h, int start, int len) {
        return (h[start + len] - h[start] * pPow[len] % mod + mod) % mod;
    }

    static int getLcp(int startT, int maxLen) {
        int l = 0, r = maxLen, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (getHash(hashT, startT, mid) == getHash(hashP, 0, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}