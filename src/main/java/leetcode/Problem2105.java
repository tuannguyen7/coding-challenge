package leetcode;

public class Problem2105 {

    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int totalTime = 0;
        int curCapA = capacityA;
        int curCapB = capacityB;
        for (int i = 0; i < plants.length/2; i++) {
            if (plants[i] > curCapA) {
                curCapA = capacityA;
                totalTime += 1;
            }
            if (plants[plants.length - 1 - i] > curCapB) {
                curCapB = capacityB;
                totalTime += 1;
            }
            curCapA -= plants[i];
            curCapB -= plants[plants.length - 1 - i];
        }
        if (plants.length %2 == 0) {
            return totalTime;
        }
        if (plants[plants.length/2] <= Math.max(curCapA, curCapB)) {
            return totalTime;
        }
        return totalTime + 1;
    }

    public static void main(String[] args) {
        var app = new Problem2105();
        int[] plants = {1};
        int capacityA = 5;
        int capacityB = 5;
        System.out.println(app.minimumRefill(plants, capacityA, capacityB));
    }
}
