package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Problem347 {

    // solution 1:
    // - time complexity: O(n log n)
    // - space complexity:
    public static class NumCount {

        public int num;
        public int count;
        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, NumCount> numCount = new HashMap<>();
        for (var n : nums) {
            if (!numCount.containsKey(n)) {
                numCount.put(n, new NumCount(n, 0));
            }

            numCount.get(n).count += 1;
        }

        PriorityQueue<NumCount> queue = new PriorityQueue<>(new Comparator<NumCount>() {
            @Override
            public int compare(NumCount o1, NumCount o2) {
                return o2.count - o1.count;
            }
        });
        queue.addAll(numCount.values());
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().num;
        }

        return result;
    }

    // ---------------------------------
    // solution 2
}
