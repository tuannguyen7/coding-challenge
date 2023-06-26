package spoj.classical;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ONP {

    public static class Node {
        public LinkedList<Token> tokens;
        public Node parent;

        public Node(LinkedList<Token> tokens, Node parent) {
            this.tokens = tokens;
            this.parent = parent;
        }

        public boolean isRoot() {
            return parent == null;
        }
    }
    public interface Token {

    }
    public static abstract class Operand implements Token {
        public abstract String expr();

        public String toString() {
            return expr();
        }
    }

    public static class OpenBracket implements Token {

    }

    public static class CloseBracket implements Token {

    }

    public static class Operator implements Token {
        public char c;
        public int priority;

        public Operator(char c) {
            this.c = c;
            if (c == '+') {
                priority = 1;
            } else if (c == '-') {
                priority = 1;
            } else if (c == '*') {
                priority = 2;
            } else if (c == '/'){
                priority = 2;
            } else if (c == '^') {
                priority = 3;
            } else {
                throw new UnsupportedOperationException(String.valueOf(c));
            }
        }

        public ExprOperand apply(Operand l, Operand r) {
            return new ExprOperand(l.expr() + r.expr() + c); // postfix
        }

        @Override
        public String toString() {
            return String.valueOf(c);
        }
    }

    public static class SimpleOperand extends Operand {
        public char c;
        public SimpleOperand(char c) {
            this.c = c;
        }

        @Override
        public String expr() {
            return String.valueOf(c);
        }
    }

    public static class ExprOperand extends Operand {
        public String expr;
        public ExprOperand(String expr) {
            this.expr = expr;
        }

        @Override
        public String expr() {
            return expr;
        }
    }

    public LinkedList<Token> resolveByPriority(LinkedList<Token> tokens, int priority) {
        LinkedList<Token> newTokens = new LinkedList<>();
        Operator curOperator = null;

        while (!tokens.isEmpty()) {
            var curToken = tokens.removeFirst();
            if (curToken instanceof Operand) {
                if (curOperator == null) {
                    // this means curToken is lhs operand, add to the end of the list
                    newTokens.add(curToken);
                } else {
                    // if curOperator != null, curToken is rhs operand, get the last operand from the list then apply curOperator
                    var leftOperand = (Operand)newTokens.removeLast(); // must be operand
                    newTokens.add(curOperator.apply(leftOperand, (Operand)curToken));
                }
            } else if (curToken instanceof Operator) {
                var operator = (Operator)curToken;
                if (operator.priority < priority) {
                    // This means the current position is the end of a sub expression that needs to be resolved
                    newTokens.add(operator);
                    // reset
                    curOperator = null;
                } else if (operator.priority == priority){
                    // found the operator that needs to be resolved
                    curOperator = operator;
                } else {
                    // never reach this branch
                    throw new RuntimeException("should not reach this branch");
                }
            } else {
                // never reach this branch
                throw new RuntimeException("should not reach this branch");
            }
        }

        return newTokens;
    }

    private LinkedList<LinkedList<Token>> readInput() {
        FastReader reader = new FastReader(System.in);
        int numTest = reader.nextInt();
        LinkedList<LinkedList<Token>> tokensList = new LinkedList<>();
        while (numTest-- > 0) {
            String s = reader.next();
            if (!s.startsWith("(")) {
                s = "(" + s + ")";
            }
            LinkedList<Token> tokens = new LinkedList<>();
            for (char c : s.toCharArray()) {
                switch (c) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                        var op = new Operator(c);
                        tokens.add(op);
                        break;
                    case '(':
                        tokens.add(new OpenBracket());
                        break;
                    case ')':
                        tokens.add(new CloseBracket());
                        break;
                    case ' ':
                        break;
                    default:
                        tokens.add(new SimpleOperand(c));
                        break;
                }
            }
            tokensList.add(tokens);
        }
        return tokensList;
    }

    public LinkedList<Token> resolve(LinkedList<Token> tokens) {
        int priority = 3;
        while (priority > 0) {
            tokens = resolveByPriority(tokens, priority);
            //printSol(tokens);
            priority--;
        }

        return tokens;
    }

    public LinkedList<Token> resolveBrackets(LinkedList<Token> tokens) {
        LinkedList<Token> finalTokens = new LinkedList<>();
        Node curNode = null;
        for (var token: tokens) {
            // open => new token list
            // close => stop current token list
            //  => resolve cur tokens
            //  put into outer
            if (token instanceof OpenBracket) {
                var child = new Node(new LinkedList<>(), curNode);
                curNode = child;
            } else if (token instanceof CloseBracket) {
                var rpnTokens = resolve(curNode.tokens);
                if (!curNode.isRoot()) {
                    curNode = curNode.parent;
                    curNode.tokens.addAll(rpnTokens);
                } else {
                    curNode = curNode.parent;
                    finalTokens.addAll(rpnTokens);
                }
            } else {
                curNode.tokens.add(token);
            }
        }

        return resolve(finalTokens);
    }

    public void solve() {
        var tokensList = readInput();
        for (var tokens : tokensList) {
            tokens = resolveBrackets(tokens);
            printSol(tokens);
        }
    }

    public void printSol(LinkedList<Token> stack) {
        StringBuilder s = new StringBuilder();
        stack.forEach(item -> s.append(item.toString()));
        System.out.println(s);
    }

    public static void main(String[] args) {
        var sol = new ONP();
        sol.solve();
    }

    // IO
    static class FastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        private int lenbuf = 0, ptrbuf = 0;

        public FastReader(InputStream is) {
            this.is = is;
        }

        public int readByte() {
            if (lenbuf == -1) throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        public boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        public int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b)) ;
            return b;
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String nextLine() {
            int b = readByte();
            StringBuilder sb = new StringBuilder();
            while (b != '\n' || b != '\r') {
                sb.appendCodePoint(b);
            }
            return sb.toString();
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = (num << 3) + (num << 1) + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = (num << 3) + (num << 1) + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] next(int n) {
            char[] buf = new char[n];
            int b = skip(), p = 0;
            while (p < n && !(isSpaceChar(b))) {
                buf[p++] = (char) b;
                b = readByte();
            }
            return n == p ? buf : Arrays.copyOf(buf, p);
        }

        public char nextChar() {
            return (char) skip();
        }
    }
}