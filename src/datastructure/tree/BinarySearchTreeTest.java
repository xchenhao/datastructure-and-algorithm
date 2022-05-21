package datastructure.tree;

import com.tools.file.Files;
import com.tools.printer.BinaryTrees;
import org.junit.Test;

public class BinarySearchTreeTest {

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    @Test
    public void test() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1,
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
//        BinaryTrees.println(bst, BinaryTrees.PrintStyle.INORDER);
//        BinaryTrees.println(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
        bst2.add(new Person(12));
        bst2.add(new Person(15));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
        bst3.add(new Person(12));
        bst3.add(new Person(15));



        // Java 匿名类
        BinarySearchTree<Person> bst5 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person e1, Person e2) {
                return 0;
            }
        });
        bst5.add(new Person(12));
        bst5.add(new Person(15));
    }

    @Test
    public void test1() {
        BinarySearchTree<Person> bst4 = new BinarySearchTree<>();
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1,
        };
        for (int i = 0; i < data.length; i++) {
            bst4.add(new Person(data[i]));
        }

        BinaryTrees.println(bst4);
    }

    @Test
    public void test2() {
        BinarySearchTree<Person> bst4 = new BinarySearchTree<>(new PersonComparator2());
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1,
        };
        for (int i = 0; i < data.length; i++) {
            bst4.add(new Person(data[i]));
        }

        String str = BinaryTrees.printString(bst4);
        Files.writeToFile("./a.log", str);
    }
}
