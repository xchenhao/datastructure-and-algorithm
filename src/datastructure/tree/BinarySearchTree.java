package datastructure.tree;

import com.tools.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

// https://github.com/

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size; // 元素的个数

    private Node<E> root; // 根节点

    private Comparator<E> comparator; // 比较器

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

    private static class Node<E> {
        // 存放元素
        E element;
        // 左节点
        Node<E> left;
        // 右节点
        Node<E> right;
        // 父节点
        Node<E> parent;
        // 构造函数
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        inOrder(root, visitor);
    }
    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inOrder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrder(root, visitor);
    }
    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrder(root, visitor);
    }
    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (visitor.visit(node.element)) {
                break;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * @param <E>
     * @return 如果返回 true，就代表停止遍历
     */
    public static abstract class Visitor<E> {
        boolean stop;
        abstract boolean visit(E element);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    // 便利构造器
    public BinarySearchTree() {
        this(null);
    }
    // 构造函数
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((java.lang.Comparable<E>)e1).compareTo(e2);
    }

    // 元素的数量
    public int size() {
        return size;
    }
    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }
    // 清空所有元素
    public void clear() {

    }
    // 添加元素
    public void add(E element) {
        // 判断节点的值是否为空。
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 添加的不是第一个节点
        Node<E> parent = root;
        Node<E> node = root;
        // 比较结果
        int cmp = 0;
        while (node != null) {
            // 在之前定义的比较函数
            cmp = compare(element, node.element);
            //保存node的父节点
            parent = node;
            if (cmp > 0) {
                // 右子节点
                node = node.right;
            } else if (cmp < 0) {
                //左子节点
                node = node.left;
            } else {
                // 相等
                node.element = element;
                return;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }
    // 删除元素
    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

//        if (node.hasTwoChildren()) { // 度为2的节点
//            // 找到后继节点
//            Node<E> s = successor(node);
//            // 用后继节点的值覆盖度为2的节点的值
//            node.element = s.element;
//            // 删除后继节点
//            node = s;
//        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    // 是否包含某元素
    public boolean contains(E element) {
        return false;
    }

    public void preorderTraversal() {
        preorderTraversal(root);
    }
    // 递归实现前序遍历
    private void preorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) return;
        // 打印节点值
        System.out.println(node.element);
        // 前序遍历左子树
        preorderTraversal(node.left);
        // 前序遍历右子树
        preorderTraversal(node.right);
    }

    // 中序遍历
    // 左子树->根节点->右子树：从小到大的顺序
    // 右子树->根节点->左子树：从大到小的顺序
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    // 递归实现中序遍历
    private void inorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) return;
        // 中序遍历左子树
        inorderTraversal(node.left);
        // 打印节点值
        System.out.println(node.element);
        // 中序遍历右子树
        inorderTraversal(node.right);
    }

    // 后序遍历：左子树、右子树，根节点
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    // 递归实现后序遍历
    private void postorderTraversal(Node<E> node) {
        // 退出条件
        if (node == null) return;
        // 后序遍历左子树
        postorderTraversal(node.left);
        // 后序遍历右子树
        postorderTraversal(node.right);
        // 打印节点值
        System.out.println(node.element);
    }

    public void levelOrderTranversal() {
        // 如果根节点为空，直接返回
        if (root == null) return;
        // 创建存储节点的队列
        Queue<Node<E>> queue = new LinkedList<>();
        //将头节点入队列
        queue.offer(root);
        //退出条件，当队列为空
        while (!queue.isEmpty()) {
            //取出队列头元素
            Node<E> node = queue.poll();
            //打印头元素
            System.out.println(node.element);
            //如果头元素左子树不为空
            if (node.left != null) {
                //将头元素左子树入队
                queue.offer(node.left);
            }
            //如果头元素右子树不为空
            if (node.right != null) {
                //将头元素右子树入队
                queue.offer(node.right);
            }
        }
    }

    // 前驱节点
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        // 前驱节点在左子树当中（left.right.right.right....）
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    // 后继节点
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        // 前驱节点在左子树当中（right.left.left.left....）
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    // 前驱节点：左子树的极右，祖父节点往右转的起点
    // 后继节点：祖父节点往左转的起点，右子树的极左

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;

        toString(node.left, sb, prefix + "L---");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb, prefix + "R---");
    }

    // 通过递归实现获取树的高度
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 通过迭代（层序遍历）实现获取树的高度
    public int height2() {
        if (root == null) return 0;
        int height = 0;  // 树的高度
        int levelSize = 1;  // 存储着每一层的高度

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {  // 意味着即将要访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }

    // 判断是否是一棵完全二叉树
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {  // 后面遍历的节点都必须是叶子节点
                leaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }

        return true;
    }

    public boolean isComplete2() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // node.left == null && node.right == null
                // node.left != null && node.right == null
                leaf = true;
            }
        }

        return true;
    }
}
