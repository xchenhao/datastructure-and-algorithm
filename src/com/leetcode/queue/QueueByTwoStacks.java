package com.leetcode.queue;

import datastructure.linear.Stack2;
import org.junit.Test;

// 用两个栈实现队列
public class QueueByTwoStacks {

    private Stack2<Integer> inStack = new Stack2<>();
    private Stack2<Integer> outStack = new Stack2<>();

    @Test
    public void test() {
        QueueByTwoStacks queue = new QueueByTwoStacks();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    // 入队
    public void enQueue(int x) {
        inStack.push(x);
    }

    // 出队
    public int deQueue() {
        checkOutStack();
        return outStack.pop();
    }

    // 获取队头
    public int front() {
        checkOutStack();
        return outStack.top();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

}
