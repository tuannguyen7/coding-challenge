package codeforces;

import java.util.Scanner;

/**
 * URL: https://codeforces.com/problemset/problem/1337/A
 * */
public class IchihimeAndTriangle {

    public static int[] solve(int a, int b, int c, int d) {
        return new int[] {b, c, c,};
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);    //Instantiation Of Scanner Object
        int numTests = sc.nextInt();          //reads integer
        for (int i = 0; i < numTests; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            int[] solution = solve(a, b, c, d);
            System.out.println("" + b + " " + c + " " + c);
        }
    }
}
