package leetcode;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = IntStream.range(0, inorder.length)
                .boxed()
                .collect(Collectors.toMap(i -> inorder[i], Function.identity()));
        Pair p = build(preorder, 0, inorder.length, null, inorderMap);
        return p.treeNode;
    }

    public Pair build(
            int[] preorder,
            int startIndex,
            int boundaryIndex, // index boundary in inorder traversal
            TreeNode parent,
            Map<Integer, Integer> inorderMap) {

        if (startIndex == preorder.length) return new Pair(null, startIndex - 1);
        if (isOutside(preorder, startIndex, boundaryIndex, parent, inorderMap))
            return new Pair(null, startIndex - 1);

        TreeNode cur = new TreeNode(preorder[startIndex]);
        int endLeftIndex = inorderMap.get(cur.val);
        Pair pLeft = build(preorder, startIndex + 1, endLeftIndex, cur, inorderMap);
        cur.left = pLeft.treeNode;
        Pair pRight = build(preorder, pLeft.index + 1, boundaryIndex, cur, inorderMap);
        cur.right = pRight.treeNode;
        return new Pair(cur, pRight.index);
    }

    public boolean isOutside(int[] preorder, int index, int endIndex, TreeNode parent, Map<Integer, Integer> inorderMap) {
        if (inorderMap.get(preorder[index]) >= endIndex)
            return true;
        return false;
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

    public class Pair {
        TreeNode treeNode;
        int index;

        public Pair(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }

    public static void main(String[] args){
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        new Problem105().buildTree(preorder, inorder);
    }
}
