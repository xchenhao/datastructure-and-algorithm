package datastructure.linear;

import org.junit.Test;

public class StackTest {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();
        stack.add(11);
        stack.add(22);
        stack.add(33);
        stack.add(44);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    @Test
    public void test2() {
        Stack2<Integer> stack = new Stack2<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
