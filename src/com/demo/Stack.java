package com.demo;

// 直接继承把父类的所有方法继承下来，会被外面方法，不合理
public class Stack<E> extends ArrayList<E> {
    public void push(E element) {
        add(element);
    }

    public E pop() {
        return remove(size - 1);
    }

    public E top() {
        return get(size - 1);
    }
}
