package leetcode;

public class Problem19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode curHead = head;
        while (curHead != null) {
            curHead = curHead.next;
            len++;
        }
        curHead = head;
        for (int i = 0; i < len - n - 1; i++)
            curHead = curHead.next;

        if (len == n) { // remove the head
            return head.next;
        }

        curHead.next = curHead.next.next;
        return head;
    }

    /**
     * one this in one pass.
     * Idea: Keep 2 pointers
     * */
    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        int distance = 1;
        while (distance <= n) {
            fastPointer = fastPointer.next;
            distance++;
        }

        if (fastPointer == null)
            return head.next;

        while (fastPointer.next != null) { // end
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        slowPointer.next = slowPointer.next.next;
        return head;

    }

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
