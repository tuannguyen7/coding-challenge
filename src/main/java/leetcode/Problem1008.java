package leetcode;

import java.util.Stack;

public class Problem1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode head = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            TreeNode newNode = new TreeNode(val);
            TreeNode parentNode = head;
            TreeNode curNode = head;
            while (curNode != null) {
                if (val < curNode.val) {
                    parentNode = curNode;
                    curNode = curNode.left;
                } else {
                    parentNode = curNode;
                    curNode = curNode.right;
                }
            }
            if (val < parentNode.val)
                parentNode.left = newNode;
            else
                parentNode.right = newNode;

        }
        return head;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        int[] preorder = {8,5,1,7,10,12};
        new Problem1008().bstFromPreorder(preorder);
    }
}
