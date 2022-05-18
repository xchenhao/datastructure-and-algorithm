package com.demo;

/**
 * @link https://juejin.cn/post/6844904023393304590
 * @link https://visualgo.net/zh
 * @param <E>
 */
public class CircleDoubleDirectionLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    // 私有类, 链表中的节点
    private static class Node<E> {
        E element;

        Node<E> prev;
        Node<E> next;

        // 构造方法
        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    private Node<E> node(int index) {
        //越界判断
        rangeCheck(index);

        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }

            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

            return node;
        }
    }

    // 返回index位置对应的元素
    public E get(int index) {
        // node方法中已经判断了索引是否越界
        return node(index).element;
    }

    // 设置index位置的元素
    public E set(int index, E element) {
        // 找到对应节点, node方法中已经判断了索引是否越界
        Node<E> node = node(index);
        // 记录旧元素
        E old = node.element;
        // 覆盖元素
        node.element = element;
        // 返回旧元素
        return old;
    }

    // 往index位置添加元素
    public void add(int index, E element) {
        // 检查索引是否越界
        rangeCheckForAdd(index);

        if (index == size) {  // 往最后添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast, element, first);
            if (oldLast == null) {
                // 链表添加的第 1 个元素
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> pre = next.prev;
            Node<E> node = new Node<>(pre, element, next);

            next.prev = node;
            pre.next = node;

            // 或 index == 0
            if (next == first) {
                first = node;
            }
        }

        size++;
    }

    // 删除index位置对应的元素
    public E remove(int index) {
        // 检查索引是否越界
        rangeCheck(index);

        Node<E> node = first;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            node = node(index);
            Node<E> prev = node.prev;
            Node<E> next = node.next;

            prev.next = next;
            next.prev = prev;

            // 或 index == 0
            if (node == first) {
                first = next;
            }
            // 或 index == size - 1
            if (node == last) {
                last = prev;
            }
        }

        size--;
        return node.element;
    }

    public int indexOf(E element) {
        // 取出头结点
        Node<E> node = first;
        // 当element为null时的处理
        if (element == null) {
            // 遍历节点, 找到存储为null的节点, 返回索引
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        }else {
            for (int i = 0; i < size; i++) {
                // 遍历节点, 找到存储的元素与指定元素相等的节点, 返回索引
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        // 没有找到元素对应的节点, 返回ELEMENT_ON_FOUND
        return ELEMENT_NOT_FOUND;
    }

    // 清除所有元素
    public void clear() {
        size = 0;
        first = null;
        last = null;
        // gc root 对象
        // 1 被栈指针（局部变量）指向的对象
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

}
