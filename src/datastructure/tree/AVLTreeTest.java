package datastructure.tree;

import com.tools.file.Files;
import com.tools.printer.BinaryTrees;
import datastructure.linear.ArrayList;
import datastructure.linear.List;
import org.junit.Test;

public class AVLTreeTest {

    @Test
    public void test() {
        Integer data[] = new Integer[] {
                85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56,
        };

        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        String str = BinaryTrees.printString(avlTree);
        Files.writeToFile("a.log", str);
    }

    @Test
    public void test2() {
        Integer data[] = new Integer[] {
                85, 19, 69, 3, 7, 99, 95
        };

        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        BinaryTrees.println(avlTree);

        avlTree.remove(99);
        avlTree.remove(85);
        avlTree.remove(95);

        BinaryTrees.println(avlTree);
//        String str = BinaryTrees.printString(avlTree);
//        Files.writeToFile("a.log", str);
    }

    @Test
    public void test3() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            data.add((int)(Math.random() * 100_0000));
        }

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.size(); i++) {
            bst.add(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            bst.contains(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            bst.remove(data.get(i));
        }


        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < data.size(); i++) {
            avl.add(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            avl.contains(data.get(i));
        }
        for (int i = 0; i < data.size(); i++) {
            avl.remove(data.get(i));
        }

    }

}
