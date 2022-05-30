package datastructure.tree;

// 红黑树继承于二叉平衡搜索树，这里只列出红黑树特有的属性。
public class RedBlackTree<E> extends BalancedBinarySearchTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) {  // 叔父节点是红色（B 树节点上溢）
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) {  // L
            if (node.isLeftChild()) {  // LL
                black(parent);
            } else {  // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {  // R
            if (node.isLeftChild()) {  // RL
                black(node);
                rotateRight(parent);
            } else {  // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
    }

    // 构造一个红黑节点，默认为红色
    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }


        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }

            return str + element.toString();
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }


    // 节点染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    // 将节点染为红色
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    // 将节点染为黑色
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    // 节点的颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    // 是否为黑色节点
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    // 是否为红色节点
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

}
