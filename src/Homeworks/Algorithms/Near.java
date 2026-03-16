package Homeworks.Algorithms;

import java.io.InputStream;
import java.io.IOException;
/**
 * ===========================================================
 * ЗАДАЧА: Поиск ближайшего числа в отсортированном массиве
 * ===========================================================
 * <p>
 * Условие:
 * Дан отсортированный массив из n чисел.
 * Для каждого из k запросов target нужно вывести элемент массива,
 * который ближе всего к target.
 * <p>
 * Если есть два одинаково близких варианта,
 * нужно выбрать меньший.
 * <p>
 * Тема:
 * - Бинарный поиск
 * - lowerBound
 * - Поиск ближайшего элемента
 * <p>
 * Идея:
 * 1) Для каждого target находим позицию lowerBound —
 *    первый индекс, где arr[pos] >= target.
 * 2) Ближайший ответ может быть только:
 *    - arr[pos]
 *    - arr[pos - 1]
 * 3) Сравниваем расстояния до этих двух кандидатов.
 * 4) При равенстве выбираем левый элемент, то есть меньший.
 * <p>
 * Почему работает:
 * - Массив отсортирован.
 * - После lowerBound все элементы справа не меньше arr[pos],
 *   а все слева не больше arr[pos - 1].
 * - Значит ближайший элемент может быть только среди соседей around pos.
 * <p>
 * Асимптотика:
 * Один запрос: O(log n)
 * Все запросы: O(k log n)
 * Память: O(n)
 * <p>
 * Важно:
 * - lowerBound работает на полуинтервале [l, r).
 * - Отдельно обрабатываются случаи:
 *   - pos == 0
 *   - pos == n
 * - При равенстве расстояний выбирается левый элемент:
 *   if (dl <= dr) ans = left.
 * ===========================================================
 */
public class Near {

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

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    private static int lowerBound(long[] arr, long target) {
        int l = 0, r = arr.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (target <= arr[m]) r = m;
            else l = m + 1;
        }
        return l;
    }

    private static long getAns(long[] arr, long target, int n) {
        int pos = lowerBound(arr, target);

        long ans;
        if (pos == 0) {
            ans = arr[0];
        } else if (pos == n) {
            ans = arr[n - 1];
        } else {
            long left = arr[pos - 1];
            long right = arr[pos];

            long dl = target - left;
            if (dl < 0) dl = -dl;

            long dr = right - target;
            if (dr < 0) dr = -dr;

            if (dl <= dr) ans = left;
            else ans = right;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextLong();

        StringBuilder sb = new StringBuilder();

        for (int qi = 0; qi < k; qi++) {
            long target = fs.nextLong();
            long ans = getAns(arr, target, n);
            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}