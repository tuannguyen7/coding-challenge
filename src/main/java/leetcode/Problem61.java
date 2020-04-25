package leetcode;

public class Problem61 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        int len = len(head);
        ListNode oldHead = head;
        ListNode lastNode = getLast(head);
        ListNode cur = head;
        while (k < len - 1) {
            cur = cur.next;
            k++;
        }
        lastNode.next = head;
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    public ListNode getLast(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
    public int len(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
    //
    }
}
