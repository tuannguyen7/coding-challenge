package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem839 {

    public int numSimilarGroups(String[] strs) {
        int[] groups = new int[strs.length];
        for (int i = 0; i < groups.length; i++)
            groups[i] = i;

        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int ri = findRoot(groups, i);
                    int rj = findRoot(groups, j);
                    groups[ri] = groups[rj];
                }
            }
        }
        for (int i = 0; i < groups.length; i++) {
            groups[i] = findRoot(groups, i);
        }

        Set<Integer> diffGroups = new HashSet<>();
        for (int i = 0; i < groups.length; i++)
            diffGroups.add(groups[i]);
        return diffGroups.size();
    }

    // find including path compression
    public int findRoot(int[] groups, int index) {
        if (groups[index] == index)
            return index;
        var root = findRoot(groups, groups[index]);
        groups[index] = root;
        return root;
    }

    public boolean isSimilar(String a, String b) {
        int firstIndexDiff = -1;
        int secondIndexDiff = -1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (firstIndexDiff == -1) {
                    firstIndexDiff = i;
                } else if (secondIndexDiff == -1) {
                    secondIndexDiff = i;
                } else {
                    return false;
                }
            }
        }

        if (firstIndexDiff == -1) {
            return true;
        }
        if (secondIndexDiff == -1) {
            return false;
        }
        return a.charAt(firstIndexDiff) == b.charAt(secondIndexDiff) && a.charAt(secondIndexDiff) == b.charAt(firstIndexDiff);
    }

    public static void main(String[] args) {

        var sol = new Problem839();
        String[] strs = {"blw","bwl","wlb"};
        System.out.println(sol.numSimilarGroups(strs));
    }
}
