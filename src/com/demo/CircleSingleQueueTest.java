package com.demo;

import org.junit.Test;

public class CircleSingleQueueTest {

    @Test
    public void test() {
        CircleSingleQueue<Object> queue = new CircleSingleQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        for (int i = 15; i < 23; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

}
