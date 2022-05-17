package com.demo;

import org.junit.Test;

public class FibonacciTest {

    @Test
    public void test() {
        int n = 45;

        TimeTool.check("method1", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(Fibonacci.method1(n));
            }
        });

        TimeTool.check("method2", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(Fibonacci.method2(n));
            }
        });

        TimeTool.check("method3", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(Fibonacci.method3(n));
            }
        });

        TimeTool.check("method4", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(Fibonacci.method4(n));
            }
        });
    }

}
