package line;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

// Complexity: O(w) w is the size of the sliding window
public class Problem7 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer w = Integer.parseInt(br.readLine().trim());
        MaximumSlidingWindow window = new MaximumSlidingWindow(w);
        for (int i = 0; i < w; i++) {
            int element = Integer.parseInt(br.readLine().trim());
            window.add(element);
        }
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            int element = Integer.parseInt(line.trim());
            window.add(element);
            System.out.println(window.max());
        }
    }

    public static class MaximumSlidingWindow {
        LinkedList<Integer> elements;
        int capacity;
        int maximalIndex;
        int maximalElement;

        public MaximumSlidingWindow(int capacity) {
            this.elements = new LinkedList<>();
            this.capacity = capacity;
            maximalElement = -1;
            maximalIndex = -1;
        }

        public boolean add(Integer element) {
            if (elements.size() == capacity)
                poll();
            if (element >= maximalElement) {
                maximalIndex = elements.size();
                maximalElement = element;
            }
            return elements.add(element);
        }

        public int poll() {
            int element = elements.poll();
            if (maximalIndex == 0) {
               findMax();
            }
            return element;
        }

        private void findMax() {
            for (int i = 0; i < elements.size(); i++) {
                int e = elements.get(i);
                if (e >= maximalElement) {
                    maximalElement = e;
                    maximalIndex = i;
                }
            }
        }

        public int max() {
            return maximalElement;
        }
    }
}
