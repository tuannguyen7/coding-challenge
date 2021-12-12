package orangelogic;

public class Problem2 {

    public int solution(int N) {
        boolean isPositive = N >= 0;
        String nStr = "" + Math.abs(N);
        StringBuffer ans = new StringBuffer();
        int nIndex = 0;
        for (; nIndex < nStr.length(); nIndex++) {
            char number = nStr.charAt(nIndex);
            if (canAppendNumber(number, isPositive)) {
                ans.append(number);
            } else {
                ans.append('5');
                break;
            }
        }

        if (nIndex == nStr.length()) { // if nIndex == nStr.length(), we couldn't append 5 at the middle of the number
            ans.append('5');
        }
        for (; nIndex < nStr.length(); nIndex++) {
            ans.append(nStr.charAt(nIndex));
        }

        int absAns = Integer.valueOf(ans.toString());
        if (isPositive) {
            return absAns;
        }
        return -absAns;
    }

    private boolean canAppendNumber(char num, boolean isPositive) {
        if (isPositive) {
            return num >= '5';
        }
        return num <= '5';
    }

    public static void main(String[] args) {
        var app = new Problem2();
        System.out.println(app.solution(0));
        System.out.println(app.solution(554));
        System.out.println(app.solution(655));
        System.out.println(app.solution(556));
        System.out.println(app.solution(321));
        System.out.println(app.solution(-545));
        System.out.println(app.solution(100));
        System.out.println(app.solution(-100));
        System.out.println(app.solution(-564));
        System.out.println(app.solution(-3));
        System.out.println(app.solution(-1));
    }
}
