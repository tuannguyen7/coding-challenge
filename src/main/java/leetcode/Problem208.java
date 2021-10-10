package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem208 {
    class Trie {

        Node root;

        public Trie() {

        }

        public void insert(String word) {
            word += "$";
            Node curParent = root;
            for (int i = 0; i < word.length(); i++) {
                if (curParent.children == null) {
                    curParent.children = new HashMap<>();
                }
                Node child = curParent.getChild(word.charAt(i));
                if (child == null) {
                    child = new Node(word.charAt(i));
                    curParent.addNode(child);
                }

                curParent = child;
            }
        }

        public boolean search(String word) {
            word += "$";
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                cur = cur.getChild(word.charAt(i));
                if (cur == null) {
                    return false;
                }
            }

            return cur.isLeaf();
        }

        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                cur = cur.getChild(prefix.charAt(i));
                if (cur == null) {
                    return false;
                }
            }

            return true;
        }
    }

    class Node {
        char ch;
        Map<Character, Node> children;

        public Node(char ch) {
            this.ch = ch;
        }

        public boolean has(char ch) {
            return children.containsKey(ch);
        }

        public void addNode(Node n) {
            children.put(n.ch, n);
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public boolean isLeaf() {
            return ch == '$';
        }
    }

    public static void main(String[] args) {

    }
}
