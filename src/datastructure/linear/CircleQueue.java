package datastructure.linear;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
    private int frontIndex;
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

     public CircleQueue() {
         elements = (E[]) new Object[DEFAULT_CAPACITY];
     }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
         ensureCapacity(size + 1);

         elements[index(size)] = element;
         size++;
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

    public E deQueue() {
         E frontElement = elements[frontIndex];
         elements[frontIndex] = null;
         frontIndex = index(1);
         size--;

         return frontElement;
    }

    public E front() {
         return elements[frontIndex];
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

    private int index(int index) {
         return (frontIndex + index) % elements.length;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        frontIndex = 0;
        size = 0;
    }
}
