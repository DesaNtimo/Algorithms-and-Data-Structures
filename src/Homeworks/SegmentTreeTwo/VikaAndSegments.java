package Homeworks.SegmentTreeTwo;

import java.io.*;
import java.util.*;

public class VikaAndSegments {
    static class Event {
        long x, y1, y2;
        int type;

        Event(long x, int type, long y1, long y2) {
            this.x = x;
            this.type = type;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = Integer.parseInt(sc.next());

        Map<Long, List<long[]>> hMap = new HashMap<>();
        Map<Long, List<long[]>> vMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long x1 = sc.nextLong(), y1 = sc.nextLong();
            long x2 = sc.nextLong(), y2 = sc.nextLong();
            if (y1 == y2) {
                if (x1 > x2) {
                    long t = x1;
                    x1 = x2;
                    x2 = t;
                }
                hMap.computeIfAbsent(y1, k -> new ArrayList<>()).add(new long[]{x1, x2});
            } else {
                if (y1 > y2) {
                    long t = y1;
                    y1 = y2;
                    y2 = t;
                }
                if (x1 > x2) {
                    x1 = x2;
                }
                vMap.computeIfAbsent(x1, k -> new ArrayList<>()).add(new long[]{y1, y2});
            }
        }

        List<long[]> mergedH = new ArrayList<>();
        for (Map.Entry<Long, List<long[]>> e : hMap.entrySet()) {
            long y = e.getKey();
            List<long[]> list = e.getValue();
            list.sort(Comparator.comparingLong(a -> a[0]));
            long c1 = list.getFirst()[0], c2 = list.getFirst()[1];
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i)[0] <= c2) c2 = Math.max(c2, list.get(i)[1]);
                else {
                    mergedH.add(new long[]{c1, c2, y});
                    c1 = list.get(i)[0];
                    c2 = list.get(i)[1];
                }
            }
            mergedH.add(new long[]{c1, c2, y});
        }

        List<long[]> mergedV = new ArrayList<>();
        for (Map.Entry<Long, List<long[]>> e : vMap.entrySet()) {
            long x = e.getKey();
            List<long[]> list = e.getValue();
            list.sort(Comparator.comparingLong(a -> a[0]));
            long c1 = list.getFirst()[0], c2 = list.getFirst()[1];
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i)[0] <= c2) c2 = Math.max(c2, list.get(i)[1]);
                else {
                    mergedV.add(new long[]{x, c1, c2});
                    c1 = list.get(i)[0];
                    c2 = list.get(i)[1];
                }
            }
            mergedV.add(new long[]{x, c1, c2});
        }

        long total = 0;
        TreeSet<Long> ySet = new TreeSet<>();

        for (long[] h : mergedH) {
            total += h[1] - h[0] + 1;
            ySet.add(h[2]);
        }
        for (long[] v : mergedV) {
            total += v[2] - v[1] + 1;
            ySet.add(v[1]);
            ySet.add(v[2]);
        }

        Map<Long, Integer> yComp = new HashMap<>();
        int yId = 1;
        for (long y : ySet) yComp.put(y, yId++);

        List<Event> events = new ArrayList<>();
        for (long[] h : mergedH) {
            events.add(new Event(h[0], 1, h[2], 0));
            events.add(new Event(h[1] + 1, 0, h[2], 0));
        }
        for (long[] v : mergedV) {
            events.add(new Event(v[0], 2, v[1], v[2]));
        }

        events.sort((a, b) -> a.x != b.x ? Long.compare(a.x, b.x) : Integer.compare(a.type, b.type));

        int[] fenwick = new int[yId + 1];
        long inter = 0;
        for (Event e : events) {
            if (e.type == 1) add(fenwick, yComp.get(e.y1), 1);
            else if (e.type == 0) add(fenwick, yComp.get(e.y1), -1);
            else inter += query(fenwick, yComp.get(e.y2)) - query(fenwick, yComp.get(e.y1) - 1);
        }

        System.out.println(total - inter);
    }

    static void add(int[] bit, int i, int v) {
        for (; i < bit.length; i += i & -i) bit[i] += v;
    }

    static int query(int[] bit, int i) {
        int s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}