package leetcode;

public class Problem543 {

    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxTree(root);
        return maxDiameter;
    }

    public int maxTree(TreeNode node) {
        if (node == null) return 0;
        int maxRight = maxTree(node.right);
        int maxLeft = maxTree(node.left);
        maxDiameter = Math.max(maxDiameter, maxRight + maxLeft);
        return 1 + Math.max(maxRight, maxLeft);
    }

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
}
