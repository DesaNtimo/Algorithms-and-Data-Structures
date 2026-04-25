package Homeworks.ProblemSolvingTwo;

import java.io.*;
import java.util.Arrays;

public class Coins {
    private static int minCoins = Integer.MAX_VALUE;
    private static final int[] bestCombination = new int[20];
    private static int bestLen = 0;
    private static int[] a;
    private static int m;
    private static long target;
    private static long[] suffixSum;

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        long n = sc.nextLong();
        if (n == -1) return;
        int maxM = sc.nextInt();

        long totalMoney = 0;
        a = new int[maxM];
        for (int i = 0; i < maxM; i++) {
            a[i] = sc.nextInt();
            totalMoney += 2L * a[i];
        }

        if (totalMoney < n) {
            System.out.println(-1);
            return;
        }

        m = maxM;
        target = n;
        suffixSum = new long[maxM + 1];
        for (int i = maxM - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + 2L * a[i];
        }

        int[] current = new int[20];
        dfs(0, 0, 0, current, 0);

        if (minCoins == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            PrintWriter out = new PrintWriter(System.out);
            out.println(minCoins);
            int[] ans = Arrays.copyOf(bestCombination, bestLen);
            Arrays.sort(ans);
            for (int i = 0; i < bestLen; i++) {
                out.print(ans[i] + (i == bestLen - 1 ? "" : " "));
            }
            out.println();
            out.flush();
        }
    }

    private static void dfs(int idx, long sum, int coinsUsed, int[] current, int len) {
        if (sum == target) {
            if (coinsUsed < minCoins) {
                minCoins = coinsUsed;
                bestLen = len;
                System.arraycopy(current, 0, bestCombination, 0, len);
            }
            return;
        }
        if (idx == m) return;
        if (sum + suffixSum[idx] < target) return;
        if (coinsUsed >= minCoins) return;

        current[len] = a[idx];
        current[len + 1] = a[idx];
        dfs(idx + 1, sum + 2L * a[idx], coinsUsed + 2, current, len + 2);

        dfs(idx + 1, sum + a[idx], coinsUsed + 1, current, len + 1);

        dfs(idx + 1, sum, coinsUsed, current, len);
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

        public long nextLong() throws IOException {
            int c = read();
            while (c <= 32) {
                if (c == -1) return -1;
                c = read();
            }
            long res = 0;
            while (c > 32) {
                res = res * 10 + c - '0';
                c = read();
            }
            return res;
        }
    }
}