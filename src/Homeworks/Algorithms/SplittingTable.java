package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: Разрез таблицы с минимальной разницей сумм
 * ===========================================================
 * <p>
 * Условие:
 * Дана таблица n × m, заполненная числами от 1 до n*m.
 * Нужно сделать один разрез:
 * - либо горизонтальный,
 * - либо вертикальный,
 * чтобы разность сумм чисел в двух частях была минимальной.
 * <p>
 * Требуется вывести:
 * - направление разреза: H или V,
 * - номер строки/столбца, перед которым делается разрез.
 * <p>
 * Тема:
 * - Бинарный поиск по ответу
 * - Префиксные суммы по формулам
 * - Математика
 * <p>
 * Идея:
 * 1) Для горизонтального разреза сумма верхней части считается по формуле
 *    как сумма первых h * m чисел.
 * 2) Для вертикального разреза сумма левой части тоже считается по формуле,
 *    без явного построения таблицы.
 * 3) Для каждого направления бинарным поиском находим разрез,
 *    где сумма одной части максимально близка к total / 2.
 * 4) Сравниваем лучший горизонтальный и лучший вертикальный вариант.
 * <p>
 * Почему работает:
 * - При увеличении линии разреза сумма одной части растёт монотонно.
 * - Значит можно бинарным поиском найти точку, ближайшую к половине общей суммы.
 * - Проверки lo и lo - 1 достаточно, потому что optimum лежит рядом с границей перехода.
 * <p>
 * Асимптотика:
 * На один тест: O(log n + log m)
 * Память: O(1)
 * <p>
 * Важно:
 * - Таблица не строится явно, все суммы считаются формулами.
 * - pick() выбирает лучший ответ:
 *   сначала по минимальной diff,
 *   потом предпочитает V перед H,
 *   потом меньший x.
 * - mul3Div2() и mul4Div2() заранее делят один множитель на 2,
 *   чтобы снизить риск overflow.
 * ===========================================================
 */
public class SplittingTable {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int t = (int) fs.nextLong();

        StringBuilder out = new StringBuilder();

        for (int qi = 0; qi < t; qi++) {
            long n = fs.nextLong();
            long m = fs.nextLong();

            long N = n * m;
            long total = N * (N + 1) / 2;

            Answer best = null;

            if (m >= 2) {
                best = pick(null, bestVertical(n, m, total));
            }
            if (n >= 2) {
                best = pick(best, bestHorizontal(n, m, total));
            }

            assert best != null;
            out.append(best.dir).append(' ').append(best.x).append('\n');
        }

        System.out.print(out);
    }

    private static Answer pick(Answer cur, Answer cand) {
        if (cand == null) return cur;
        if (cur == null) return cand;

        if (cand.diff < cur.diff) return cand;
        if (cand.diff > cur.diff) return cur;

        if (cur.dir != cand.dir) {
            return (cand.dir == 'V') ? cand : cur;
        }

        return (cand.x < cur.x) ? cand : cur;
    }

    private static Answer bestHorizontal(long n, long m, long total) {
        long lo = 1, hi = n - 1;
        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            long T = mid * m;
            long left = T * (T + 1) / 2;
            if (2 * left >= total) hi = mid;
            else lo = mid + 1;
        }
        Answer a1 = evalHorizontal(lo, m, total);
        Answer a2 = (lo > 1) ? evalHorizontal(lo - 1, m, total) : null;
        return pick(a1, a2);
    }

    private static Answer evalHorizontal(long h, long m, long total) {
        long T = h * m;
        long left = T * (T + 1) / 2;
        long diff = total - 2 * left;
        if (diff < 0) diff = -diff;
        return new Answer('H', h + 1, diff);
    }

    private static Answer bestVertical(long n, long m, long total) {
        long lo = 1, hi = m - 1;
        while (lo < hi) {
            long mid = (lo + hi) >>> 1;
            long left = verticalLeftSum(n, m, mid);
            if (2 * left >= total) hi = mid;
            else lo = mid + 1;
        }
        Answer a1 = evalVertical(n, m, lo, total);
        Answer a2 = (lo > 1) ? evalVertical(n, m, lo - 1, total) : null;
        return pick(a1, a2);
    }

    private static Answer evalVertical(long n, long m, long k, long total) {
        long left = verticalLeftSum(n, m, k);
        long diff = total - 2 * left;
        if (diff < 0) diff = -diff;
        return new Answer('V', k + 1, diff);
    }

    private static long verticalLeftSum(long n, long m, long k) {
        long term1 = mul4Div2(k, m, n, (n - 1));
        long term2 = mul3Div2(n, k, (k + 1));
        return term1 + term2;
    }

    private static long mul3Div2(long a, long b, long c) {
        if ((b & 1) == 0) b >>= 1;
        else c >>= 1;
        return a * b * c;
    }

    private static long mul4Div2(long a, long b, long c, long d) {
        if ((c & 1) == 0) c >>= 1;
        else d >>= 1;
        return a * b * c * d;
    }

    private static final class Answer {
        final char dir;
        final long x;
        final long diff;

        Answer(char dir, long x, long diff) {
            this.dir = dir;
            this.x = x;
            this.diff = diff;
        }
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

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return Long.MIN_VALUE;
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
