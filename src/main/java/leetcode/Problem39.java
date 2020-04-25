package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem39 {
    List<List<Integer>> solutions = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        _try(candidates, target, 0, 0, new LinkedList<>());
        return solutions;
    }

    public void _try(int[] candidates, int target, int curIndex, int curTarget, LinkedList<Integer> curSol) {
        if (curTarget == target) {
            solutions.add(curSol.stream().collect(Collectors.toList()));
            return;
        }

        if (curTarget > target || curIndex == candidates.length)
            return;

        int maxMul = (target - curTarget)/candidates[curIndex];
        for (int mul = 0; mul <= maxMul; mul++) {
            for (int i = 0; i < mul; i++)
                curSol.add(candidates[curIndex]);
            _try(candidates, target, curIndex + 1, curTarget + candidates[curIndex]*mul, curSol);
            for (int i = 0; i < mul; i++)
                curSol.removeLast();
        }
    }

  public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        new Problem39().combinationSum(candidates, 7);
  }
}
