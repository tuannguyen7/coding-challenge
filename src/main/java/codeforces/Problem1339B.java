package codeforces;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * URL: https://codeforces.com/problemset/problem/1339/B
 *
 */
public class Problem1339B {

    public static List<Integer> solution(int[] nums) {
        Arrays.sort(nums);
        int left = (nums.length - 1)/2;
        int right = left + 1;
        List<Integer> result = new LinkedList<>();
        while (left >= 0 && right < nums.length) {
            result.add(nums[left]);
            result.add(nums[right]);
            left -= 1;
            right += 1;
        }

        if (left == 0)
            result.add(nums[left]);
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        return result;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);    //Instantiation Of Scanner Object
        int numTests = sc.nextInt();          //reads integer
        for (int i = 0; i < numTests; i++) {
            int size = sc.nextInt();
            int[] nums = new int[size];
            for (int s = 0; s < size; s++) {
                nums[s] = sc.nextInt();
            }
            solution(nums);
        }
    }
}
