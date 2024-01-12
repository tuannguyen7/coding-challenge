package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem1123 {

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

    public TreeNode lcaDeepestLeaves(TreeNode root) {
         int[] depths = new int[1001];
         Arrays.fill(depths, -1);
         LinkedList<Integer>[] nodeParents = new LinkedList[1001];
         dfs(root, 0, new LinkedList<>(), nodeParents, depths);
         int maxDepth = Arrays.stream(depths).max().getAsInt();
         int[] deepestNodes = IntStream.range(0, depths.length).filter(i -> depths[i] == maxDepth).toArray();

         System.out.println(nodeParents[deepestNodes[0]].stream().map(v -> v + ",").collect(Collectors.joining()));
         Integer commonNodeVal = root.val;
         Integer prevCommonNodeVal = commonNodeVal;
         outter: while (commonNodeVal != null) {
             for (int nodeVal : deepestNodes) {
                 if (!Objects.equals(nodeParents[nodeVal].pollFirst(), commonNodeVal)) {
                     break outter;
                 }
             }
             prevCommonNodeVal = commonNodeVal;
             commonNodeVal = nodeParents[deepestNodes[0]].peek();
         }
         return findNode(root, prevCommonNodeVal);
    }

    private TreeNode findNode(TreeNode node, int v) {
         if (node == null) return null;
         if (node.val == v) return node;
         TreeNode r = findNode(node.right, v);
         if (r != null) return r;
         return findNode(node.left, v);
    }

    public void dfs(TreeNode node, int depth, LinkedList<Integer> parents, LinkedList<Integer>[] nodeParents, int[] depths) {
         if (node == null)
             return;
        parents.addLast(node.val);
         if (isLeaf(node)) {
             nodeParents[node.val] = new LinkedList<>(parents);
             depths[node.val] = depth;
         } else {
             parents.addLast(node.val);
             dfs(node.left, depth + 1, parents, nodeParents, depths);
             dfs(node.right, depth + 1, parents, nodeParents, depths);
         }
        parents.pollLast();
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    public static void main(String[] args) {

    }
}
