package Homeworks.Math;

import java.io.*;

public class GoldbachConjecture {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) isPrime[i] = true;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int p = 2; p <= n / 2; p++) {
            if (isPrime[p] && isPrime[n - p]) {
                System.out.println(p + " " + (n - p));
                break;
            }
        }
    }
}