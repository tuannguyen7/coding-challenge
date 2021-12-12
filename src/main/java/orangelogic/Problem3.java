package orangelogic;

import java.util.HashMap;
import java.util.Map;

public class Problem3 {

    public int solution(int[] A) {
        int[] segments = new int[A.length - 1];
        for (int i = 0; i < A.length - 1; i++) {
            segments[i] = A[i] + A[i+1];
        }

        Map<Integer, Integer> prevSegments = new HashMap<>(); // key: segment, value: segment count
        Map<Integer, Integer> pPrevSegments = new HashMap<>();

        for (int i = 0; i < segments.length; i++) {
            Map<Integer, Integer> curSegment = new HashMap<>();
            curSegment.putAll(prevSegments);
            int count = pPrevSegments.getOrDefault(segments[i], 0); // we can't take prevSegment because it may intersect with current segment
            curSegment.put(segments[i], count + 1);
            pPrevSegments = prevSegments;
            prevSegments = curSegment;
        }

        return getSegmentMaxCount(prevSegments);
    }

    private int getSegmentMaxCount(Map<Integer, Integer> segments) {
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : segments.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        var app = new Problem3();
        //int[] A = {10, 1, 3, 1, 2, 2, 1, 0, 4};
        //int[] A = {9, 9, 9, 9, 9};
        //int[] A = {1, 5, 2, 4, 3, 3};
        int[] A = {1, 5, 1, 5};
        System.out.println(app.solution(A));
    }
}
