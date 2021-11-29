package leetcode;

import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem797 {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>>[] memo = new ArrayList[15];
        List<List<Integer>> paths = dfs(graph, 0, graph.length - 1, memo);
        paths.forEach(path -> Collections.reverse(path));
        return paths;
    }

    public List<List<Integer>> dfs(int[][] graph, int curNode, int endNode, List<List<Integer>>[] memo) {
        if (curNode == endNode) {
            List<Integer> l = new ArrayList<>();
            l.add(curNode);
            return List.of(l);
        }
        if (memo[curNode] != null) {
            return memo[curNode];
        }
        List<List<Integer>> paths = new ArrayList<>();

        for (int nVertex : graph[curNode]) {
            List<List<Integer>> childPaths = dfs(graph, nVertex, endNode, memo);
            for (List<Integer> cPath : childPaths) {
                List<Integer> copy = new ArrayList<>(cPath);
                copy.add(curNode);
                paths.add(copy);
            }
        }

        memo[curNode] = paths;
        return paths;
    }

    public static void main(String[] args) {
        var app = new Problem797();
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        //int[][] graph = {{1,2},{3},{3},{}};
        var paths = app.allPathsSourceTarget(graph);
        LoggerUtil.INFO("{}", paths);
    }
}
