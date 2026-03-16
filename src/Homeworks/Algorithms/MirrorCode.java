package Homeworks.Algorithms;

import java.io.*;
/**
 * ===========================================================
 * ЗАДАЧА: Построение палиндрома из букв строки
 * ===========================================================
 * <p>
 * Условие:
 * Дана строка из заглавных латинских букв.
 * Нужно составить из её символов палиндром максимальной длины.
 * <p>
 * Тема:
 * - Жадный алгоритм
 * - Подсчёт частот
 * - Работа со строками
 * <p>
 * Идея:
 * 1) Считаем, сколько раз встречается каждая буква.
 * 2) Из каждой буквы берём count / 2 пар и добавляем в левую половину.
 * 3) Одну букву с нечётной частотой ставим в центр.
 * 4) Правую половину строим как reverse от левой.
 * <p>
 * Почему работает:
 * - Палиндром симметричен, значит буквы по краям должны идти парами.
 * - Все возможные пары используются в левой и правой части.
 * - В центр можно поставить не более одной буквы с нечётной частотой.
 * <p>
 * Асимптотика:
 * Время: O(n)
 * Память: O(1), так как алфавит фиксирован (26 букв)
 * <p>
 * Важно:
 * - В центр берётся первая буква с остатком 1.
 * - Используются именно заглавные буквы 'A'..' Z'.
 * - Решение строит корректный палиндром из всех возможных пар.
 * ===========================================================
 */
public class MirrorCode {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();

        int[] count = new int[26];
        for (int i = 0; i < n; i++) count[s.charAt(i) - 'A']++;

        StringBuilder left = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int pairs = count[i] / 2;
            left.append(String.valueOf((char) ('A' + i)).repeat(Math.max(0, pairs)));
            count[i] %= 2;
        }

        char center = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) { center = (char)('A' + i); break; }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        StringBuilder res = new StringBuilder(left.length() * 2 + (center == 0 ? 0 : 1));
        res.append(left);
        if (center != 0) res.append(center);
        res.append(right);

        System.out.println(res);
    }
}
