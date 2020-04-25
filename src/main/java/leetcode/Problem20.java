package leetcode;

import java.util.Set;
import java.util.Stack;

public class Problem20 {

    Stack<Character> parentheses = new Stack<>();
    Set<Character> openParentheses = Set.of('(', '{', '[');
    Set<Character> closeParentheses = Set.of(')', '}', ']');
    public boolean isValid(String s) {
        for (char c : s.toCharArray()) {
            if (openParentheses.contains(c))
                parentheses.push(c);
            else {
                if (!match(parentheses.pop(), c)) return false;
            }
        }
        return parentheses.isEmpty();
    }

    private boolean match(char openParen, char closeParen) {
        if ((openParen == '(' && closeParen == ')')
                || (openParen == '[' && closeParen == ']')
                || (openParen == '{' && closeParen == '}'))
            return true;
        return false;
    }
}
