package datastructure.tree;

public class BalancedBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalancedBinarySearchTree() {
        this(null);
    }

    public BalancedBinarySearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> p = grand.right;
        Node<E> child = p.left;

        grand.right = child;
        p.left = grand;

        afterRotate(grand, p, child);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> p = grand.left;
        Node<E> child = p.right;

        grand.left = child;
        p.right = grand;

        afterRotate(grand, p, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> p, Node<E> child) {
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
    }

    // 对于 AVL 树：a, g 可以不处理
    protected void rotate(
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

        // e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;

        b.parent = d;
        f.parent = d;
    }
}
