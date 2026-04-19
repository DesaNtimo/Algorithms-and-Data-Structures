package Homeworks.SegmentTreeTwo;

import java.io.*;
import java.util.*;

public class Greenhouse {
    static class Plant implements Comparable<Plant> {
        long x, y;
        int id;

        Plant(long x, long y, int id) {
            this.x = x; this.y = y; this.id = id;
        }

        public int compareTo(Plant o) {
            if (this.y != o.y) return Long.compare(this.y, o.y);
            return Long.compare(this.x, o.x);
        }
    }

    static final int MAX_NODES = 400_005;
    static int[] lc = new int[MAX_NODES];
    static int[] rc = new int[MAX_NODES];
    static long[] tk = new long[MAX_NODES];
    static long[] tv = new long[MAX_NODES];
    static int[] tp = new int[MAX_NODES];
    static int root = 0;
    static int treapSz = 0;

    static int seed = 12345;
    static int nextRand() {
        seed ^= seed << 13;
        seed ^= seed >>> 17;
        seed ^= seed << 5;
        return seed;
    }

    static int newNode(long k, long v) {
        int id = ++treapSz;
        tk[id] = k;
        tv[id] = v;
        tp[id] = nextRand();
        return id;
    }

    static int splitL, splitR;

    static void split(int node, long K) {
        if (node == 0) {
            splitL = 0; splitR = 0;
            return;
        }
        if (tk[node] < K) {
            split(rc[node], K);
            rc[node] = splitL;
            splitL = node;
        } else {
            split(lc[node], K);
            lc[node] = splitR;
            splitR = node;
        }
    }

    static int merge(int l, int r) {
        if (l == 0) return r;
        if (r == 0) return l;
        if (tp[l] > tp[r]) {
            rc[l] = merge(rc[l], r);
            return l;
        } else {
            lc[r] = merge(l, lc[r]);
            return r;
        }
    }

    static long floorValue(int node, long K) {
        long res = 0;
        while (node != 0) {
            if (tk[node] <= K) {
                res = tv[node];
                node = rc[node];
            } else {
                node = lc[node];
            }
        }
        return res;
    }

    static boolean containsKey(int node, long K) {
        while (node != 0) {
            if (tk[node] == K) return true;
            if (tk[node] < K) node = rc[node];
            else node = lc[node];
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        long w = sc.nextScaledCord();
        if (w == -1) return;
        sc.nextScaledCord();
        int n = sc.nextInt();

        Plant[] plants = new Plant[n];
        for (int i = 0; i < n; i++) {
            plants[i] = new Plant(sc.nextScaledCord(), sc.nextScaledCord(), i);
        }

        Arrays.sort(plants);

        root = newNode(0L, 0L);
        long[] ans = new long[n];

        for (Plant p : plants) {
            long yBotScaled = floorValue(root, p.x);

            long L = p.y - yBotScaled;
            if (L < 0) L = 0;
            ans[p.id] = L;

            long leftScaled = p.x - L;
            long rightScaled = p.x + L;
            long newTopScaled = yBotScaled + 2 * L;

            if (leftScaled >= rightScaled) continue;

            long valAtR = floorValue(root, rightScaled);
            boolean hasRight = containsKey(root, rightScaled);

            split(root, leftScaled);
            int t1 = splitL;
            int t2 = splitR;

            split(t2, rightScaled);
            int t4 = splitR;

            root = merge(t1, t4);

            int nodeL = newNode(leftScaled, newTopScaled);
            split(root, leftScaled);
            root = merge(merge(splitL, nodeL), splitR);

            if (rightScaled < w && !hasRight) {
                int nodeR = newNode(rightScaled, valAtR);
                split(root, rightScaled);
                root = merge(merge(splitL, nodeR), splitR);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }
        out.flush();
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int head = 0, tail = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        public long nextScaledCord() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return -1;

            long res = 0;
            boolean fraction = false;
            boolean fractionDigitRead = false;
            int fractionValue = 0;

            while (c > ' ') {
                if (c == '.') {
                    fraction = true;
                } else if (c >= '0' && c <= '9') {
                    if (!fraction) {
                        res = res * 10 + (c - '0');
                    } else if (!fractionDigitRead) {
                        fractionValue = c - '0';
                        fractionDigitRead = true;
                    }
                }
                c = read();
            }

            res *= 2;
            if (fraction && fractionValue >= 5) {
                res += 1;
            }
            return res;
        }

        public int nextInt() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return -1;
            int res = 0;
            while (c > ' ') {
                if (c >= '0' && c <= '9') {
                    res = res * 10 + (c - '0');
                }
                c = read();
            }
            return res;
        }
    }
}