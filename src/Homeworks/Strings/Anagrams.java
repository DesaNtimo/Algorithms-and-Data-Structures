package Homeworks.Strings;

import java.io.*;
import java.util.*;

public class Anagrams {
    static long[] randHash;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] a = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine().trim());
        int[] b = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Каждому числу сопоставляем случайное 64-битное значение
        randHash = new long[100_005];
        Random rnd = new Random(42);
        for (int i = 0; i < randHash.length; i++) randHash[i] = rnd.nextLong();

        int left = 0, right = Math.min(n, m), ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == 0 || checkLen(a, b, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean checkLen(int[] a, int[] b, int len) {
        Set<Long> aHashes = new HashSet<>();
        long currentHash = 0;

        for (int i = 0; i < a.length; i++) {
            currentHash += randHash[a[i]];
            if (i >= len) currentHash -= randHash[a[i - len]];
            if (i >= len - 1) aHashes.add(currentHash);
        }

        currentHash = 0;
        for (int i = 0; i < b.length; i++) {
            currentHash += randHash[b[i]];
            if (i >= len) currentHash -= randHash[b[i - len]];
            if (i >= len - 1 && aHashes.contains(currentHash)) return true;
        }
        return false;
    }
}