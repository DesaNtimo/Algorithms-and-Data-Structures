package Homeworks.ProblemSolving;

import java.io.*;
import java.util.Arrays;

public class RegionalOlympiadPlanner {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long c = fs.nextLong();

        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            long s = fs.nextLong();
            long t = fs.nextLong();
            tasks[i] = new Task(i + 1, s, s + t);
        }

        Arrays.sort(tasks);

        int[] chosen = new int[n];
        int cnt = 0;
        long lastEnd = Long.MIN_VALUE;

        for (Task task : tasks) {
            if (task.start >= lastEnd) {
                chosen[cnt++] = task.index;
                lastEnd = task.end;
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(cnt * c).append('\n');
        out.append(cnt).append('\n');
        for (int i = 0; i < cnt; i++) {
            if (i > 0) out.append(' ');
            out.append(chosen[i]);
        }
        out.append('\n');

        System.out.print(out);
    }

    private static final class Task implements Comparable<Task> {
        int index;
        long start;
        long end;

        Task(int index, long start, long end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Task o) {
            if (end != o.end) return Long.compare(end, o.end);
            if (start != o.start) return Long.compare(start, o.start);
            return Integer.compare(index, o.index);
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