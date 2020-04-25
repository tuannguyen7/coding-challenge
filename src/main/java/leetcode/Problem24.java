package leetcode;

public class Problem24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode newHead = head.next;

        while (l2 != null) {
            ListNode savedL2Next = l2.next;
            l2.next = l1;
            if (savedL2Next != null) {
                if (savedL2Next.next != null)
                    l1.next = savedL2Next.next;
                else
                    l1.next = savedL2Next;
            } else {
                l1.next = null;
            }

            l1 = savedL2Next;
            if (l1 == null)
                break;
            l2 = l1.next;
        }

        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode next1 = new ListNode(2);
        ListNode next2 = new ListNode(3);
        ListNode next3 = new ListNode(4);
        head.next = next1;
        next1.next = next2;
        next2.next = next3;

        new Problem24().swapPairs(head);
    }
}
