package leetcode;

public class Problem82 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = null;
        ListNode curChainNode = fakeHead;
        ListNode prevNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            if (canBeInChain(prevNode, curNode)) {
                curChainNode.next = curNode;
                curChainNode = curChainNode.next;
            }
            prevNode = curNode;
            curNode = curNode.next;
        }
        curChainNode.next = null;

        return fakeHead.next;
    }

    private boolean canBeInChain(ListNode prev, ListNode cur) {
        boolean b1 = false;
        boolean b2 = false;
        if (prev == null || cur.val != prev.val)
            b1 = true;
        if (cur.next == null || cur.val != cur.next.val)
            b2 = true;
        return b1 && b2;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
