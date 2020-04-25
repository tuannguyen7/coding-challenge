package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem77 {

    public List<List<Integer>> combine(int n, int k) {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        return combine(k, visited, new LinkedList<>());
    }

    public List<List<Integer>> combine(int k, boolean[] visited, LinkedList<Integer> curSol) {
        if (curSol.size() == k) {
            return Arrays.asList(curSol.stream().collect(Collectors.toList()));
        }

        int i = curSol.size() > 0 ? curSol.getLast() - 1 : 0;
        List<List<Integer>> sols = new ArrayList<>();
        for (; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curSol.add(i + 1);
                sols.addAll(combine(k, visited, curSol));
                visited[i] = false;
                curSol.removeLast();
            }
        }
        return sols;
    }

  public static void main(String[] args) {
    //
  }
}
