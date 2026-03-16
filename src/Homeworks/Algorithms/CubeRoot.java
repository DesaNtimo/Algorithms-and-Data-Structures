package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: Нахождение корня кубического уравнения
 * ===========================================================
 * <p>
 * Условие:
 * Даны коэффициенты a, b, c, d кубического уравнения
 * ax^3 + bx^2 + cx + d = 0.
 * Нужно найти один его действительный корень с высокой точностью.
 * <p>
 * Тема:
 * - Бинарный поиск по ответу
 * - Численные методы
 * - Вещественный бинарный поиск
 * <p>
 * Идея:
 * 1) Рассматриваем функцию f(x) = ax^3 + bx^2 + cx + d.
 * 2) Сначала находим отрезок [lo, hi], на концах которого функция имеет разные знаки.
 * 3) Дальше бинарным поиском сужаем этот отрезок.
 * 4) В конце середина отрезка и есть приближение корня.
 * <p>
 * Почему работает:
 * - Кубический многочлен непрерывен.
 * - Если f(lo) и f(hi) разных знаков, на отрезке есть корень.
 * - Каждый шаг уменьшает длину отрезка в 2 раза.
 * <p>
 * Асимптотика:
 * Время: O(K), где K = 200 итераций
 * Память: O(1)
 * <p>
 * Важно:
 * - Это не "cube root" числа, а поиск корня кубического уравнения.
 * - Сначала отрезок расширяется, пока на его концах не появятся разные знаки.
 * - 200 итераций более чем достаточно для точности double.
 * ===========================================================
 */
public class CubeRoot {

    private static double a, b, c, d;

    private static double f(double x) {
        return ((a * x + b) * x + c) * x + d;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        a = fs.nextLong();
        b = fs.nextLong();
        c = fs.nextLong();
        d = fs.nextLong();

        double lo = -1.0, hi = 1.0;
        double flo = f(lo), fhi = f(hi);

        while (flo * fhi > 0) {
            lo *= 2.0;
            hi *= 2.0;
            flo = f(lo);
            fhi = f(hi);
        }

        for (int it = 0; it < 200; it++) {
            double mid = (lo + hi) / 2.0;
            double fmid = f(mid);
            if (flo * fmid <= 0) {
                hi = mid;
            } else {
                lo = mid;
                flo = fmid;
            }
        }

        System.out.printf("%.10f%n", (lo + hi) / 2.0);
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