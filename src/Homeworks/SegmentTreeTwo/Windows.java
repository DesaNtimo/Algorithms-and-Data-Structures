package Homeworks.SegmentTreeTwo;

import java.io.*;
import java.util.*;

public class Windows {
    static final int OFFSET = 200000;
    static final int MAX_CORD = 400005;
    static int[] max, lazy, pos;

    static class Event implements Comparable<Event> {
        int x, y1, y2, type;

        Event(int x, int y1, int y2, int type) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
        }

        public int compareTo(Event o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(o.type, this.type);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt(), y1 = sc.nextInt();
            int x2 = sc.nextInt(), y2 = sc.nextInt();
            events.add(new Event(x1, y1 + OFFSET, y2 + OFFSET, 1));
            events.add(new Event(x2 + 1, y1 + OFFSET, y2 + OFFSET, -1));
        }
        Collections.sort(events);

        max = new int[4 * MAX_CORD];
        lazy = new int[4 * MAX_CORD];
        pos = new int[4 * MAX_CORD];
        build(1, 0, MAX_CORD - 1);

        int bestMax = -1, bestX = 0, bestY = 0;
        int i = 0;
        while (i < events.size()) {
            int curX = events.get(i).x;
            while (i < events.size() && events.get(i).x == curX) {
                Event e = events.get(i);
                update(1, 0, MAX_CORD - 1, e.y1, e.y2, e.type);
                i++;
            }
            if (max[1] > bestMax) {
                bestMax = max[1];
                bestX = curX;
                bestY = pos[1] - OFFSET;
            }
        }
        System.out.println(bestMax);
        System.out.println(bestX + " " + bestY);
    }

    static void build(int node, int start, int end) {
        if (start == end) pos[node] = start;
        else {
            int mid = (start + end) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            pos[node] = pos[2 * node];
        }
    }

    static void apply(int node, int v) {
        max[node] += v;
        lazy[node] += v;
    }

    static void push(int node) {
        if (lazy[node] != 0) {
            apply(2 * node, lazy[node]);
            apply(2 * node + 1, lazy[node]);
            lazy[node] = 0;
        }
    }

    static void update(int node, int start, int end, int l, int r, int v) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            apply(node, v);
            return;
        }
        push(node);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r, v);
        update(2 * node + 1, mid + 1, end, l, r, v);
        if (max[2 * node] >= max[2 * node + 1]) {
            max[node] = max[2 * node];
            pos[node] = pos[2 * node];
        } else {
            max[node] = max[2 * node + 1];
            pos[node] = pos[2 * node + 1];
        }
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        public FastScanner(InputStream in) {
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
            while (c <= ' ' && c != -1) c = readByte();
            if (c == -1) return -1;
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res * sign;
        }
    }
}