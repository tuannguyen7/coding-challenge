package leetcode;

public class Problem86 {

    // Solution1. Long and verbose implementation
    public ListNode partition(ListNode head, int x) {
        ListNode leftSideHead = new ListNode(1);
        ListNode rightSideHead = new ListNode(1);
        ListNode curLeft = null;
        ListNode curRight = null;
        ListNode curIterator = head;
        while (curIterator != null) {
            if (curIterator.val < x) {
                if (curLeft == null) {
                    curLeft = curIterator;
                    leftSideHead.next = curLeft;
                } else {
                    curLeft.next = curIterator;
                    curLeft = curLeft.next;
                }
            } else {
                if (curRight == null) {
                    curRight = curIterator;
                    rightSideHead.next = curRight;
                } else {
                    curRight.next = curIterator;
                    curRight = curRight.next;
                }
            }

            curIterator = curIterator.next;
        }
        if (curLeft != null) {
            curLeft.next = rightSideHead.next;
        }
        if (curRight != null) {
            curRight.next = null;
        }
        if (leftSideHead.next == null) {
            leftSideHead.next = rightSideHead.next;
        }
        return leftSideHead.next;
    }


    // Solution2. concise solution
    public ListNode partition2(ListNode head, int x) {
        ListNode leftSideHead = new ListNode(1);
        ListNode rightSideHead = new ListNode(1);
        ListNode curLeft = leftSideHead;
        ListNode curRight = rightSideHead;
        while (head != null) {
            if (head.val < x) {
                curLeft.next = head;
                curLeft = curLeft.next;
            } else {
                curRight.next = head;
                curRight = curRight.next;
            }
            head = head.next;
        }
        curLeft.next = rightSideHead.next;
        curRight.next = null;
        return leftSideHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
