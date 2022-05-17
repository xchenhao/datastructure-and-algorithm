package com.demo;

import org.junit.Test;

public class ArrayListTest {

    public static class Person {

        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(20);
        list.add(50);
        list.add(30);
        list.add(3, 79);

        Assert.test(list.remove(2) == 50);
        Assert.test(list.set(1, 55) == 20);
        Assert.test(list.get(2) == 79);

        System.out.println(list);
    }

    @Test
    public void test2() {
        ArrayList<Person> list = new ArrayList<>();

        list.add(new Person(30, "Jack"));
        list.add(new Person(60, "Tom"));
        list.add(new Person(24, "Kay"));
        list.add(new Person(28, "Lily"));

        System.out.println(list);
    }

}
