package Exam;

import java.io.*;

public class PetyaAndCMS {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        long n = sc.nextLong();
        if (n == -1) return;
        int m = sc.nextInt();

        long[] t = new long[m], b = new long[m], y = new long[m];
        for (int i = 0; i < m; i++) {
            t[i] = sc.nextLong();
            b[i] = sc.nextLong();
            y[i] = sc.nextLong();
        }

        long low = 0, high = (long) 3e18, ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long sum = 0;
            boolean ok = false;
            for (int i = 0; i < m; i++) {
                long cycle = t[i] * b[i] + y[i];
                long k = mid / cycle;
                long rem = mid % cycle;
                long done = k * b[i] + Math.min(b[i], rem / t[i]);

                if (n - sum <= done) {
                    ok = true;
                    break;
                }
                sum += done;
            }

            if (ok) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        long[] tasks = new long[m];
        long need = n;
        for (int i = 0; i < m; i++) {
            long cycle = t[i] * b[i] + y[i];
            long kPrev = (ans == 0) ? 0 : (ans - 1) / cycle;
            long remPrev = (ans == 0) ? 0 : (ans - 1) % cycle;
            long guaranteed = (ans == 0) ? 0 : (kPrev * b[i] + Math.min(b[i], remPrev / t[i]));

            tasks[i] = guaranteed;
            need -= guaranteed;
        }

        for (int i = 0; i < m && need > 0; i++) {
            long cycle = t[i] * b[i] + y[i];
            long k = ans / cycle;
            long rem = ans % cycle;
            long maxPossible = k * b[i] + Math.min(b[i], rem / t[i]);

            if (maxPossible > tasks[i]) {
                long add = Math.min(need, maxPossible - tasks[i]);
                tasks[i] += add;
                need -= add;
            }
        }

        PrintWriter out = new PrintWriter(System.out);
        out.println(ans);
        for (int i = 0; i < m; i++) {
            out.print(tasks[i] + (i == m - 1 ? "" : " "));
        }
        out.println();
        out.flush();
    }

    private static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buf = new byte[1 << 16];
        private int h = 0, t = 0;

        private int read() throws IOException {
            if (h >= t) {
                h = 0;
                t = in.read(buf, 0, buf.length);
                if (t <= 0) return -1;
            }
            return buf[h++];
        }

        public int nextInt() throws IOException {
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

        public long nextLong() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return -1;
            long r = 0;
            while (c > ' ') {
                r = r * 10 + (c - '0');
                c = read();
            }
            return r;
        }
    }
}