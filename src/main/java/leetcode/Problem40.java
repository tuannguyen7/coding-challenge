package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem40 {
    List<List<Integer>> solutions = new LinkedList<>();
    Map<Integer, Integer> count = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[] distinctCandidates = Arrays.stream(candidates)
                .distinct()
                .toArray();

        for (int candidate : candidates) {
            if (!count.containsKey(candidate)) {
                count.put(candidate, 0);
            }
            count.put(candidate, count.get(candidate) + 1);
        }

        _try(distinctCandidates, target, 0, 0, new LinkedList<>());
        return solutions;
    }

    public void _try(int[] candidates, int target, int curIndex, int curTarget, LinkedList<Integer> curSol) {
        if (curTarget == target) {
            solutions.add(curSol.stream().collect(Collectors.toList()));
            return;
        }

        if (curTarget > target || curIndex == candidates.length)
            return;

        int maxMul = count.get(candidates[curIndex]);
        for (int mul = 0; mul <= maxMul; mul++) {
            for (int i = 0; i < mul; i++)
                curSol.add(candidates[curIndex]);
            _try(candidates, target, curIndex + 1, curTarget + candidates[curIndex]*mul, curSol);
            for (int i = 0; i < mul; i++)
                curSol.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        new Problem40().combinationSum2(candidates, 8);
    }
}
