package leetcode;

import java.util.*;

public class Problem785 {

    boolean[] visited;
    Queue<Integer> leftQueue = new ArrayDeque<>();
    Queue<Integer> rightQueue = new ArrayDeque<>();
    Stack<Integer> leftStack = new Stack<>();
    Stack<Integer> rightStack = new Stack<>();
    boolean[] leftSet;
    boolean[] rightSet;
    int curVisitedIndex = 0;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        leftSet = new boolean[graph.length];
        rightSet = new boolean[graph.length];
        Arrays.fill(visited, false);
        Arrays.fill(leftSet, false);
        Arrays.fill(rightSet, false);

        int nextNotVisited = getNextNotVisited();
        while (nextNotVisited != -1) {
            leftQueue.add(nextNotVisited); // not important left or right
            leftSet[nextNotVisited] = true;
            while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
                while (!leftQueue.isEmpty()) {
                    if (check(leftQueue.poll(), graph, visited, rightQueue, rightSet, leftSet))
                        return false;
                }

                while (!rightQueue.isEmpty()) {

                    if (check(rightQueue.poll(), graph, visited, leftQueue, leftSet, rightSet))
                        return false;
                }
            }
            nextNotVisited = getNextNotVisited();
        }

        return true;
    }

    private boolean check(int vertex,
                          int[][] graph,
                          boolean[] visited,
                          Queue<Integer> oppositeQueue,
                          boolean[] oppositeSet,
                          boolean[] curSet) {

        int[] adjacency = graph[vertex];
        visited[vertex] = true;
        for (int adj : adjacency) {
            if (visited[adj])
                continue;
            if (curSet[adj])
                return true;       // not bipartite
            oppositeSet[adj] = true;
            oppositeQueue.add(adj);
        }

        return false;
    }

    private int getNextNotVisited() {
        for (; curVisitedIndex < visited.length; curVisitedIndex++) {
            if (!visited[curVisitedIndex])
                return curVisitedIndex;
        }

        return -1;
    }

  public static void main(String[] args) {
    int[][] graph = {{1,3}, {0,2}, {1,3}, {0,2}};
    //   int[][] graph = {{1, 2,3}, {0,2}, {0, 1,3}, {0,2}};
    System.out.println(new Problem785().isBipartite(graph));
  }
}
