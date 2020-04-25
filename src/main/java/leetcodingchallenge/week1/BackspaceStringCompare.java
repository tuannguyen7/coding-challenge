package leetcodingchallenge.week1;

public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {
        int sIndex = S.length() - 1;
        int tIndex = T.length() - 1;
        int sBPCount = 0;   // S backspace count
        int tBPCount = 0;   // T backspace count
        while (sIndex >=0 && tIndex >= 0) {
            char sChar = S.charAt(sIndex);
            char tChar = T.charAt(tIndex);

            if (sChar == '#' && tChar == '#') {
                sBPCount++;
                tBPCount++;
                sIndex--;
                tIndex--;
            } else if (sChar == '#') {
                sBPCount++;
                sIndex--;
            } else if (tChar == '#') {
                tBPCount++;
                tIndex--;
            } else {
                if (tBPCount == 0 && sBPCount == 0) {
                    if (sChar != tChar)
                        return false;
                    sIndex--;
                    tIndex--;
                } else {
                    if (sBPCount > 0) {
                        sBPCount--;
                        sIndex--;
                    }
                    if (tBPCount > 0) {
                        tBPCount--;
                        tIndex--;
                    }
                }
            }
        }

        for (;tIndex >= 0; tIndex--) {
            char tChar = T.charAt(tIndex);
            if (tChar == '#') tBPCount++;
            else if (tBPCount > 0) tBPCount--;
            else
                return false;
        }

        for (;sIndex >= 0; sIndex--) {
            char sChar = S.charAt(sIndex);
            if (sChar == '#') sBPCount++;
            else if (sBPCount > 0) sBPCount--;
            else
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
    }
}
