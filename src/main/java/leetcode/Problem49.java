package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        return IntStream.range(0, strs.length)
            .boxed()
            .map(
                index -> {
                  char[] chars = strs[index].toCharArray();
                  Arrays.sort(chars);
                  return new Pair(index, new String(chars));
                })
            .collect(Collectors.groupingBy(Pair::getStr, Collectors.toList()))
                .values()
                .stream()
                .map(t -> t.stream().map(pair -> strs[pair.index]).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static class Pair {
        int index;
        String str;

        public int getIndex() {
            return index;
        }

        public String getStr() {
            return str;
        }

        public Pair(int index, String str) {
            this.index = index;
            this.str = str;
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = new Problem49().groupAnagrams(strs);
        System.out.println(result.size());
    }
}
