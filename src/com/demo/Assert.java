package com.demo;

public class Assert {

    public static void test(boolean ok) throws Exception {
        if (!ok) {
            throw new Exception("测试不通过");
        }
    }
}
