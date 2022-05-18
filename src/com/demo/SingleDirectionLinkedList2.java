package com.demo;

/**
 * 增加一个虚拟头节点
 * @param <E>
 */
public class SingleDirectionLinkedList2<E> extends AbstractList<E> {
    private Node<E> first;

    public SingleDirectionLinkedList2() {
        first = new Node<>(null, null);
    }

    // 私有类, 链表中的节点
    private static class Node<E> {
        E element;
        Node<E> next;
        // 构造方法
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> node(int index) {
        //越界判断
        rangeCheck(index);

        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
        // 当插入到0的位置时

        // 找到指定位置前面的节点
        Node<E> prev =index == 0 ? first : node(index - 1);
        // 将前面节点的next指向新节点, 新节点的next指向prev之前指向的节点
        prev.next = new Node<>(element, prev.next);
        size++;
    }

    // 删除index位置对应的元素
    public E remove(int index) {
        // 检查索引是否越界
        rangeCheck(index);

        // 找到前一个元素
        Node<E> prev = index == 0 ? first : node(index - 1);
        // 记录需要删除的节点
        Node<E> old = prev.next;
        // 将prev的next指向需要删除节点的后一个节点
        prev.next = old.next;

        size--;
        // 返回删除的元素
        return old.element;
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
        first = null;
        size = 0;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(",");
            }
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

}
