package leetcode;

public class Problem67 {

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int extra = 0;
        while (aIndex >= 0 || bIndex >=0) {
            if (aIndex < 0) {
                int bChr = Character.getNumericValue(b.charAt(bIndex));
                result.append("" + (bChr + extra)%2);
                extra = (bChr + extra)/2;
            } else if (bIndex < 0) {
                int aChr = Character.getNumericValue(a.charAt(aIndex));
                result.append("" + (aChr + extra)%2);
                extra = (aChr + extra)/2;
            } else {
                int aChr = Character.getNumericValue(a.charAt(aIndex));
                int bChr = Character.getNumericValue(b.charAt(bIndex));
                result.append("" + (aChr + bChr + extra)%2);
                extra = (aChr + bChr + extra)/2;
            }
            aIndex--;
            bIndex--;
        }
        if (extra == 1) {
            result.append("1");
        }
        return result.reverse().toString();
    }
}
