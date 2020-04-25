package leetcode;

import java.util.*;

public class Problem855 {

    ArrayList<Integer> spots;
    int N;

    public Problem855(int N) {
        spots = new ArrayList<Integer>();
        this.N = N;
    }

    public int seat() {
        if (spots.isEmpty()) {
            addSorted(0, spots);
            return 0;
        } else if (spots.size() == 1) {
            int prevSpot = spots.get(0);
            int newSpot = 0;
            if (prevSpot < N/2)
                newSpot = N - 1;

            addSorted(newSpot, spots);
            return newSpot;
        } else {
            int newSpot = getMax(spots);
            addSorted(newSpot, spots);
            return newSpot;
        }
    }

    public void leave(int p) {
        Integer key = p;
        spots.remove(key);
    }

    public static void main(String[] args) {
        Problem855 problem = new Problem855(10);
        //int[] commands = {-1, -1, -1, 0, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0};
        int[] commands = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, -1};
        List<Integer> out = new LinkedList<Integer>();
        for (int c : commands) {
            if (c == -1)
                out.add(problem.seat());
            else
                problem.leave(c);
        }

        out.forEach(System.out::println);
    }

    public void addSorted(int n, ArrayList<Integer> list) {
        list.add(n);
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) < list.get(i - 1)) {
                // swap
                int tmp = list.get(i);
                list.set(i, list.get(i - 1));
                list.set(i - 1, tmp);
            }
        }
    }

    public int getMax(ArrayList<Integer> list) {
        int leftMax = 0;
        int rightMax = 0;
        int curLenMax = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            int len = (list.get(i + 1) - list.get(i)) / 2;
            if (len > curLenMax) {
                leftMax = list.get(i);
                rightMax = list.get(i + 1);
                curLenMax = len;
            }
        }

        int ret = (rightMax + leftMax) / 2;
        if (curLenMax < (N - 1) - list.get(list.size() - 1)) {
            curLenMax = (N - 1) - list.get(list.size() - 1);
            ret = N - 1;
        }

        if (curLenMax < list.get(0))
            ret = 0;

        return ret;
    }

    public int binarySearch(ArrayList<Integer> list, int key, int left, int right) {
        if (left >= right)
            return right;
        int mid = (right + left) / 2;
        if (list.get(mid) == key)
            return mid;
        else if (list.get(mid) < key)
            return binarySearch(list, key, mid + 1, right);
        else
            return binarySearch(list, key, left, mid);
    }

    public static class Range implements Comparable<Range> {
        public int left;        // inclusive
        public int right;    // exclusive
        public int len;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
            len = right - left;
        }

        public int compareTo(Range o) {
            if (len == o.len)
                return left - o.left;
            return len - o.left;
        }
    }

}
