package leetcode;

import java.util.*;

public class Problem310 {

    // inprogress
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        return solution1(n, edges);
    }

    // Solution 1: TLE
    public List<Integer> solution1(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int curMinHeight = 99999;
        List<Integer> heads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int height = visit(i, adj, visited);
            if (height < curMinHeight) {
                heads = new ArrayList<>();
                heads.add(i);
                curMinHeight = height;
            } else if (height == curMinHeight) {
                heads.add(i);
            }
        }
        return heads;
    }

    int visit(int node, List<Integer>[] adj, boolean[] visited) {
        if (visited[node])
            return 0;
        int max = 0;
        visited[node] = true;
        for (int adjNode : adj[node]) {
            max = Math.max(visit(adjNode, adj, visited), max);
        }
        return max + 1;
    }
}
