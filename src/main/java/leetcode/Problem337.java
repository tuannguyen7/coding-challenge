package leetcode;

public class Problem337 {

    public static class TreeNode {
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

    public static class Pair {
        int left;
        int right;

        private Pair(int l, int r) {
            left = l;
            right = r;
        }

        public static Pair of(int l, int r) {
            return new Pair(l, r);
        }
    }

    public int rob(TreeNode root) {
        Integer[] memo = new Integer[1000007];
        int[] indexes = new int[1];
        return recursion(root, indexes, memo);
    }

    public int recursion(TreeNode node, int[] indexes, Integer[] memo) {
        if (node == null) return 0;
        if (memo[indexes[0]] != null) return memo[indexes[0]];
        int tmpIndex = indexes[0];
        // take current
        int curMax = node.val + visitChildren(node.left, indexes, memo)
                + visitChildren(node.right, indexes, memo);
        // take children
        int childrenMax = recursion(node.left, indexes, memo)
                + recursion(node.right, indexes, memo);

        indexes[0] = tmpIndex;
        memo[tmpIndex] = Math.max(curMax, childrenMax);
        return memo[tmpIndex];
    }

    public int visitChildren(TreeNode node, int[] indexes, Integer[] memo) {
        if (node == null) return 0;
        indexes[0] += 1;
        int m = recursion(node.left, indexes, memo) + recursion(node.right, indexes, memo);
        return m;
    }
}
