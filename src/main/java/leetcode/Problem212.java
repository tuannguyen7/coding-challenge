package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem212 {

    private static int[][] m = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static class Node {
        public char letter;
        public Map<Character, Node> children;

        public Node(char letter) {
            this.letter = letter;
        }

        public Node(char letter, Map<Character, Node> children) {
            this.letter = letter;
            this.children = children;
        }

        public static Node NewLeaf() {
            return new Node('$', Collections.emptyMap());
        }

        public static Node NewRoot() {
            return new Node('^', new HashMap<>());
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public boolean endOfWord() {
            return children.containsKey('$');
        }

        public boolean isLeaf() {
            return letter == '$';
        }

        public boolean isRoot() {
            return letter == '^';
        }

        public void addChild(Node child) {
            children.put(child.letter, child);
        }

        public Node getChild(char letter) {
            return children.get(letter);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        // words tree
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                visited[i][j] = false;
        }


        Node wordsNode = wordTrees(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Node node = wordsNode.getChild(board[i][j]);
                if (node == null) continue;
                visited[i][j] = true;
                findWordStartIn(board, i, j, visited, node, new StringBuilder(), result);
                visited[i][j] = false;
            }
        }
        return result.stream().collect(Collectors.toList());
    }

    private void findWordStartIn(char[][] board, int x, int y, boolean[][] visited, Node wordNode, StringBuilder curString, Set<String> result) {
        curString.append(wordNode.letter);
        if (wordNode.endOfWord()) {
            result.add(curString.toString());
        }

        for (int i = 0; i < m.length; i++) {
            int newX = x + m[i][0];
            int newY = y + m[i][1];
            if (canVisit(visited, newX, newY)) {
                Node nextNode = wordNode.getChild(board[newX][newY]);
                if (nextNode != null) {
                    visited[newX][newY] = true;
                    findWordStartIn(board, newX, newY, visited, nextNode, curString, result);
                    visited[newX][newY] = false;
                }
            }
        }
        curString.setLength(curString.length() - 1);
    }

    private Node wordTrees(String[] words) {
        Node wordsNode = Node.NewRoot();
        for (int i = 0; i < words.length; i++) {
            Node child = constructTree(words[i], 0, wordsNode);
            wordsNode.addChild(child);
        }
        return wordsNode;
    }

    public Node constructTree(String s, int index, Node parent) {
        Node curNode = parent.getChild(s.charAt(index));
        if (curNode == null) {
            curNode = new Node(s.charAt(index), new HashMap<>());
        }

        if (index < s.length() - 1) {
            Node child = constructTree(s, index + 1, curNode);
            curNode.addChild(child);
        } else {
            curNode.addChild(Node.NewLeaf());
        }
        return curNode;
    }

    private boolean canVisit(boolean[][] visited, int x, int y) {
        if (x < 0) return false;
        if (x >= visited.length) return false;
        if (y < 0) return false;
        if (y >= visited[0].length) return false;
        return !visited[x][y];
    }

    public static void main(String[] args) {
        char[][] board = {{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'}};
        String[] words = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        //char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        //String[] words = {"oath","pea","eat","rain"};
        List<String> matchWords = new Problem212().findWords(board, words);
        System.out.println(matchWords);
    }
}
