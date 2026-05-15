package Exam;

import java.io.*;

public class PetyaAndBattleship {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        if (n == -1) return;
        int m = sc.nextInt();

        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.nextString();
            for (int j = 0; j < m; j++) {
                assert s != null;
                grid[i][j] = s.charAt(j);
            }
        }

        boolean[][] vis = new boolean[n][m];
        int intact = 0, hit = 0, destroyed = 0;
        int[] q = new int[n * m];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '.' && !vis[i][j]) {
                    int head = 0, tail = 0;
                    q[tail++] = i * m + j;
                    vis[i][j] = true;
                    boolean hasHash = false;
                    boolean hasX = false;

                    while (head < tail) {
                        int curr = q[head++];
                        int r = curr / m;
                        int c = curr % m;

                        if (grid[r][c] == '#') hasHash = true;
                        if (grid[r][c] == 'X') hasX = true;

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dx[d], nc = c + dy[d];
                            if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != '.' && !vis[nr][nc]) {
                                vis[nr][nc] = true;
                                q[tail++] = nr * m + nc;
                            }
                        }
                    }

                    if (hasHash && !hasX) intact++;
                    else if (hasHash) hit++;
                    else if (hasX) destroyed++;
                }
            }
        }

        System.out.println(intact + " " + hit + " " + destroyed);
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

        public String nextString() throws IOException {
            int c = read();
            while (c <= ' ' && c != -1) c = read();
            if (c == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }
}