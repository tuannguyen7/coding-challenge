package leetcode;

public class BinaryTreeMaximumPathSum {

    int max;
    public int maxPathSum(TreeNode root) {
        max = root.val;
        maxPath(root);
        return max;
    }

    public int maxPath(TreeNode node) {
        if (node == null)
            return 0;
        int maxLeft = maxPath(node.left);
        int maxRight = maxPath(node.right);
        int maxAtNode = Math.max(Math.max(node.val, node.val +maxLeft), node.val + maxRight);
        max = Math.max(Math.max(node.val + maxLeft + maxRight, max), maxAtNode);
        return maxAtNode;
    }

    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }
}
