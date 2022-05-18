package com.demo;

import org.junit.Test;

public class CircleDequeTest {

    @Test
    public void test() {
        CircleDeque<Integer> deque = new CircleDeque<>();

        for (int i = 0; i < 10; i++) {
            deque.enQueueFront(i + 1);
            deque.enQueueRear(i + 100);
        }

        System.out.println(deque);
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
            deque.deQueueRear();
        }

        System.out.println(deque);
        deque.enQueueFront(11);
        deque.enQueueFront(12);

        System.out.println(deque);

        while (!deque.isEmpty()) {
            System.out.println(deque.deQueueFront());
        }
    }

}
