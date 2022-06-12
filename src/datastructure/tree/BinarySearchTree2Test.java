package datastructure.tree;

import com.tools.printer.BinaryTrees;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTree2Test {

    @Test
    public void test() {
        // 创建BST
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11
        };
        BinarySearchTree2<Integer> bst = new BinarySearchTree2<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        // 树状打印
        BinaryTrees.println(bst);
        // 遍历器
        StringBuilder sb = new StringBuilder();
        BinaryTree2.Visitor<Integer> visitor = new BinaryTree2.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                sb.append(element).append(" ");
                return false;
            }
        };
        // 遍历
        sb.delete(0, sb.length());
        bst.preorder(visitor);
        System.out.println("前序：" + sb );
        Assert.assertEquals(sb.toString(), "7 4 2 5 9 8 11 ");

        sb.delete(0, sb.length());
        bst.inorder(visitor);
        System.out.println("中序：" + sb );
        Assert.assertEquals(sb.toString(), "2 4 5 7 8 9 11 ");

        sb.delete(0, sb.length());
        bst.postorder(visitor);
        System.out.println("后序：" + sb );
        Assert.assertEquals(sb.toString(), "2 5 4 8 11 9 7 ");
    }
}
