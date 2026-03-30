package Homeworks.Graphs;

import java.io.*;
import java.util.*;

public class HungryKnight {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        int endX = sc.nextInt();
        int endY = sc.nextInt();

        // Смещения для хода коня
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        int[][] dist = new int[n + 1][n + 1];
        int[][] parentX = new int[n + 1][n + 1];
        int[][] parentY = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        dist[startX][startY] = 0;

        // BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == endX && y == endY) break; // Нашли цель

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    parentX[nx][ny] = x;
                    parentY[nx][ny] = y;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // Восстанавливаем путь с конца в начало
        List<int[]> path = new ArrayList<>();
        int cx = endX;
        int cy = endY;
        while (cx != startX || cy != startY) {
            path.add(new int[]{cx, cy});
            int px = parentX[cx][cy];
            int py = parentY[cx][cy];
            cx = px;
            cy = py;
        }
        path.add(new int[]{startX, startY});
        Collections.reverse(path);

        // Вывод ответа
        StringBuilder out = new StringBuilder();
        out.append(dist[endX][endY]).append("\n");
        for (int[] p : path) {
            out.append(p[0]).append(" ").append(p[1]).append("\n");
        }
        System.out.print(out);
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