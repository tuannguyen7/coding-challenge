package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem211 {

    static class Node {
        Character c;
        Map<Character, Node> children;
        public Node(Character c) {
            this.c = c;
            children = new HashMap<>();
        }

        public void addStr(String s, int start) {
            if (start >= s.length())
                return;

            char c = s.charAt(start);
            if (children.containsKey(c)) {
                Node node = children.get(c);
                node.addStr(s, start + 1);
            } else {
                Node node = new Node(c);
                node.addStr(s, start + 1);
                children.put(c, node);
            }
        }

        public boolean search(String s, int start) {
            if (start >= s.length())
                // if this is leaf node -> true, else false
                return this.children.containsKey('$');
            char c = s.charAt(start);

            if (c == '.') {
                for (Node n : children.values()) {
                    if (n.search(s, start + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                Node n = children.get(c);
                if (n == null) {
                    return false;
                }
                return n.search(s, start + 1);
            }
        }
    }

    Node root = new Node(new Character('?'));

    public void addWord(String word) {
        root.addStr(word + "$", 0);
    }

    public boolean search(String word) {
        return root.search(word, 0);
    }
}
