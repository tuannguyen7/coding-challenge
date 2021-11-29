package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem337 {

    // Cach 1: DFS + memoization
    public int rob(TreeNode root) {
        Map<Integer, Integer> memo = new HashMap<>();
        return maximumRob(root, memo);
    }

    private int maximumRob(TreeNode node, Map<Integer, Integer> memo) {
        if (node == null) return 0;
        int hashCode = node.hashCode();

        if (memo.containsKey(hashCode)) {
            return memo.get(hashCode);
        }
        // take current node, then visit it's grand children
        int max = node.val + visitChildren(node.left, memo) + visitChildren(node.right, memo);

        // don't take current node, visit it's children
        int max2 = maximumRob(node.left, memo) + maximumRob(node.right, memo);
        memo.put(hashCode, Math.max(max, max2));
        return memo.get(hashCode);
    }

    private int visitChildren(TreeNode node, Map<Integer, Integer> memo) {
        if (node == null) return 0;
        return maximumRob(node.left, memo) + maximumRob(node.right, memo);
    }

    // Cach 2: DFS + we almost can get rid of memoization
    private int[] empty = {0, 0};
    public int rob2(TreeNode root) {
        int[] result = maximumRob2(root);
        return Math.max(result[0], result[1]);
    }

    // Visit the current node, return an array of 2 elements.
    // The first element is the maximum money we can get if we take the current node
    // The second element thi2 is the maximum money we can get if we NOT take the current node
    private int[] maximumRob2(TreeNode node) {
        if (node == null) return empty;

        // don't take current node, visit it's children
        int[] lValues = maximumRob2(node.left);
        int[] rValues = maximumRob2(node.right);

        int maxIfTakeCurNode = node.val + lValues[1] + rValues[1];

        int maxIfNotCurNode = Math.max(lValues[0] + rValues[0], lValues[1] + rValues[1]);
        maxIfNotCurNode = Math.max(maxIfNotCurNode, lValues[1] + rValues[0]);
        maxIfNotCurNode = Math.max(maxIfNotCurNode, lValues[0] + rValues[1]);
        // Or better
        // int maxIfNotCurNode = Math.max(lValues[0], lValues[1]) + Math.max(lValues[1], rValues[1]);

        return new int[]{maxIfTakeCurNode, maxIfNotCurNode};
    }
}

class TreeNode {
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
