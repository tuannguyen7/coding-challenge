package leetcode;

public class Problem151 {

    public String reverseWords(String s) {
        StringBuffer str = new StringBuffer(s);
        int prevPointer = 0;
        int curPointer = 0;
        int prevState = 0; // 0 initial, 1 char, 2 space
        while (curPointer < s.length()) {
            char c = s.charAt(curPointer);
            if (prevState == 0) {
                if (c == ' ') {
                    // do nothing
                } else {
                    str.setCharAt(prevPointer, c);
                    prevPointer++;
                }

            } else if (prevState == 1) {
                if (c == ' ') {
                    // do nothing
                    str.setCharAt(prevPointer, ' ');
                } else {
                    // char
                    str.setCharAt(prevPointer, c);
                }

                prevPointer++;
            } else if (prevState == 2) {
                if (c == ' ') {
                    // do nothing
                } else {
                    // char
                    str.setCharAt(prevPointer, c);
                    prevPointer++;
                }
            } else {

                throw new IllegalArgumentException("");
            }

            if (c == ' ') {
                prevState = 2;
            } else {
                prevState = 1;
            }
            curPointer++;
        }

        return str.substring(0, prevPointer).toString();
    }

    public static void main(String[] args) {


    }
}
