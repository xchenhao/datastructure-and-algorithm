package datastructure.linear;

public class Fibonacci {

    public static int method1(int n) {
        if (n <= 1) {
            return n;
        }
        return method1(n - 1) + method1(n - 2);
    }

    public static int method2(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    // method2 的优化版
    public static int method3(int n) {
        if (n <= 1) {
            return n;
        }

        int first = 0;
        int second = 1;
        while (n-- > 1) {
            second += first;
            first = second - first;
        }

        return second;
    }

    // 其它方法：线性代数解法（特征方程）
    public static int method4(int n) {
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c)/2, n) - Math.pow((1-c)/2, n))/c);
    }
}
