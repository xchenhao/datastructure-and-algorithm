package com.demo;

import org.junit.Test;

public class Fibonacci {

    @Test
    public void test() {
        int n = 45;

        TimeTool.check("method1", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(method1(n));
            }
        });

        TimeTool.check("method2", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(method2(n));
            }
        });
    }

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
}
