package leetcode;

import java.lang.ref.Cleaner;
import java.util.*;

public class Problem1019 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public int[] nextLargerNodes(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> indexes = new Stack<>();
        ArrayList<Integer> nodes = new ArrayList<>();
        while (head != null) {
            nodes.add(head.val);
            head = head.next;
        }

        int[] ans = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            int curVal = nodes.get(i);
            while (!stack.isEmpty() && stack.peek() < curVal) {
                stack.pop();
                int nIndex = indexes.pop();
                ans[nIndex] = curVal;
            }

            stack.push(curVal);
            indexes.push(i);
        }

        // remaining nodes in stack are nodes without larger node
        while (!stack.isEmpty()) {
            stack.pop();
            int nIndex = indexes.pop();
            ans[nIndex] = 0;
        }
        return ans;

    }

    public static void main(String[] args) {
    }
}
