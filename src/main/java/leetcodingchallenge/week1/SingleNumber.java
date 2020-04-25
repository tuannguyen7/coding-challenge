package leetcodingchallenge.week1;


import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                set.remove(num);
            }
        }
        return set.iterator().next();
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(new SingleNumber().singleNumber(nums));
    }
}
