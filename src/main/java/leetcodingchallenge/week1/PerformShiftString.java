package leetcodingchallenge.week1;

public class PerformShiftString {
    public String stringShift(String s, int[][] shift) {
        int shiftRight = 0;
        for (int[] sTmp : shift) {
            shiftRight += sTmp[0] == 0 ? -sTmp[1] : sTmp[1];
        }
        shiftRight %= s.length();
        shiftRight = shiftRight < 0 ? s.length() + shiftRight : shiftRight;
        StringBuilder sAfterShifting = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int index = i - shiftRight < 0 ? i - shiftRight + s.length() : i - shiftRight;
            sAfterShifting.append(s.charAt(index));
        }
        return sAfterShifting.toString();
    }

    public static void main(String[] args) {
        String s = "abc";
        int[][] shift = {{0, 1}, {1, 2}};
        System.out.println(new PerformShiftString().stringShift(s, shift));
    }
}
