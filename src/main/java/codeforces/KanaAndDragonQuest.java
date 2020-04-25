package codeforces;

import java.util.Scanner;

/**
 * URL: https://codeforces.com/problemset/problem/1337/B
 * rank: 1000
*/
public class KanaAndDragonQuest {

    public static String solve(int x, int m, int n) {
        while (x > 20 && m > 0) {
            x = x/2 + 10;
            m -= 1;
        }

        x -= 10*n;
        if (x > 0) return "NO";
        else return "YES";
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);    //Instantiation Of Scanner Object
        int numTests = sc.nextInt();          //reads integer
        for (int i = 0; i < numTests; i++) {
            int dragonHitPoint = sc.nextInt();
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(solve(dragonHitPoint, m, n));
        }
    }
}
