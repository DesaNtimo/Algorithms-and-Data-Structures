package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: Сортировка массива и подсчёт числа инверсий
 * ===========================================================
 * <p>
 * Условие:
 * Дан массив из n чисел.
 * Нужно:
 * 1) отсортировать его по возрастанию,
 * 2) посчитать количество инверсий.
 * <p>
 * Инверсия — это пара индексов (i, j), где i < j, но arr[i] > arr[j].
 * <p>
 * Тема:
 * - Merge Sort
 * - Подсчёт инверсий
 * - Разделяй и властвуй
 * <p>
 * Идея:
 * 1) Делим массив на две половины.
 * 2) Рекурсивно сортируем левую и правую части.
 * 3) При слиянии одновременно считаем инверсии:
 *    если arr[i] > arr[j], то arr[i] и все элементы до m - 1
 *    тоже образуют инверсии с arr[j].
 * <p>
 * Почему работает:
 * - В merge sort каждая половина уже отсортирована.
 * - Поэтому при arr[i] > arr[j] можно сразу добавить (m - i) инверсий,
 *   а не искать их по одной.
 * <p>
 * Асимптотика:
 * Время: O(n log n)
 * Память: O(n)
 * <p>
 * Важно:
 * - temp нужен для слияния без потери данных.
 * - Условие arr[i] <= arr[j] сохраняет корректность и стабильность слияния.
 * - Количество инверсий хранится в long, потому что int может переполниться.
 * ===========================================================
 */
public class MergeSort {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = (int) fs.nextLong();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextLong();

        long[] temp = new long[n];
        long inv = mergeSort(arr, temp, 0, n);

        StringBuilder sb = new StringBuilder();
        sb.append(inv).append('\n');
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        sb.append('\n');
        System.out.print(sb);
    }

    private static long mergeSort(long[] arr, long[] temp, int l, int r) {
        if (r - l <= 1) return 0;
        int m = (l + r) >>> 1;
        long inv = 0;
        inv += mergeSort(arr, temp, l, m);
        inv += mergeSort(arr, temp, m, r);
        inv += merge(arr, temp, l, m, r);
        return inv;
    }

    private static long merge(long[] arr, long[] temp, int l, int m, int r) {
        int i = l, j = m, t = l;
        long inv = 0;

        while (i < m && j < r) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
                inv += (m - i);
            }
        }
        while (i < m) temp[t++] = arr[i++];
        while (j < r) temp[t++] = arr[j++];

        if (r - l >= 0) System.arraycopy(temp, l, arr, l, r - l);
        return inv;
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
