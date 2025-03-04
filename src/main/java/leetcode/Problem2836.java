package leetcode;

import java.util.*;

public class Problem2836 {

    public class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public class Pair {
        int a;
        long b;
        public Pair(int a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public class CycleInfo {
        int head;
        int len;
        long[] sumInCycle;

        public CycleInfo(int node, int len, long[] sumInCycle) {
            this.head = node;
            this.len = len;
            this.sumInCycle = sumInCycle;
        }
    }

    Map<Integer, CycleInfo> cycleNodeCache = new HashMap<>();
    Map<Integer, Long> cycleMax = new HashMap<>();

    public double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public long getMaxFunctionValueV2(List<Integer> receivers, long k) {
        int arrSize = (int) Math.ceil(log2(k)) + 1; // need 1 more
        int[][] lastReceivers = new int[receivers.size()][arrSize];
        long[][] sums = new long[receivers.size()][arrSize];

        for (int i = 0; i < lastReceivers.length; i++) {
            lastReceivers[i][0] = receivers.get(i);
        }

        for (int i = 0; i < sums.length; i++) {
            sums[i][0] = receivers.get(i);
        }

        for (int j = 1; j < arrSize; j++) {
            for (int i = 0; i < lastReceivers.length; i++) {
                int nextReceiver = lastReceivers[i][j-1]; // kiếm nextReceiver nếu đi từ i 2^(j-1) lần
                lastReceivers[i][j] = lastReceivers[nextReceiver][j-1]; // lastReceiver[i][j] = đi từ nextReceiver 2^(j-1) lần nữa
            }
        }

        for (int j = 1; j < arrSize; j++) {
            for (int i = 0; i < sums.length; i++) {
                int nextReceiver = lastReceivers[i][j - 1]; // kiếm nextReceiver nếu đi từ i 2^(j-1) lần
                sums[i][j] = sums[i][j - 1] + sums[nextReceiver][j - 1]; // sum[i][j] = sum của đi từ i (j-1) lần + sum của đi từ nextReceiver (j-1) lần
            }
        }

        List<Integer> logs = findLogs(k);
        long max = 0;
        for (int i = 0; i < receivers.size(); i++) {
            int curReceiver = i;
            long curMax = i; // bắt đầu từ i nên curMax = i
            for (int l : logs) {
                curMax += sums[curReceiver][l];
                curReceiver = lastReceivers[curReceiver][l]; // nextReceiver sẽ là từ curReceiver đi them 2^l
            }
            if (curMax == 17) {
                System.out.println(receivers.get(lastReceivers[i][logs.get(0)]) + " " + receivers.get(lastReceivers[i][logs.get(1)]) + " " + receivers.get(lastReceivers[i][logs.get(2)]));
            }
            max = Math.max(max, curMax);
        }
        return max;
    }

    public List<Integer> findLogs(double k) {
        List<Integer> l = new ArrayList<>();
        int log = (int)Math.floor(log2(k));
        while (k > 0) {
            l.add(log);
            k = k - Math.pow(2, log);
            log = (int)Math.floor(log2(k));
        }
        return l;
    }

    public long getMaxFunctionValue(List<Integer> receivers, long k) {
        int[] parents = new int[receivers.size()];
        Node[] nodes = new Node[receivers.size()];
        Arrays.fill(parents, -1);
        long maxFuncVal = 0;

        for (int curNodeVal = 0; curNodeVal < receivers.size(); curNodeVal++) {
            int nextNodeVal = receivers.get(curNodeVal);
            Node nextNode = nodes[nextNodeVal];

            if (nextNode == null) {
                nextNode = new Node(nextNodeVal);
                nodes[nextNodeVal] = nextNode;
            }

            Node curNode = nodes[curNodeVal];
            if (curNode == null) {
                curNode = new Node(curNodeVal);
            }

            curNode.next = nextNode;
            nodes[curNodeVal] = curNode;
            parents[nextNodeVal] = curNodeVal;
        }

        // find heads
        List<Node> heads = findHeads(parents, nodes);

        for (Node head : heads) {
            maxFuncVal = Math.max(maxFuncVal, maxValueInChain(head, nodes, k));
        }
        return maxFuncVal;
    }

    public List<Node> findHeads(int[] parents, Node[] nodes) {
        List<Node> heads = new ArrayList<>();
        boolean[] visited = new boolean[nodes.length];
        Arrays.fill(visited, false);

        for (int nodeVal = 0; nodeVal < parents.length; nodeVal++) {
            if (parents[nodeVal] == -1) {
                heads.add(nodes[nodeVal]);
                visited[nodeVal] = true;
            }
        }

        for (Node head : heads) {
            Node curNode = head.next;
            while (!visited[curNode.val]) {
                visited[curNode.val] = true;
                curNode = curNode.next;
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            if (visited[i]) {
                continue;
            }
            // if head is one node in a cycle
            Node curNode = nodes[i];
            heads.add(curNode);
            // mark all nodes in this cycle as visited
            while (!visited[curNode.val]) {
                visited[curNode.val] = true;
                curNode = curNode.next;
            }
        }

        return heads;
    }

    public long maxValueInChain(Node head, Node[] nodes, long k) {
        Node cycleNode = detectCycleNode(head);
        long maxInCycle = maxInCycle2(cycleNode, k);

        int nodeCountBeforeCycle = countNodeBeforeCircle(head, cycleNode);
        long maxOutCircle = 0;
        // calculate max before the cycle
        if (nodeCountBeforeCycle > 0) {
            long[] sumOutCircle = new long[nodeCountBeforeCycle];
            Node curNode = head;
            sumOutCircle[0] = curNode.val;
            for (int i = 1; i < sumOutCircle.length; i++) {
                curNode = curNode.next;
                sumOutCircle[i] = curNode.val + sumOutCircle[i - 1];
            }

            for (int i = 0; i < nodeCountBeforeCycle; i++) {
                int lastIndex = 0;
                if (k + i >= nodeCountBeforeCycle) {
                    lastIndex = nodeCountBeforeCycle - 1;
                } else {
                    lastIndex = (int) (k + i);
                }

                long fistNodeSum = i > 0 ? sumOutCircle[i - 1] : 0;
                long curMax = (sumOutCircle[lastIndex] - fistNodeSum);

                long circleK = k - (nodeCountBeforeCycle - i);
                if (circleK >= 0) {
                    curMax += maxInFirstLoop(cycleNode, circleK);
                }

                maxOutCircle = Math.max(curMax, maxOutCircle);
            }
        }

        return Math.max(maxInCycle, maxOutCircle);
    }

    public long maxInFirstLoop(Node cycleNode, long k) {
        CycleInfo cycleInfo = cycleNodeCache.get(cycleNode.val);
        long size = k + 1; // k bước nhảy sẽ có k+1 phần tử
        long[] sums = cycleInfo.sumInCycle;
        int leftOver = (int)(size % cycleInfo.len); // dư 2 phần tử
        long leftOverSum = 0;
        if (leftOver > 0) {
            leftOverSum = sums[leftOver-1]; // tổng của số phần tử dư, nếu dư 0 phần tử tức là tổng phần dư = 0
        }

        return leftOverSum + sums[cycleInfo.len-1]*(size/cycleInfo.len);
    }

    public long maxInCycle2(Node cycleNode, long k) {
        if (cycleMax.containsKey(cycleNode.val)) {
            return cycleMax.get(cycleNode.val);
        }
        CycleInfo cycleInfo = cycleNodeCache.get(cycleNode.val);
        if (cycleInfo == null) {
            int cycleLen = detectCycleLen(cycleNode);

            long[] sums = new long[cycleLen];
            sums[0] = cycleNode.val;
            Node curNode = cycleNode.next;
            for (int i = 1; i < cycleLen; i++) {
                sums[i] = sums[i - 1] + curNode.val;
                curNode = curNode.next;
            }

            cycleInfo = new CycleInfo(cycleNode.val, cycleLen, sums);
            cycleNodeCache.put(cycleNode.val, cycleInfo);
        }

        int cycleLen = cycleInfo.len;
        long size = k + 1; // k bước nhảy sẽ có k+1 phần tử
        int leftOver = (int)(size % cycleLen);
        int pointerNode2Index = leftOver;

        Node pointerNode1 = cycleNode;
        Node pointerNode2 = pointerNode1;
        while (pointerNode2Index > 0) {
            pointerNode2 = pointerNode2.next;
            pointerNode2Index--;
        }

        long curSum = 0;
        Node curNode = pointerNode1;
        while (leftOver > 0) {
            curSum += curNode.val;
            curNode = curNode.next;
            leftOver--;
        }

        long curMax = curSum;
        curNode = cycleNode.next;
        while (curNode != cycleNode) {
            curSum = curSum - pointerNode1.val + pointerNode2.val;
            curMax = Math.max(curSum, curMax);
            curNode = curNode.next;
            pointerNode1 = pointerNode1.next;
            pointerNode2 = pointerNode2.next;
        }

        long sumInOneCycle = cycleInfo.sumInCycle[cycleLen-1];
        long max = curMax + (size/cycleLen)*sumInOneCycle;
        cycleMax.put(cycleNode.val, max);
        return max;
    }

    public int countNodeBeforeCircle(Node head, Node circleNode) {
        int count = 0;
        while (head != circleNode) {
            head = head.next;
            count++;
        }
        return count;
    }

    public int detectCycleLen(Node firstCircleNode) {
        Set<Integer> visited = new HashSet<>();
        int circleLen = 0;
        Node curNode = firstCircleNode;
        while (!visited.contains(curNode.val)) {
            circleLen++;
            visited.add(curNode.val);
            curNode = curNode.next;
        }

        return circleLen;
    }

    /**
     * return node id in the circle
     */
    public Node detectCycleNode(Node head) {
        Set<Integer> visited = new HashSet<>();
        Node curNode = head;
        while (true) {
            if (visited.contains(curNode.val)) {
                return curNode;
            }
            visited.add(curNode.val);
            curNode = curNode.next;
        }
    }

    public static void main(String[] args) {
        Problem2836 app = new Problem2836();
        //long v = app.getMaxFunctionValue(List.of(2,0,1), 4);
        //long v = app.getMaxFunctionValue(List.of(0),  1);
        //long v = app.getMaxFunctionValue(List.of(2, 1, 1),  4);
        //long v = app.getMaxFunctionValue(List.of(3,2,2,1,0),  2);
        long v = app.getMaxFunctionValueV2(List.of(1,2,0,0), 14);
        System.out.println(v);
    }
}
