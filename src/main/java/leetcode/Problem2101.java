package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2101 {

    public int maximumDetonation(int[][] bombs) {
        List<Integer>[] edges = new List[bombs.length];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < bombs.length - 1; i++) {
            for (int j = i + 1; j < bombs.length; j++) {
                if (canBoom(bombs[i], bombs[j])) {
                    edges[i].add(j);
                }
                if (canBoom(bombs[j], bombs[i])) {
                    edges[j].add(i);
                }
            }
        }

        // DFS
        boolean[] visited = new boolean[bombs.length];
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            Arrays.fill(visited, false);
            max = Math.max(maxBomb(i, edges, visited), max);
        }

        return max;
    }

    private int maxBomb(int index, List<Integer>[] edges, boolean[] visited) {
        if (visited[index]) {
            return 0;
        }
        visited[index] = true;

        int total = 1;
        for (int vertex : edges[index]) {
            total += maxBomb(vertex, edges, visited);
        }
        return total;
    }

    // return true if b1 can bomb b2
    private boolean canBoom(int[] b1, int[] b2) {
        long dis = pow2(b1[0] - b2[0]) + pow2(b1[1] - b2[1]);
        return dis <= pow2(b1[2]);
    }
    private long pow2(int a) {
        return (long)a*a;
    }

    public static void main(String[] args) {
        var app = new Problem2101();
        //int[][] bombs = {{54,95,4},{99,46,3},{29,21,3},{96,72,8},{49,43,3},{11,20,3},{2,57,1},{69,51,7},{97,1,10},{85,45,2},{38,47,1},{83,75,3},{65,59,3},{33,4,1},{32,10,2},{20,97,8},{35,37,3}};
        //int[][] bombs = {{855,82,158},{17,719,430},{90,756,164},{376,17,340},{691,636,152},{565,776,5},{464,154,271},{53,361,162},{278,609,82},{202,927,219},{542,865,377},{330,402,270},{720,199,10},{986,697,443},{471,296,69},{393,81,404},{127,405,177}};
        //int[][] bombs = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        int[][] bombs = {{38496,37528,4845},{46272,98187,1365},{70550,7578,3223},{77200,18005,7272},{7648,58155,7628},{95708,33470,1889},{20157,92266,9823},{52803,2765,6751},{50429,63049,3002},{72582,69729,2281},{49317,35327,1922},{715,8902,9620},{21154,58349,8544},{43935,46296,6868},{7881,24144,2372},{95258,97730,6554},{5525,56971,9191},{95762,81415,2027},{62518,75469,1330},{83660,4341,6817},{30268,38781,8309},{97922,20474,4047},{39466,40057,2061},{91983,24242,5451},{92380,31509,8446},{12436,8897,5279},{28386,8556,4702},{54672,88180,1106},{17843,95337,4420},{21956,49924,1839}};
        System.out.println(app.maximumDetonation(bombs));
    }
}
