package Homeworks.Strings;

import java.io.*;

public class SubstringComparison {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int m = Integer.parseInt(br.readLine());

        long[] pPow = new long[n + 1];
        long[] hash = new long[n + 1];
        long p = 31;
        long mod = 1_000_000_007;

        pPow[0] = 1;
        for (int i = 0; i < n; i++) {
            pPow[i + 1] = (pPow[i] * p) % mod;
            hash[i + 1] = (hash[i] + (s.charAt(i) - 'a' + 1) * pPow[i]) % mod;
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);
            int d = Integer.parseInt(parts[3]);

            long hash1 = (hash[b] - hash[a - 1] + mod) % mod;
            long hash2 = (hash[d] - hash[c - 1] + mod) % mod;

            // Выравниваем хеши домножением на недостающую степень p
            if (a < c) {
                hash1 = (hash1 * pPow[c - a]) % mod;
            } else {
                hash2 = (hash2 * pPow[a - c]) % mod;
            }

            if (hash1 == hash2) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
        }
        System.out.print(out);
    }
}