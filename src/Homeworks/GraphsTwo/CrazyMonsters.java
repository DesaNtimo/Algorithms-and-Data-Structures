package Homeworks.GraphsTwo;

import java.io.*;

public class CrazyMonsters {
    static class IntList {
        int[] arr = new int[10];
        int size = 0;

        void add(int v) {
            if (size == arr.length) arr = java.util.Arrays.copyOf(arr, size * 2);
            arr[size++] = v;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        if (n == -1) return;

        int[] a = new int[n + 2];
        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();
        int[] d = new int[n + 2];
        for (int i = 1; i <= n; i++) d[i] = sc.nextInt();

        int[] L = new int[n + 2];
        int[] R = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            L[i] = i - 1;
            R[i] = i + 1;
        }

        int[] q = new int[n * 3];
        int head = 0, tail = 0;
        boolean[] inQ = new boolean[n + 2];
        boolean[] dead = new boolean[n + 2];

        for (int i = 1; i <= n; i++) {
            q[tail++] = i;
            inQ[i] = true;
        }

        int[] ans = new int[n];

        for (int k = 0; k < n; k++) {
            IntList toDie = new IntList();
            for (int i = head; i < tail; i++) {
                int u = q[i];
                inQ[u] = false;
                long dmg = 0;
                if (L[u] > 0) dmg += a[L[u]];
                if (R[u] <= n) dmg += a[R[u]];
                if (dmg > d[u]) toDie.add(u);
            }

            ans[k] = toDie.size;
            tail = 0;

            for (int i = 0; i < toDie.size; i++) dead[toDie.arr[i]] = true;

            for (int i = 0; i < toDie.size; i++) {
                int u = toDie.arr[i];
                int leftAlive = L[u], rightAlive = R[u];
                if (leftAlive > 0) R[leftAlive] = rightAlive;
                if (rightAlive <= n) L[rightAlive] = leftAlive;

                if (leftAlive > 0 && !dead[leftAlive] && !inQ[leftAlive]) {
                    q[tail++] = leftAlive;
                    inQ[leftAlive] = true;
                }
                if (rightAlive <= n && !dead[rightAlive] && !inQ[rightAlive]) {
                    q[tail++] = rightAlive;
                    inQ[rightAlive] = true;
                }
            }
        }

        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 0; i < n; i++) out.print(ans[i] + (i == n - 1 ? "" : " "));
        out.println();
        out.flush();
    }

    static class FastScanner {
        InputStream in;
        byte[] b = new byte[1 << 16];
        int h = 0, t = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (h >= t) {
                h = 0;
                t = in.read(b, 0, b.length);
                if (t <= 0) return -1;
            }
            return b[h++];
        }

        int nextInt() throws IOException {
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
    }
}