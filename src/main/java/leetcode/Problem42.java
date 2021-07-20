package leetcode;

public class Problem42 {

    public int trap(int[] height) {
        int trap = 0;
        for (int i = 0; i < height.length - 1;) {
            if (height[i] <= height[i+1]) {
                i++;
                continue;
            }
            int leftIndex = i;
            int rightIndex = i;
            int curMaxRight = height[i+1];

            // find the maximum index that h[mi] > h[i+1]
            for (int j = i + 2; j < height.length; j++) {
                if (height[j] >= height[i]) {
                    rightIndex = j;
                    break;
                }
                if (height[j] > curMaxRight) {
                    curMaxRight = height[j];
                    rightIndex = j;
                }
            }
            // not found appropriate right index
            if (rightIndex == i) {
                i++;
                continue;
            }
            int minH = Math.min(height[leftIndex], height[rightIndex]);
            int length = rightIndex - leftIndex - 1;
            int totalTrap = minH * length;
            for (int k = i + 1; k < rightIndex; k++) {
                totalTrap -= height[k];
            }
            trap += totalTrap;
            i = rightIndex;
        }

        return trap;
    }

    public int[] maxIndexes(int[] hs) {
        int[] result = new int[hs.length];
        int curMaxIndex = hs.length - 1;
        for (int i = hs.length - 1; i >= 0; i--) {
            if (hs[i] >= hs[curMaxIndex]) {
                curMaxIndex = i;
            }
            result[i] = curMaxIndex;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights2 = {4,2,0,3,2,5};
        Problem42 solver = new Problem42();
        System.out.println(solver.trap(heights));
        System.out.println(solver.trap(heights2));
    }
}
