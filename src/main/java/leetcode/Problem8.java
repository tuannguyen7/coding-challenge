package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem8 {

    // 10 -> stop
    // 11 -> next
    // 12 -> require number
    // 13 -> first char
    // 14 -> first number
    public int myAtoi(String s) {
        List<Integer> numbers = new ArrayList<>();
        int state = 11;
        boolean negative = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (state == 10) {
                break;
            } else if (state == 11) {
                state = firstChar(c);
            } else if (state == 12) {
                state = firstNumber(c);
            } else if (state == 13) {
                negative = true;
                state = firstNumber(c);
            } else if (state == 14) {
                state = firstNumber(c);
            } else {
                numbers.add(state);
                state = requireNumber(c);
            }
        }
        if (state < 10) {
            numbers.add(state);
        }

        long value = 0;
        int counter = 0;
        if (numbers.size() > 10) {
            // exceed max integer
            if (negative) {
                value = Integer.MIN_VALUE;
            } else {
                value = Integer.MAX_VALUE;
            }
        } else {
            for (int i = numbers.size() - 1; i >= 0; i--) {
                value += numbers.get(i) * (long)Math.pow(10, counter);
                counter++;
            }

            if (negative) {
                value = value * -1;
            }
        }

        if (value > Integer.MAX_VALUE) value = Integer.MAX_VALUE;
        if (value < Integer.MIN_VALUE) value = Integer.MIN_VALUE;
        return (int)value;
    }

    boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    int firstChar(char c) {
        if (c == '0') { // if first number is 0 we can ignore
            return 14;
        }
        if (isNumber(c)) {
            return c - '0';
        }
        if (c == ' ') {
            return 11;
        }
        if (c == '+') {
            return 12;
        }
        if (c == '-') {
            return 13;
        }
        return 10;
    }

    int firstNumber(char c) {
        if (c == '0') {
            return 14;
        }
        if (isNumber(c)) {
            return c - '0';
        }
        return 10;
    }

    int requireNumber(char c) {
        if (isNumber(c)) {
            return c - '0';
        } else {
            return 10;
        }
    }

    public static void main(String[] args) {
        Problem8 app = new Problem8();
        //System.out.println(app.myAtoi("-42"));
        //System.out.println(app.myAtoi("42"));
        System.out.println(app.myAtoi("  0000000000012345678"));
    }
}
