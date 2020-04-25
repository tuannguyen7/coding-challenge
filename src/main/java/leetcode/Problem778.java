package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem778 {

  public int M = 0;
  public int N = 0;

  public int swimInWater(int[][] grid) {
    M = grid.length;
    N = grid[0].length;
    boolean[][] visited = new boolean[M][N];
    for (int i = 0; i < M; i++)
        Arrays.fill(visited[i], false);
    PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
    visited[0][0] = true;
    pQueue.add(new Vertex(0, 0, grid[0][0]));
    while (!pQueue.isEmpty()) {
        Vertex vertex = pQueue.poll();
        List<int[]> neighbors = neighbors(vertex.x, vertex.y);
        for (int[] neighbor : neighbors ) {
            int nx = neighbor[0];
            int ny = neighbor[1];
            if (visited[nx][ny])
                continue;
            visited[nx][ny] = true;
            grid[nx][ny] = Math.max(grid[nx][ny], vertex.weight);
            pQueue.add(new Vertex(nx, ny, grid[nx][ny]));
        }
    }

    return grid[M-1][N-1];
  }

  public List<int[]> neighbors(int x, int y) {
      List<int[]> neighbors = new LinkedList<>();
      if (x < M - 1) neighbors.add(new int[] {x + 1, y});
      if (y < N - 1) neighbors.add(new int[] {x, y + 1});
      if (x > 0) neighbors.add(new int[] {x - 1, y});
      if (y > 0) neighbors.add(new int[] {x, y - 1});
      return neighbors;
  }

  public static class Vertex implements Comparable<Vertex> {
      int x;
      int y;
      int weight;

      public Vertex(int x, int y, int weight) {
          this.x = x;
          this.y = y;
          this.weight = weight;
      }

      @Override
      public int compareTo(Vertex o) {
          if (weight != o.weight)
              return weight - o.weight;
          if (x != o.x)
              return x - o.x;
          return y - o.y;
      }
  }

  public static void main(String[] args) {
      //int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
      //int[][] grid = {{0,2},{1,3}};
      int[][] grid = {{3,2},{0,1}};
      //int[][] grid = {{375,396,190,333,304,65,99,262,214,344,167,328,124,207,253,173,55,243,132,163},{22,12,223,298,387,238,237,213,332,379,228,128,280,225,103,114,109,64,271,172},{359,26,80,18,370,372,206,346,342,363,184,11,393,317,291,362,194,308,274,188},{288,158,153,260,278,296,40,231,397,334,4,7,181,219,189,101,54,112,292,25},{391,195,165,268,248,388,143,266,87,250,204,358,187,275,32,127,66,115,146,234},{313,218,8,19,50,164,279,23,182,73,392,74,149,323,107,283,203,302,148,28},{326,162,301,41,131,306,96,200,160,44,137,300,398,170,94,309,38,16,83,129},{245,339,72,310,117,140,264,366,252,314,361,282,230,353,325,374,180,351,68,77},{205,340,367,169,209,255,221,152,226,354,381,319,285,136,138,175,389,273,35,142},{286,249,395,81,390,37,303,338,220,71,242,399,82,176,168,263,299,51,1,125},{198,355,133,15,210,151,376,294,3,235,336,76,113,281,122,110,257,343,246,159},{394,92,384,316,123,211,324,329,5,284,212,239,48,224,196,9,236,119,147,233},{20,24,331,183,90,45,106,201,70,276,186,320,13,256,202,91,352,69,178,116},{105,10,121,86,111,385,98,139,141,269,100,95,327,130,287,58,382,126,297,191},{272,364,222,330,53,232,118,97,63,365,322,36,185,270,47,277,241,49,373,369},{75,0,57,193,199,357,43,60,145,360,267,134,120,29,337,349,161,62,254,350},{348,240,78,311,371,318,17,259,335,251,31,88,341,14,39,85,217,108,293,144},{290,46,56,104,156,356,229,377,177,347,261,52,179,345,289,135,59,34,265,295},{386,380,27,2,6,307,192,378,33,89,84,30,102,321,197,157,61,368,67,383},{154,312,155,215,21,315,216,150,93,79,305,174,42,227,208,244,247,258,166,171}};
      System.out.println(new Problem778().swimInWater(grid));
  }
}
