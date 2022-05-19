package datastructure.linear;

/**
 * @link https://juejin.cn/post/6844904023921786894
 * @link https://visualgo.net/zh
 * @param <E>
 */
public class CircleSingleDirectionLinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    // 私有类, 链表中的节点
    private static class Node<E> {
        E element;
        Node<E> next;
        // 构造方法
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append(element).append("_").append(next.element);

            return sb.toString();
        }
    }

    private Node<E> node(int index) {
        // 越界判断
        rangeCheck(index);

        Node<E> node = first;
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
        if (index == 0) {
            // 将first指向新节点, 新节点的next指向first之前指向的节点
            Node<E> newFirst = new Node<>(element, first);
            // 拿到最后一个节点
            Node<E> last = size == 0 ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        }else {
            // 找到指定位置前面的节点
            Node<E> prev = node(index - 1);
            // 将前面节点的next指向新节点, 新节点的next指向prev之前指向的节点
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    // 删除index位置对应的元素
    public E remove(int index) {
        // 检查索引是否越界
        rangeCheck(index);
        // 记录需要删除的节点
        Node<E> old = first;
        // 当删除第0个元素时, 将first的next指向索引为`1`的节点即可
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        }else {
            // 找到前一个元素
            Node<E> prev = node(index - 1);
            // 记录需要删除的节点
            old = prev.next;
            // 将prev的next指向需要删除节点的后一个节点
            prev.next = old.next;
        }
        // size-1
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
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(",");
            }
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }

}
