package datastructure.linear;

@SuppressWarnings("unchecked")
public class CircleDeque<E> {
    private int frontIndex;
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++;
    }

    public E deQueueFront() {
        E frontElement = elements[frontIndex];
        elements[frontIndex] = null;
        frontIndex = index(1);
        size--;

        return frontElement;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        frontIndex = index(-1);
        elements[frontIndex] = element;
        size++;
    }

    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;

        size--;
        return rear;
    }

    public E front() {
        return elements[frontIndex];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    private int index(int index) {
        index += frontIndex;
        if (index < 0) {
            return index + elements.length;
        }

        // return index % elements.length;
        // 取模运算符 % 优化
        // 前提：index < 2 * elements.length
        return index - (index >= elements.length ? elements.length : 0);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;

        if (capacity <= oldCapacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }

        elements = newElements;
        frontIndex = 0;
        System.out.println("动态扩容：" + capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity=").append(elements.length)
                .append(", size=").append(size)
                .append(", frontIndex=").append(frontIndex)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        frontIndex = 0;
        size = 0;
    }
}
