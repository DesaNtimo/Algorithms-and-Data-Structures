package Homeworks.Math;

import java.io.*;

public class LastDigitOfFactorial {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        int twos = 0;
        int last = 1;

        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp % 2 == 0) {
                twos++;
                temp /= 2;
            }
            while (temp % 5 == 0) {
                twos--;
                temp /= 5;
            }
            last = (last * (temp % 10)) % 10;
        }

        for (int i = 0; i < twos; i++) {
            last = (last * 2) % 10;
        }

        System.out.println(last);
    }
}