package datastructure.tree;

import com.tools.file.Files;
import com.tools.printer.BinaryTrees;
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

}
