package com.demo;

/**
 * @param <E>
 * @link https://juejin.cn/post/6844904021778513933
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
    private E[] elements;

    // 设置 elements 数组默认的初始化空间
    private static final int CAPACITY_DEFAULT = 10;

    public ArrayList(int capacity) {
        capacity = Math.max(capacity, CAPACITY_DEFAULT);
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(CAPACITY_DEFAULT);
    }

    private void ensureCapacity(int capacity) {
        // 获取数组当前容量
        int oldCapacity = elements.length;
        // 如果 当前存储的元素个数  当前数组容量, 直接返回
        if (capacity <= oldCapacity) return;
        // 新数组的容量为原数组容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 创建新数组
        E[] newElements = (E[]) new Object[newCapacity];
        // 原数组中的元素存储到新数组中
        for (int i = 0; i < capacity; i++) {
            newElements[i] = elements[i];
        }
        // 引用新数组
        elements = newElements;
    }

    // 返回index位置对应的元素
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    // 设置index位置的元素
    public E set(int index, E element) {
        // 判断索引是否越界
        rangeCheck(index);
        // 取出被替换元素
        E oldElement = elements[index];
        // 替换元素
        elements[index] = element;
        // 返回被替换元素
        return oldElement;
    }

    // 往index位置添加元素
    public void add(int index, E element) {
        //判断越界
        rangeCheckForAdd(index);
        //判断扩容
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    // 删除index位置对应的元素
    public E remove(int index) {
        // 判断索引是否越界
        rangeCheck(index);
        // 取出需要删除的元素
        E old = elements[index];
        // 通过循环, 将index后面的所有元素向前移动一位
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        // 删除最后一个元素
        elements[--size] = null;
        // 判断数组是否需要缩容
        trim();
        // 将删除的元素返回
        return old;
    }


    // 查看元素的位置
   public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    // 清除所有元素
    public void clear() {
        // 清空存储的数据
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        // 将size置为0
        size = 0;
    }

    public void trim() {
        // 获取当前数组的容量
        int capacity = elements.length;
        // 当size大于等于容量的一半, 或则容量已经小于默认容量(10)时, 直接返回
        if (size >= capacity >> 1 || capacity < CAPACITY_DEFAULT) return;
        // 计算新的容量 = 原有容量的一半
        int newCapacity = capacity >> 1;
        // 创建新数组
        E[] newElements = (E[]) new Object[newCapacity];
        // 将原数组元素存入新数组
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        // 引用新数组
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(",");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
