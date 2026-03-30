package Homeworks.Graphs;

import java.io.*;

public class TopologicalSortCheck {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        // Массивы для хранения ребер
        int[] u = new int[m];
        int[] v = new int[m];

        // Считываем все ребра
        for (int i = 0; i < m; i++) {
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        // Массив для хранения позиций вершин в перестановке
        // pos[x] будет хранить индекс, на котором стоит вершина x
        int[] pos = new int[n + 1];

        // Считываем перестановку (последняя строка)
        for (int i = 1; i <= n; i++) {
            int vertex = sc.nextInt();
            pos[vertex] = i;
        }

        // Проверяем все ребра
        boolean isValid = true;
        for (int i = 0; i < m; i++) {
            // Если начало ребра стоит в перестановке позже конца ребра - это ошибка
            if (pos[u[i]] > pos[v[i]]) {
                isValid = false;
                break;
            }
        }

        // Выводим результат строго в нужном формате
        if (isValid) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    // Надежный сканер для быстрого чтения данных
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