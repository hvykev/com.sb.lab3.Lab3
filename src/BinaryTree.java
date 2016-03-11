// Copyright(c) 2016 Hung Ta
//
// Simple binary tree interface

package SE330;

abstract public class BinaryTree {
    protected BinaryTreeNode root = null;

    public BinaryTree() {  }

    public void clear()                     { root = null; }
    public boolean isEmpty()                { return root == null; }
    public boolean isInTree(Comparable el)  { return search(el) != null; }

    abstract public Comparable search(Comparable el);
    abstract public void insert(Comparable el);
    abstract public void delete(Comparable el);

    public void preorder()  { preorder(root); }
    public void inorder()   { inorder(root); }
    public void postorder() {
        postorder(root);
    }

    ///////////////////////////////////////////////////////////////////

    // override visit() to implement node processing logic
    protected void visit(BinaryTreeNode p) {
        System.out.print(p.el + " ");
    }

    protected void inorder(BinaryTreeNode p) {
        if (p != null) {
            inorder(p.left);
            visit(p);
            inorder(p.right);
        }
    }
    protected void preorder(BinaryTreeNode p) {
        if (p != null) {
            visit(p);
            preorder(p.left);
            preorder(p.right);
        }
    }
    protected void postorder(BinaryTreeNode p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            visit(p);
        }
    }

    ///////////////////////////////////////////
    // Pretty-print the tree - this code is modified from sample code on stackoverflow
    public StringBuilder toString(BinaryTreeNode node, StringBuilder prefix, boolean isNoMoreChild, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isNoMoreChild ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isNoMoreChild ? "└── " : "┌── ").append(node.el.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isNoMoreChild ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }
}
