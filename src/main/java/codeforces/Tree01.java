package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Tree01 {

    static int globalCounter = 0;
    static class Node{
        int data;
        int globalCounter;
        Node previous;
        Node next;

        public Node(int data) {
            this.data = data;
            this.globalCounter = Tree01.globalCounter++;
        }
    }

    static class DoubleLinkedList<E> {

        //Represent the head and tail of the doubly linked list, virtual node
        private Node head;
        private Node tail;
        private volatile int size = 0;

        public DoubleLinkedList() {
            head = new Node(-99);
            tail = new Node(-100);
            head.next = tail;
            tail.previous = head;
        }

        public int first() {
            return head.next.data;
        }

        // append new node after node n
        public void add(Node n, int data) {
            Node newNode = new Node(data);
            newNode.next = n.next;
            n.next = newNode;
            newNode.previous = n;
            newNode.next.previous = newNode;
            size++;
        }

        public void add(int data) {
            add(tail.previous, data);
        }

        public void removeNode(Node n) {
            Node prev = n.previous;
            Node next = n.next;
            prev.next = next;
            next.previous = prev;
            n.next = null;
            n.previous = null;
            size--;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void solve2(int[] nodesArr) {
        DoubleLinkedList nodes = new DoubleLinkedList();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o2.data != o1.data) {
                    return o2.data - o1.data;
                }

                if (o1.globalCounter > o2.globalCounter) return 1;
                return -1;
            }
        });

        for (int n : nodesArr) {
            nodes.add(n);
        }

        Node cur = nodes.head.next;
        while (cur.data != -100) {
            priorityQueue.add(cur);
            cur = cur.next;
        }

        while (priorityQueue.size() > 1) {
            Queue<Node> curProcessingNodes = new LinkedList<>();
            Stack<Node> notYetSolved = new Stack<>();
            int curPeekData = priorityQueue.peek().data;
            boolean shouldGetFromNotYetSolved = false;

            while (priorityQueue.size() > 1 && priorityQueue.peek().data == curPeekData) {
                curProcessingNodes.add(priorityQueue.poll());
            }

            while (!curProcessingNodes.isEmpty() || !notYetSolved.isEmpty()) {
                Node curNode;
                boolean fromNotYetSolved = false;
                if (shouldGetFromNotYetSolved && !notYetSolved.isEmpty()) {
                    curNode = notYetSolved.pop();
                    fromNotYetSolved = true;
                } else {
                    curNode = curProcessingNodes.poll();
                }

                if (curNode == null) {
                    System.out.println("NO");
                    return;
                }
                Node chosenNode = null;

                Node next = curNode.next; // next and prev are never null
                Node prev = curNode.previous;
                if ((curNode.data - next.data) == 1) {
                    chosenNode = next;
                } else if ((curNode.data - prev.data) == 1) {
                    chosenNode = prev;
                }

                if (chosenNode != null) {
                    nodes.removeNode(curNode);
                    shouldGetFromNotYetSolved = true;
                } else {
                    if (fromNotYetSolved) {
                        // cannot solve
                        System.out.println("NO");
                        return;
                    }
                    curNode.globalCounter = globalCounter++;
                    notYetSolved.push(curNode);
                    shouldGetFromNotYetSolved = false;
                }
            }
        }

        if (priorityQueue.peek().data == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int T = reader.nextInt();

        for (int i = 0; i < T; i++) {
            int size = reader.nextInt();
            int[] nodes = new int[size];
            for (int s = 0; s < size; s++)
                nodes[s] = reader.nextInt();
            // Algorithm
            solve2(nodes);
        }
    }

    public static void main(String[] args) {
        solution();
    }

    static class FastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        private int lenbuf = 0, ptrbuf = 0;

        public FastReader(InputStream is) {
            this.is = is;
        }

        public int readByte(){
            if(lenbuf == -1)throw new InputMismatchException();
            if(ptrbuf >= lenbuf){
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if(lenbuf <= 0)
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
        public String next(){
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while(!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }
        public String nextLine(){
            int b = readByte();
            StringBuilder sb = new StringBuilder();
            while(b != '\n' || b != '\r'){
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
        public char[] next(int n){
            char[] buf = new char[n];
            int b = skip(), p = 0;
            while (p < n && !(isSpaceChar(b))) {
                buf[p++] = (char) b;
                b = readByte();
            }
            return n == p ? buf : Arrays.copyOf(buf, p);
        }
        public char nextChar () {
            return (char) skip();
        }
    }
}
