package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class NumberTheory {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int k = sc.nextInt();

        int[] dist = new int[k];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();

        // Начинаем с цифры 1 (остаток 1 % k, сумма цифр 1)
        dist[1 % k] = 1;
        deque.addFirst(1 % k);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            if (u == 0) {
                System.out.println(dist[0]);
                return;
            }

            // Переход 1: приписываем 0 (стоимость 0)
            int v1 = (u * 10) % k;
            if (dist[u] < dist[v1]) {
                dist[v1] = dist[u];
                deque.addFirst(v1); // Нулевой вес кладем в начало очереди
            }

            // Переход 2: увеличиваем цифру (стоимость 1)
            int v2 = (u + 1) % k;
            if (dist[u] + 1 < dist[v2]) {
                dist[v2] = dist[u] + 1;
                deque.addLast(v2); // Вес 1 кладем в конец очереди
            }
        }
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) { this.in = in; }

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
            int res = 0;
            while (c > ' ') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return res;
        }
    }
}