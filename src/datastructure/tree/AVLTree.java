package datastructure.tree;


public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalanceNew(node);
            }
        }
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                // rebalance(node);
                rebalanceNew(node);  // 统一旋转操作
                // 整棵树恢复平衡
                break;
            }
        }
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private void rebalanceNew(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) {  // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {  // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {  // R
            if (node.isLeftChild()) {  // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {  // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    // 对于 AVL 树：a, g 可以不处理
    private void rotate(
            Node<E> r,  // 子树的根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
        )
    {
        // 让 d 成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;

        b.parent = d;
        f.parent = d;

        updateHeight(d);
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) {  // LL
                rotateRight(grand);
            } else {  // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {  // R
            if (node.isLeftChild()) {  // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {  // RR
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> grand) {
        Node<E> p = grand.right;
        Node<E> child = p.left;

        grand.right = child;
        p.left = grand;

        afterRotate(grand, p, child);
    }

    private void rotateRight(Node<E> grand) {
        Node<E> p = grand.left;
        Node<E> child = p.right;

        grand.left = child;
        p.right = grand;

        afterRotate(grand, p, child);
    }

    private void afterRotate(Node<E> grand, Node<E> p, Node<E> child) {
        // 让 p 称为子树的根节点
        p.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = p;
        } else if (grand.isRightChild()) {
            grand.parent.right = p;
        } else {  // grand 是 root 节点
            root = p;
        }

        // 更新 child 的 parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新 grand 的 parent
        grand.parent = p;

        // 更新高度
        updateHeight(grand);
        updateHeight(p);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;

            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            if (parent != null) {
                parentString = parent.element.toString();
            }

            return element + "_p(" + parentString + ")_h(" + height + ")";
        }
    }
}
