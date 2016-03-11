// Copyright(c) 2016 Hung Ta
//
package SE330;

public class BinaryTreeNode {
    protected Comparable el;
    protected BinaryTreeNode left, right;

    public BinaryTreeNode() {
        left = right = null;
    }
    public BinaryTreeNode(Comparable el) {
        this(el,null,null);
    }
    public BinaryTreeNode(Comparable el, BinaryTreeNode lt, BinaryTreeNode rt) {
        this.el = el; left = lt; right = rt;
    }
}
