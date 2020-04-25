package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem22 {
    List<String> solution = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
//        generateParen(n, 0, n, new StringBuilder());
//        System.out.println(solution.size());
//        return solution;
        List<String> result = generateParen_2(n, 0, n, new StringBuilder());
        System.out.println(result.size());
        return result;
    }

    private void generateParen(int closeParenLeft, int openParenLeft, int openLeft, StringBuilder curStringBuilder) {
        if (closeParenLeft == 0) {
            solution.add(curStringBuilder.toString());
            return;
        }

        if (openParenLeft > 0) {
            generateParen(closeParenLeft - 1, openParenLeft - 1, openLeft, curStringBuilder.append(")"));
            curStringBuilder.deleteCharAt(curStringBuilder.length() - 1);
        }

        if (openLeft > 0) {
            generateParen(closeParenLeft, openParenLeft + 1, openLeft - 1, curStringBuilder.append("("));
            curStringBuilder.deleteCharAt(curStringBuilder.length() - 1);
        }
    }

    private List<String> generateParen_2(int closeParenLeft, int openParenLeft, int openLeft, StringBuilder curStringBuilder) {
        if (closeParenLeft == 0) {
            return Arrays.asList(curStringBuilder.toString());
        }

        List<String> result = new LinkedList<>();
        if (openParenLeft > 0) {
            result.addAll(generateParen_2(closeParenLeft - 1, openParenLeft - 1, openLeft, curStringBuilder.append(")")));
            curStringBuilder.deleteCharAt(curStringBuilder.length() - 1);
        }

        if (openLeft > 0) {
            result.addAll(generateParen_2(closeParenLeft, openParenLeft + 1, openLeft - 1, curStringBuilder.append("(")));
            curStringBuilder.deleteCharAt(curStringBuilder.length() - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        new Problem22().generateParenthesis(3);
        new Problem22().generateParenthesis(4);
        new Problem22().generateParenthesis(5);
    }
}
