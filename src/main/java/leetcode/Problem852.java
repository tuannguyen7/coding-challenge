package leetcode;

public class Problem852 {
    public int peakIndexInMountainArray(int[] A) {
        int lo = 0;
        int hi = A.length - 1;
        int ans = lo;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (A[mid] >= A[mid-1]) {
                lo = mid + 1;
                ans = mid;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {0,2,1,0};
        int index = new Problem852().peakIndexInMountainArray(A);
        System.out.println(index);
    }
}
