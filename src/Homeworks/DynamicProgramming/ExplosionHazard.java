package Homeworks.DynamicProgramming;

import java.io.*;

public class ExplosionHazard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        long[] dpA = new long[n + 1];
        long[] dpBC = new long[n + 1];

        dpA[1] = 1;
        dpBC[1] = 2;

        for (int i = 2; i <= n; i++) {
            dpA[i] = dpBC[i - 1];
            dpBC[i] = 2 * (dpA[i - 1] + dpBC[i - 1]);
        }

        System.out.println(dpA[n] + dpBC[n]);
    }
}