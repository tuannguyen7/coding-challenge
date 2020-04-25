package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem1104 {

    public List<Integer> pathInZigZagTree(int label) {
        if (label == 1)
            return Arrays.asList(1);
        int level = level(label);
        int other = (int)(Math.pow(2, level) + Math.pow(2, level+1) - 1 - label)/2;
        LinkedList<Integer> result = new LinkedList<>();
        while (other > 0) {
            result.addFirst(label);
            result.addFirst(other);
            label /= 4;
            other /=4;
        }
        if (result.get(0) != 1)
            result.addFirst(1);
        return result;
    }

    private int level(int label) {
        if (label == 1)
            return 0;
        if (label < 4)
            return 1;
        return 2 + level(label/4);
    }

  public static void main(String[] args) {
        new Problem1104().pathInZigZagTree(1);
  }
}
