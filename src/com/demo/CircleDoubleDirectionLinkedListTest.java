package com.demo;

import org.junit.Assert;
import org.junit.Test;

public class CircleDoubleDirectionLinkedListTest {

    @Test
    public void test() {
        CircleDoubleDirectionLinkedList<Integer> list = new CircleDoubleDirectionLinkedList<>();

        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55); // 55 11 22 33 44
        list.add(2, 66);  // 55 11 66 22 33 44
        list.add(list.size(), 77);  // 55 11 66 22 33 44 77

        list.remove(0); // 11 66 22 33 44 77
        list.remove(2); // 11 66 33 44 77
        list.remove(list.size()-1);  // 11 66 33 44

        System.out.println(list);

        Assert.assertEquals(3, list.indexOf(44));
        Assert.assertEquals(List.ELEMENT_NOT_FOUND, list.indexOf(22));
        Assert.assertTrue(list.contains(33));
        Assert.assertEquals(11, (long)list.get(0));
        Assert.assertEquals(66, (long)list.get(1));
        Assert.assertEquals(44, (long)list.get(list.size() - 1));

        System.out.println(list);
    }

    @Test
    public void JosephusProblem() {
        CircleDoubleDirectionLinkedList<Integer> list = new CircleDoubleDirectionLinkedList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }
        list.reset();  // 指向头节点

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }

}
