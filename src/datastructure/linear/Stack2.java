package datastructure.linear;

public class Stack2<E> {
    // 使用组合：动态数组存储数组
    private ArrayList<E> list;

    public Stack2() {
        // 初始化方法中创建列表
        this.list = new ArrayList<>();
    }

    public int size() {
        // 栈中元素数量, 就是列表中存储的元素数量
        return list.size();
    }

    public boolean isEmpty() {
        // 栈是否空, 就是列表是否空
        return list.isEmpty();
    }

    public void push(E element) {
        // 入栈, 将元素添加到列表的最后面
        list.add(element);
    }

    public E pop() {
        // 出栈, 将列表中最后一个元素删除并返回
        return list.remove(list.size() - 1);
    }

    public E top() {
        // 查看栈顶元素, 就是查看列表中的最后一个元素
        return list.get(list.size() - 1);
    }

    public void clear() {
        // 清空栈, 就是清空列表
        list.clear();
    }

}
