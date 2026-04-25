package Homeworks.Math;

import java.io.*;
import java.util.StringTokenizer;

public class ForgetfulMatvey {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long mod = Long.parseLong(st.nextToken());

        long num = power(m, n, mod);
        long den = power(k, mod - 2, mod);
        long ans = (num * den) % mod;

        System.out.println(ans);
    }

    private static long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}