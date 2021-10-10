package leetcode;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Problem199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        ArrayList<TreeNode> curLevelNodes = new ArrayList<>();
        ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
        if (root == null) return ans;
        curLevelNodes.add(root);
        while (curLevelNodes.size() > 0) {
            TreeNode head = curLevelNodes.get(0);
            ans.add(head.val);
            for (TreeNode n : curLevelNodes) {
                if (n.right != null) {
                    nextLevelNodes.add(n.right);
                }
                if (n.left != null) {
                    nextLevelNodes.add(n.left);
                }
            }
            curLevelNodes = nextLevelNodes;
            nextLevelNodes = new ArrayList<>();
        }
        return ans;
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
