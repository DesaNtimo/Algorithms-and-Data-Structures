package Homeworks.Strings;

import java.io.*;
import java.util.*;

public class Move {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        if (b.length() > a.length()) {
            System.out.println(0);
            return;
        }

        int m = b.length();
        String doubleB = b + b;

        long p = 31;
        long mod = 1_000_000_007;
        long pPowM = 1;
        for (int i = 0; i < m; i++) pPowM = (pPowM * p) % mod;

        Set<Long> validHashes = new HashSet<>();
        long currentHash = 0;

        for (int i = 0; i < doubleB.length(); i++) {
            currentHash = (currentHash * p + doubleB.charAt(i)) % mod;
            if (i >= m) {
                currentHash = (currentHash - doubleB.charAt(i - m) * pPowM % mod + mod) % mod;
            }
            if (i >= m - 1) validHashes.add(currentHash);
        }

        int ans = 0;
        currentHash = 0;
        for (int i = 0; i < a.length(); i++) {
            currentHash = (currentHash * p + a.charAt(i)) % mod;
            if (i >= m) {
                currentHash = (currentHash - a.charAt(i - m) * pPowM % mod + mod) % mod;
            }
            if (i >= m - 1 && validHashes.contains(currentHash)) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}