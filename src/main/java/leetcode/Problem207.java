package leetcode;

import javax.swing.text.AsyncBoxView;
import java.util.*;

public class Problem207 {

    static class Node {
        int id;
        List<Node> neighbors;

        public Node(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        Node[] nodes = new Node[numCourses];
        Arrays.fill(visited, false);
        for (int[] prerequisite : prerequisites) {
            Node n = nodes[prerequisite[0]];
            if (n == null){
                n = new Node(prerequisite[0]);
                nodes[n.id] = n;
            }
            Node nb = nodes[prerequisite[1]];
            if (nb == null) {
                nb = new Node(prerequisite[1]);
                nodes[nb.id] = nb;
            }
            n.neighbors.add(nb);
        }

        for (Node n : nodes) {
            if (n == null || visited[n.id]) continue;;
            boolean[] parents = new boolean[numCourses];
            Arrays.fill(parents, false);
            if (dfs(n, parents, visited)) { // found a cycle
                return false;
            }
        }
        return true;
    }

    // return true has a cycle, return false otherwise
    private boolean dfs(Node n, boolean[] parents, boolean[] visited) {
        if (parents[n.id]) return true; // found a cycle
        if (visited[n.id]) return false; // visited return no cycle
        visited[n.id] = true;
        parents[n.id] = true;
        for (Node nb : n.neighbors) {
            if (dfs(nb, parents, visited)) {
                return true;
            }
        }
        parents[n.id] = false;
        return false;
    }

    public static void main(String[] args) {
        Problem207 app = new Problem207();

    }
}
