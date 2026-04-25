package Homeworks.Math;

import java.io.*;
import java.util.StringTokenizer;

public class Gears {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        System.out.println((n / gcd(n, k)) * k);
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}