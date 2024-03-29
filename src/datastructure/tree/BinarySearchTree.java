package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://github.com/

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {
    private Comparator<E> comparator; // 比较器

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

    // 添加元素
    public void add(E element) {
        // 判断节点的值是否为空。
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;

            // 新添加节点之后的处理
            afterAdd(root);
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
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加 node 之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {

    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {  // cmp < 0
                node = node.left;
            }
        }

        return null;
    }

    /**
     * 删除 node 之后的调整
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node, Node<E> replacement) {
    }

    /**
     * 删除 node 之后的调整
     * @param node 被删除的节点 或者用以取代被删除节点的子节点（当被删除节点的度为 1）
     */
    protected void afterRemove(Node<E> node) {
    }

    public void remove(E element) {
        remove(node(element));
    }

    // 删除元素
    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

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

            // 删除节点之后的处理
            // afterRemove(node, replacement);
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    // 是否包含某元素
    public boolean contains(E element) {
        return node(element) != null;
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

}
