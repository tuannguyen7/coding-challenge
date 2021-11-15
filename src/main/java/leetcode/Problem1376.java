package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem1376 {

    public boolean checkIfExist(int[] arr) {
        Set<Integer> targets = new HashSet<>();
        Set<Integer> itself = new HashSet<>();
        for (int element : arr) {
            if (itself.contains(element)) {
                return true;
            }
            if (targets.contains(2*element)) {
               return true;
            }
            targets.add(element);
            itself.add(2*element);
        }
        return false;
    }
}
