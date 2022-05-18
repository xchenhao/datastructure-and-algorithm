package com.demo;

import org.junit.Test;

public class DequeTest {

    @Test
    public void test() {
        Deque<Integer> queue = new Deque<>();

        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueRear());
        }
    }

}
