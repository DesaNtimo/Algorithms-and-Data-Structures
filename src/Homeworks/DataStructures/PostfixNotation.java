package Homeworks.DataStructures;

import java.io.*;

public class PostfixNotation {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long[] st = new long[1024];
        int top = 0;

        String tok;
        while ((tok = fs.next()) != null) {
            if (tok.length() == 1) {
                char op = tok.charAt(0);
                if (op == '+' || op == '-' || op == '*') {
                    long b = st[--top];
                    long a = st[--top];
                    long res = (op == '+') ? (a + b) : (op == '-') ? (a - b) : (a * b);
                    if (top == st.length) st = grow(st);
                    st[top++] = res;
                    continue;
                }
            }
            long x = Long.parseLong(tok);
            if (top == st.length) st = grow(st);
            st[top++] = x;
        }

        System.out.println(st[top - 1]);
    }

    private static long[] grow(long[] a) {
        long[] b = new long[a.length << 1];
        System.arraycopy(a, 0, b, 0, a.length);
        return b;
    }

    private static final class FastScanner {
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

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }
}
