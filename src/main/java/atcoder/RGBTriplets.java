package atcoder;

import java.util.*;
import java.util.stream.Collectors;

public class RGBTriplets {

    public long count(String s) {
        List<Integer> RIndexes = new ArrayList<>();
        List<Integer> GIndexes = new ArrayList<>();
        List<Integer> BIndexes = new ArrayList<>();

        int index = 0;
        for (char c : s.toCharArray()) {
            if (c == 'R')
                RIndexes.add(index);
            else if (c == 'G')
                GIndexes.add(index);
            else
                BIndexes.add(index);
            index++;
        }

        long count = RIndexes.size() * GIndexes.size() * BIndexes.size();
        // tiếp tục trừ đi những phần tử j - i == k - j

        Set<Integer> setB = new HashSet<>(BIndexes);
        int excludedCount = 0;
        for (int rIndex : RIndexes)
            for (int gIndex : GIndexes) {
                int diff = Math.abs(gIndex - rIndex);
                int higher = rIndex > gIndex ? rIndex : gIndex;
                int lower = rIndex > gIndex ? gIndex : rIndex;
                if (setB.contains(higher + diff))
                    excludedCount++;
                if (setB.contains(lower - diff))
                    excludedCount++;
            }

        return count - excludedCount;
    }

    public long countExcluded(String s) {
        int excludedCount = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {

            }
        }
        return excludedCount;
    }

    public boolean exist(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size() - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (arr.get(mid) == target)
                return true;
            if (arr.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public int lowerBound(List<Integer> arr, int start, int end, int target) {
        int ans = end + 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (arr.get(mid) >= target) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        //String s = scanner.next();
        String s = "RGBRGB";
        String s2 = "RBRBGRBGGBBRRGBBRRRBGGBRBGBRBGBRBBBGBBB";
        System.out.println(new RGBTriplets().count(s));
        //System.out.println(new RGBTriplets().count(s2));
    }
}
