package leetcode;

import java.util.*;

public class FirstUnique {

    Node head;
    Node tail;
    Map<Integer, Node> elements;

    public FirstUnique(int[] nums) {
        elements = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        for (var num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        return head.next != tail ? head.next.value : -1;
    }

    public void add(int value) {
        if (elements.containsKey(value)) {
            Node n = elements.get(value);
            if (n.prev != null) {
                n.prev.next = n.next;
            }
            if (n.next != null) {
                n.next.prev = n.prev;
            }
            n.next = null;
            n.prev = null;
        } else {
            Node n = new Node(value);
            tail.prev.next = n;
            n.prev = tail.prev;
            n.next = tail;
            tail.prev = n;
            elements.put(value, n);
        }
    }

    static class Node {
        int value;
        Node prev;
        Node next;
        public Node() {}

        public Node(int value) {
            this.value = value;
        }
    }
}
