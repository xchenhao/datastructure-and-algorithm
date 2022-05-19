package datastructure.linear;

import org.junit.Test;

public class SingleDirectionLinkedListTest {

    @Test
    public void test() {

        List<Integer> list = new SingleDirectionLinkedList<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);

        list.remove(1);

        System.out.println(list);
    }

    @Test
    public void test2() {
        List<Integer> list = new SingleDirectionLinkedList2<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);

        list.remove(1);

        System.out.println(list);
    }
}
