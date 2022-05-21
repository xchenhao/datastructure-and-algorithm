package datastructure.tree;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;

        // 前序遍历
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        // 后序遍历
        invertTree(root.left);
        invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;

        // 中序遍历
        invertTree(root.left);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 注意是 root.left（即原来的 right）
        invertTree(root.left);

        return root;
    }

    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return root;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }


        return root;
    }
}
