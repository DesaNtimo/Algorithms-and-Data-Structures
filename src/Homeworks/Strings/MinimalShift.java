package Homeworks.Strings;

import java.io.*;

public class MinimalShift {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            char ci = s.charAt((i + k) % n);
            char cj = s.charAt((j + k) % n);
            if (ci == cj) {
                k++;
            } else {
                if (ci > cj) {
                    i += k + 1;
                    if (i <= j) i = j + 1;
                } else {
                    j += k + 1;
                    if (j <= i) j = i + 1;
                }
                k = 0;
            }
        }

        int startIdx = Math.min(i, j);
        System.out.println(s.substring(startIdx) + s.substring(0, startIdx));
    }
}