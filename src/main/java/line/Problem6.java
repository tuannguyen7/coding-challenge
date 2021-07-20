package line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Complexity: O(E) E is number of edges in the graph.
// The algorithm travels every edges in the graph at most 1 time.
public class Problem6 {
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numVertexes = Integer.parseInt(br.readLine().trim());
        List<Integer>[] adjList = new List[numVertexes];
        for (int i = 0; i < numVertexes; i++) {
            adjList[i] = new ArrayList<>();
        }
        while(true) {
            String line = br.readLine();
            if (line == null)
                break;
            String[] parts = line.split("\\s");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            adjList[from].add(to);
        }

        System.out.println(solve(numVertexes, adjList));
    }

    public static boolean solve(int numVertexes, List<Integer>[] adjList) {
        boolean[] visited = new boolean[numVertexes];
        Arrays.fill(visited, false);
        for (int vertex = 0; vertex < numVertexes; vertex++) {
            if (dfs(vertex, adjList, visited, new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    // DFS
    public static boolean dfs(int curNode, List<Integer>[] adjList, boolean[] visited, Set<Integer> recursionStack) {
        if (visited[curNode])
            return false;
        visited[curNode] = true;
        recursionStack.add(curNode);
        for (int adjVertex : adjList[curNode]) {
            if (recursionStack.contains(adjVertex))
                return true;
            if (visited[adjVertex])
                continue;
            if (dfs(adjVertex, adjList, visited, recursionStack))
                return true;
        }
        recursionStack.remove(curNode);
        return false;
    }
}
