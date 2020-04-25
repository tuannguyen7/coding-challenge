package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem1046 {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int stone : stones) {
            queue.add(stone);
        }

        while (queue.size() > 1) {
            int max = queue.poll();
            int secondMax = queue.poll();
            if (max != secondMax)
                queue.add(max - secondMax);
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(new Problem1046().lastStoneWeight(stones));
    }
}
