package leetcode;

public class Problem4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int totalLen = nums1.length + nums2.length;
        int k1 = totalLen/2;
        int k2 = (totalLen - 1)/2;

        if (nums1.length == 0) {
            return ((double)nums2[k1] + (double)nums2[k2])/2;
        }

        if (nums2.length == 0) {
            return ((double)nums1[k1] + (double)nums1[k2])/2;
        }

        double n1 = findKthElement(nums1, nums2, k1);
        double n2 = findKthElement(nums1, nums2, k2);
        System.out.println("" + n1 + ", " + n2);
        return (n1 + n2)/2;
    }

    public int findKthElement(int[] nums1, int[] nums2, int kth) {
        int l1 = 0;
        int h1 = nums1.length - 1;
        int l2 = 0;
        int h2 = nums2.length - 1;
        boolean toggle = false; //
        while (l1 <= h1 && l2 <= h2) {
            int upperBound;
            if (toggle) {
                int m1 = l1 + (h1 - l1)/2;
                upperBound = upperBound(nums2, l2, h2, nums1[m1]); // có upperbound phần từ phía nums[2] <= nums1[h1]
                if (upperBound + m1 == kth)
                    return nums1[m1];
                if (upperBound + m1 < kth) {
                    l1 = m1 + 1;
                    l2 = upperBound;
                } else {
                    h1 = m1 - 1;
                    h2 = upperBound - 1;
                }
            } else {
                int m2 = l2 + (h2 - l2)/2;
                upperBound = upperBound(nums1, l1, h1, nums2[m2]); // có upperbound phần từ phía nums[2] <= nums1[h1]
                if (upperBound + m2 == kth)
                    return nums2[m2];
                if (upperBound + m2 < kth) {
                    l2 = m2 + 1;
                    l1 = upperBound;
                } else {
                    h2 = m2 - 1;
                    h1 = upperBound - 1;
                }
            }

            toggle ^= true;
        }

        if (h1 < l1) { // kth in nums2
            int upperBound = upperBound(nums1, 0, nums1.length - 1, nums2[l2]);
            int kthIndex = kth - upperBound > l2 ? kth - upperBound : l2;
            return nums2[kthIndex];
        } else { // kth in nums1
            int upperBound = upperBound(nums2, 0, nums2.length - 1, nums1[l1]);
            int kthIndex = kth - upperBound > l1 ? kth - upperBound : l1;
            return nums1[kthIndex];
        }
    }

    // Find the first index that value >= target
    public int lowerBound(int[] nums, int start, int end, int target) {
        int lo = start;
        int hi = end;
        int ans = hi + 1; // stricky
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (nums[mid] >= target) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    public int upperBound(int[] array, int low, int high, int value) {
        high += 1;
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value >= array[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {2,3,4};
        new Problem4().findMedianSortedArrays(nums1, nums2);
    }
}
