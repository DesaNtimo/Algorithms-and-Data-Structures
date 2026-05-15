package Exam;

import java.io.*;

public class PetyaAndForgottenPassword {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n < 3) {
            System.out.println(0);
            return;
        }

        boolean[] isPrime = new boolean[1000];
        for (int i = 2; i < 1000; i++) isPrime[i] = true;
        for (int i = 2; i * i < 1000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < 1000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        long[] dp = new long[100];
        long MOD = 1000000009L;

        for (int p = 100; p < 1000; p++) {
            if (isPrime[p]) {
                dp[p % 100]++;
            }
        }

        for (int step = 4; step <= n; step++) {
            long[] nextDp = new long[100];
            for (int p = 100; p < 1000; p++) {
                if (isPrime[p]) {
                    nextDp[p % 100] = (nextDp[p % 100] + dp[p / 10]) % MOD;
                }
            }
            dp = nextDp;
        }

        long ans = 0;
        for (int i = 0; i < 100; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        System.out.println(ans);
    }

    static class FastScanner {
        InputStream in;
        byte[] buf = new byte[1 << 16];
        int h = 0, t = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (h >= t) {
                h = 0;
                t = in.read(buf, 0, buf.length);
                if (t <= 0) return -1;
            }
            return buf[h++];
        }

        int nextInt() throws IOException {
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