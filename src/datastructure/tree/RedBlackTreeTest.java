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

}
