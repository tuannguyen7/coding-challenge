package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem933 {


}

class RecentCounter {

    PriorityQueue<Integer> pings;
    int size;

    public RecentCounter() {
        pings = new PriorityQueue<>(8, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    public int ping(int t) {
        while (!pings.isEmpty()) {
            int top = pings.peek();
            if (t - top <= 3000) {
                break;
            }
            pings.poll();
            size -= 1;
        }

        pings.add(t);
        size += 1;
        return size;
    }
}
