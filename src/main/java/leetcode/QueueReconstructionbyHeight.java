package leetcode;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * link: https://leetcode.com/problems/queue-reconstruction-by-height/
 * */
public class QueueReconstructionbyHeight {

    private static final Logger log = LoggerFactory.getLogger(QueueReconstructionbyHeight.class);

    public int[][] reconstructQueue(int[][] people) {
        List<int[]> ans = new ArrayList<>();
        sort(people);

        for (int i = 0; i < people.length; i++) {
            fixIndexOfPerson(ans, people[i]);
        }

        return ans.stream().toArray(int[][]::new);
    }

    public void sort(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[1] == o2[1])
                return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
    }

    public void fixIndexOfPerson(List<int[]> people, int[] person) {
        int count = 0;
        int len = people.size();
        int rightIndex = len;
        for (int i = 0; i < len; i++) {
            if (people.get(i)[0] >= person[0])
                count++;
            if (count > person[1]) {
                rightIndex = i;
                break;
            }
        }

        if (rightIndex != len) {
            people.add(rightIndex, person);
        } else {
            people.add(person);
        }
    }

    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        var result = new QueueReconstructionbyHeight().reconstructQueue(people);
        log.info("result: {}", (Object) result);
    }
}
