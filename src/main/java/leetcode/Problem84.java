package leetcode;

import java.util.Stack;

public class Problem84 {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int curMax = heights[0];
        for (int i = 1; i < heights.length; i++) {
            int index = stack.peek();
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                stack.pop();

            }
        }
        return curMax;
    }
}
