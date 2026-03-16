package Homeworks.Strings;

import java.io.*;

public class Subpalindromes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) return;

        // Трансформируем строку (вставляем '#'), чтобы одинаково обрабатывать чётные и нечётные длины
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append('#').append(s.charAt(i));
        }
        sb.append('#');
        char[] t = sb.toString().toCharArray();

        int n = t.length;
        int[] d = new int[n];
        int l = 0, r = -1;
        long totalPalindromes = 0;

        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d[l + r - i], r - i + 1);
            while (i + k < n && i - k >= 0 && t[i + k] == t[i - k]) {
                k++;
            }
            d[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
            totalPalindromes += d[i] / 2;
        }

        System.out.println(totalPalindromes);
    }
}