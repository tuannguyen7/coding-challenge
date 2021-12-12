package leetcode;

import java.util.Arrays;

public class Problem567 {

    public boolean checkInclusion(String s1, String s2) {
        int[] charCounts = new int[127];
        int l = 0, r = 0;
        int charCount = s1.length();
        Arrays.fill(charCounts, -1);
        for (char c : s1.toCharArray()) {
            if (charCounts[c] == -1) {
                charCounts[c] = 0;
            }
            charCounts[c]++;
        }

        while (r < s2.length()) {
            char rChr = s2.charAt(r);
            if (charCounts[rChr] == -1) {
                for (int i = l; i < r; i++) {
                    charCounts[s2.charAt(i)]++;
                }
                r++;
                l = r;
                charCount = s1.length();
            } else if (charCounts[rChr] == 0) {
                char lChr = s2.charAt(l);
                charCount++;
                charCounts[lChr]++;
                l++;
            } else {
                charCount--;
                charCounts[rChr]--;
                if (charCount == 0) {
                    return true;
                }
                r++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        var app = new Problem567();
        String s1 = "ky";
        //String s2 = "ainwkckifykxlribaypk";
        String s2 = "kifykxlribaypk";
        System.out.println(app.checkInclusion(s1, s2));
    }
}
