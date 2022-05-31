package datastructure.tree;

import com.tools.file.Files;
import com.tools.printer.BinaryTrees;
import org.junit.Test;

public class RedBlackTreeTest {

    @Test
    public void test() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

//        BinaryTrees.println(rb);
        Files.writeToFile("a.log", BinaryTrees.printString(rb));
    }

    @Test
    public void test2() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };

        RedBlackTree<Integer> rb = new RedBlackTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);
        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("【" + data[i] + "】");
            BinaryTrees.println(rb);
            System.out.println("------------------------------");
        }
//        BinaryTrees.println(rb);
        Files.writeToFile("a.log", BinaryTrees.printString(rb));
    }

}
