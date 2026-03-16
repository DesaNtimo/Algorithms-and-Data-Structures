package Homeworks.ProblemSolving;

import java.io.*;
import java.util.Arrays;

public class PaintedLineLength {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        Segment[] segs = new Segment[n];

        for (int i = 0; i < n; i++) {
            long l = fs.nextLong();
            long r = fs.nextLong();
            segs[i] = new Segment(l, r);
        }

        Arrays.sort(segs);

        long ans = 0;
        long curL = segs[0].l;
        long curR = segs[0].r;

        for (int i = 1; i < n; i++) {
            if (segs[i].l > curR) {
                ans += curR - curL;
                curL = segs[i].l;
                curR = segs[i].r;
            } else {
                curR = Math.max(curR, segs[i].r);
            }
        }

        ans += curR - curL;
        System.out.println(ans);
    }

    private static final class Segment implements Comparable<Segment> {
        long l, r;

        Segment(long l, long r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Segment o) {
            if (l != o.l) return Long.compare(l, o.l);
            return Long.compare(r, o.r);
        }
    }

    private static final class FastScanner {
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

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}