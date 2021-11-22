package leetcode;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Problem133 {

    // BFS
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Node[] copied = new Node[1000];
        Queue<Node> nodes = new LinkedList<>();
        Node root = copy(node);
        nodes.add(root);
        copied[node.val] = root;

        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();
            if (cur.neighbors == null || cur.neighbors.isEmpty())
                continue;

            List<Node> neighbors = new ArrayList<>();
            for (Node neighbor: cur.neighbors) {
                if (copied[neighbor.val] == null) {
                    neighbor = copy(neighbor);
                    nodes.add(neighbor);
                    copied[neighbor.val] = neighbor;
                }
                neighbors.add(copied[neighbor.val]);
            }
            cur.neighbors = neighbors;
        }

        return root;
    }

    private Node copy(Node node) {
        Node newN = new Node(node.val);
        newN.neighbors = node.neighbors;
        return newN;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.neighbors = Arrays.asList(n2);
        n2.neighbors = Arrays.asList(n1);

        var app = new Problem133();
        Node clone = app.cloneGraph(n1);
        clone.hashCode();
    }
}
