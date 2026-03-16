package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: Проверка наличия числа в отсортированном массиве
 * ===========================================================
 * <p>
 * Условие:
 * Дан отсортированный массив из n чисел.
 * Дано k запросов. Для каждого числа x нужно определить,
 * присутствует ли оно в массиве.
 * <p>
 * Вывести:
 * YES — если число есть,
 * NO — если числа нет.
 * <p>
 * Тема:
 * - Бинарный поиск (Binary Search)
 * - Обработка большого числа запросов
 * - Быстрый ввод/вывод (Fast I/O)
 * <p>
 * Идея:
 * 1) Считываем отсортированный массив.
 * 2) Для каждого запроса x выполняем бинарный поиск.
 * 3) Если элемент найден — выводим YES, иначе NO.
 * <p>
 * Почему работает:
 * - Массив отсортирован.
 * - Каждый шаг бинарного поиска уменьшает диапазон поиска в 2 раза.
 * <p>
 * Асимптотика:
 * Поиск одного элемента: O(log n)
 * Все запросы: O(k log n)
 * Память: O(n)
 * <p>
 * Важно:
 * - mid вычисляется как l + (r - l)/2 (защита от overflow).
 * - FastScanner используется для быстрого чтения больших входных данных.
 * - StringBuilder уменьшает количество операций вывода.
 * ===========================================================
 */
public class BinarySearch {

    // Быстрый ввод целых чисел (int) из stdin
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
                if (c == -1) return Long.MIN_VALUE; // нет данных
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

    private static boolean contains(long[] a, long x) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (x == a[mid]) return true;
            if (x > a[mid]) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextLong();
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < k; i++) {
            long x = fs.nextLong();
            out.append(contains(arr, x) ? "YES" : "NO").append('\n');
        }

        System.out.print(out);
    }
}