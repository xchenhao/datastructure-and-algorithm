package com.demo;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void test() {

        List<Integer> list = new LinkedList<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);

        list.remove(1);

        System.out.println(list);

    }
}
