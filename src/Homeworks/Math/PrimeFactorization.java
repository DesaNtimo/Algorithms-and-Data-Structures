package Homeworks.Math;

import java.io.*;

public class PrimeFactorization {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int count = 0;
                while (n % i == 0) {
                    count++;
                    n /= i;
                }
                if (!sb.isEmpty()) sb.append("*");
                sb.append(i);
                if (count > 1) sb.append("^").append(count);
            }
        }
        if (n > 1) {
            if (!sb.isEmpty()) sb.append("*");
            sb.append(n);
        }

        System.out.println(sb);
    }
}