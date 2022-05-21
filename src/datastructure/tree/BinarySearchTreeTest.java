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


    @Test
    public void test3() {
        BinarySearchTree<Integer> bst4 = new BinarySearchTree<>();
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1,
        };
        for (int i = 0; i < data.length; i++) {
            bst4.add(data[i]);
        }

        String str = BinaryTrees.printString(bst4);
        Files.writeToFile("./a.log", str);
        // 前序遍历
        // bst4.preorderTraversal();

        // 中序遍历
        // bst4.inorderTraversal();

        // 后序遍历
        // bst4.postorderTraversal();

        // 层序遍历
        // bst4.levelOrderTranversal();

        System.out.println("层序顺序");
        bst4.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                // System.out.println(element);
                System.out.print("_" + element + "_");
            }
        });

        System.out.println();
        System.out.println("中序顺序");
        bst4.inOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                // System.out.println(element);
                System.out.print("_" + element + "_");
            }
        });

        System.out.println();
        System.out.println("后序顺序");
        bst4.postOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                // System.out.println(element);
                System.out.print("_" + element + "_");
            }
        });

        System.out.println();
        System.out.println("前序顺序");
        bst4.preOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                // System.out.println(element);
                System.out.print("_" + element + "_");
            }
        });
        System.out.println();
    }

}
