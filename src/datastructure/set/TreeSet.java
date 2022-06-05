package datastructure.set;

import datastructure.tree.BinaryTree;
import datastructure.tree.Comparator;
import datastructure.tree.RedBlackTree;

public class TreeSet<E> implements Set<E>{
    private RedBlackTree<E> tree = new RedBlackTree<>();

    public TreeSet() {
        this(null);
    }

    // 用红黑树则元素必须具有可比较性
    public TreeSet(Comparator<E> comparator) {
        tree = new RedBlackTree<E>(comparator);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);  // 我们的红黑树性质：如果相同则覆盖，不相同则追加
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor visitor) {
        tree.inOrder(new BinaryTree.Visitor<E>() {
            @Override
            public boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
