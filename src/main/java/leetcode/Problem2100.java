package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem2100 {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        int lIndex = 0;
        int curTime = 0;

        outter:
        while (lIndex < security.length - 2*time) {
            for (; curTime < time; curTime++) {
                if (security[lIndex + curTime] < security[lIndex + curTime + 1]) {
                    lIndex += curTime + 1;
                    curTime = 0;
                    continue outter;
                }
            }

            // found lIndex
            int midIndex = lIndex + time;
            for (int j = 0; j < time; j++) {
                if (security[midIndex + j] > security[midIndex + j + 1]) {
                    lIndex = lIndex + 1;
                    curTime--;
                    continue outter;
                }
            }
            ans.add(midIndex);
            int rIndex = midIndex + time;
            while (rIndex < security.length - 1) {
                if (security[midIndex] < security[midIndex + 1] || security[rIndex] > security[rIndex+1]) {
                    break;
                }
                midIndex++;
                rIndex++;
                ans.add(midIndex);
            }
            lIndex = midIndex + 1;
            curTime = 0;
        }

        return ans;
    }

    public static void main(String[] args) {
        var app = new Problem2100();
        //int[] securities = {5,3,3,3,5,6,2};
        //int time = 2;
        //int[] securities = {1,1,1,1,1};
        //int time = 0;
        //int[] securities = {1,2,3,4,5,6};
        //int time = 2;
        //int[] securities = {4, 3, 2, 1};
        //int time = 1;
        int[] securities = {1,2,5,4,1,0,2,4,5,3,1,2,4,3,2,4,8};
        int time = 2;
        app.goodDaysToRobBank(securities, time);
    }
}
