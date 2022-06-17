package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1048 {

    public int longestStrChain(String[] words) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] treeHeight = new int[words.length];
        Arrays.fill(treeHeight, -1);
        for (int i = 0; i < words.length; i++)
            adjList.add(new ArrayList<>());

        for (int i = 0; i < words.length - 1; i++)
            for (int j = i + 1; j < words.length; j++) {
                if (isPredecessor(words[i], words[j])) {
                    adjList.get(i).add(j);
                }
                if (isPredecessor(words[j], words[i])) {
                    adjList.get(j).add(i);
                }
            }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            // DFS
            max = Math.max(max, dfs(i, adjList, treeHeight));
        }
        return max;
    }

    public int dfs(int vertex, List<List<Integer>> adjList, int[] treeHeight) {
        if (treeHeight[vertex] != -1) {
            return treeHeight[vertex];
        }
        int maxH = 0;
        for (int child : adjList.get(vertex)) {
            maxH = Math.max(maxH, dfs(child, adjList, treeHeight));
        }
        treeHeight[vertex] = maxH + 1;
        return treeHeight[vertex];
    }

    private boolean isPredecessor(String w1, String w2) {
        if (w1.length() + 1 != w2.length())
            return false;
        int diff = 0;
        int w1i = 0;
        int w2i = 0;
        while(w1i < w1.length()) {
            if (w1.charAt(w1i) != w2.charAt(w2i)) {
                if (diff > 0)
                    return false;
                diff++;
            } else {
                w1i++;
            }
            w2i++;
        }

        return true;
    }

    public static void main(String[] args) {
        Problem1048 app = new Problem1048();
        String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(app.longestStrChain(words));
    }
}
