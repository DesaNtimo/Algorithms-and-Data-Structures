package Homeworks.DynamicProgramming;

import java.io.*;
import java.util.*;

public class GrasshopperCoins {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n + 1];
        for (int i = 2; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        long[] dp = new long[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dp, Long.MIN_VALUE / 2);
        dp[1] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(1);

        for (int i = 2; i <= n; i++) {
            Integer first = dq.peekFirst();

            while (first != null && first < i - k) {
                dq.pollFirst();
                first = dq.peekFirst();
            }

            if (first == null) {
                continue;
            }

            int bestPrev = first;
            dp[i] = dp[bestPrev] + coins[i];
            parent[i] = bestPrev;

            Integer last = dq.peekLast();
            while (last != null && dp[last] <= dp[i]) {
                dq.pollLast();
                last = dq.peekLast();
            }
            dq.addLast(i);
        }

        List<Integer> path = new ArrayList<>();
        int curr = n;
        while (curr != 0) {
            path.add(curr);
            curr = parent[curr];
        }
        Collections.reverse(path);

        StringBuilder out = new StringBuilder();
        out.append(dp[n]).append("\n");
        out.append(path.size() - 1).append("\n");
        for (int p : path) {
            out.append(p).append(" ");
        }

        System.out.println(out.toString().trim());
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
            while (c <= ' ') c = readByte();
            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return (int) (res * sign);
        }
    }
}