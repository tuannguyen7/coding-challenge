package leetcode;

public class Problem88 {

    // Solution1.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int rightIndex = nums1.length - 1;
        m -= 1;
        n -= 1;
        while(m >= 0 || n >= 0) {
            if (m < 0) {
                nums1[rightIndex] = nums2[n];
                n--;
            } else if (n < 0) {
                nums1[rightIndex] = nums1[m];
                m--;
            } else if (nums1[m] >= nums2[n]) {
                nums1[rightIndex] = nums1[m];
                m--;
            } else {
                nums1[rightIndex] = nums2[n];
                n--;
            }
            rightIndex--;
        }
    }

    // Solution2. A bit different with solution 1
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int rightIndex = nums1.length - 1;
        m -= 1;
        n -= 1;
        while(m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[rightIndex] = nums1[m];
                m--;
            } else {
                nums1[rightIndex] = nums2[n];
                n--;
            }
            rightIndex--;
        }
        // copy the rest from nums2 to nums1
        while (n >= 0) {
            nums1[rightIndex] = nums2[n];
            rightIndex--;
            n--;
        }
    }
}

