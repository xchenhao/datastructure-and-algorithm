package com.demo;

import org.junit.Test;

public class QueueTest {

    @Test
    public void test() {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }
}
