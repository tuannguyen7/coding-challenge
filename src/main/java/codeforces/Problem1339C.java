package codeforces;

import java.util.Scanner;

// https://codeforces.com/contest/1339/problem/C
public class Problem1339C {

    public static int solve(long[] arr) {
        int minS = -1;
        long curMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= curMax)
                curMax = arr[i];
            else {
                minS = Math.max(minS, maxPow(curMax - arr[i], 0));
            }
        }

        return minS + 1;
    }

    public static void main(String[] args) {
    //

        Scanner sc=new Scanner(System.in);    //Instantiation Of Scanner Object
        int numTests = sc.nextInt();          //reads integer
        for (int i = 0; i < numTests; i++) {
            int size = sc.nextInt();
            long[] arr = new long[size];
            for (int s = 0; s < size; s++) {
                arr[s] = sc.nextLong();
            }
            System.out.println(solve(arr));
        }
    }

    public static int maxPow(long x, int exp) {
        if (x <= 0)
            return exp - 1;
        return maxPow(x - (long)Math.pow(2, exp), exp + 1);
    }

    public static double logBase(long x, int base) {
        return Math.log(x) / Math.log(base);
    }
}
