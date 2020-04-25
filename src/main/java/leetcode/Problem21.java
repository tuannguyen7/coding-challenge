package leetcode;

public class Problem21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        ListNode curNewList = head;
        while (curL1 != null && curL2 != null) {
            if (curL1.val < curL2.val) {
                curNewList.next = curL1;
                curNewList = curL1;
                curL1 = curL1.next;
            } else {
                curNewList.next = curL2;
                curNewList = curL2;
                curL2 = curL2.next;
            }
        }

        if (curL1 == null)
            curNewList.next = curL2;
        else
            curNewList.next = curL1;

        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
