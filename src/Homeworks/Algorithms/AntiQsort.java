package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: AntiQuicksort (анти-тест для QuickSort)
 * ===========================================================
 * <p>
 * Условие:
 * По-заданному n нужно построить перестановку 1..n,
 * которая заставит классическую реализацию QuickSort
 * работать за худшее время.
 * <p>
 * Тема:
 * - QuickSort
 * - Анти-тесты для алгоритмов
 * <p>
 * Идея:
 * 1) Начинаем с отсортированного массива 1..n.
 * 2) Для каждого i (1..n-1) меняем местами a[i] и a[i/2].
 * 3) Полученная перестановка ломает стратегию выбора pivot
 *    в классическом QuickSort.
 * <p>
 * Почему работает:
 * - Такая перестановка приводит к крайне неудачному разбиению массива,
 *   из-за чего глубина рекурсии становится ~n.
 * <p>
 * Асимптотика построения:
 * Время: O(n)
 * Память: O(n)
 * <p>
 * Эффект для QuickSort:
 * Худшее время: O(n²)
 * <p>
 * Важно:
 * - Метод известен как AntiQuicksort (задача из e-olymp/Timus).
 * - FastScanner используется для быстрого ввода.
 * ===========================================================
 */
public class AntiQsort {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = (int) fs.nextLong();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i + 1;

        for (int i = 1; i < n; i++) {
            swap(array, i, i / 2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(array[i]);
        }
        sb.append('\n');
        System.out.print(sb);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
