package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem23 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * Solution1: straight forward
     **/
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        while (true) {
            int minIndex = foundMin(lists);
            if (minIndex == -1) break;
            curNode.next = lists[minIndex];
            curNode = curNode.next;
            lists[minIndex] = lists[minIndex].next;
        }
        return head.next;
    }

    public int foundMin(ListNode[] lists) {
        int min = 999999;
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node != null && node.val < min) {
                min = node.val;
                index = i;
            }
        }
        return index;
    }

    /**
     * Solution2: priority queue
     **/
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            while (node != null) {
                queue.add(node);
                node = node.next;
            }
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
        }
        cur.next = null;
        return head.next;
    }

    public static void main(String[] args) {
    }
}
