// Copyright(c) 2016 Hung Ta
//
// Binary search tree

package SE330;

public class BST extends BinaryTree
{
    public BST()
    {
    }

    // Override parent search()
    @Override
    public Comparable search(Comparable el)
    {
        return search(root,el);
    }

    protected Comparable search(BinaryTreeNode p, Comparable el)
    {
        while (p != null)
        {
            if (el.equals(p.el))
                return p.el;
            else // advance p to traverse down the tree
                p = (el.compareTo(p.el) < 0) ? p.left : p.right;
        }
        return null;
    }

    @Override
    public void insert(Comparable el)
    {
        BinaryTreeNode p = root, prev = null;
        while (p != null)
        {  // find a place for inserting new node;
            prev = p;
            p = (p.el.compareTo(el) < 0) ? p.right : p.left;
        }
        if (root == null)    // tree is empty;
            root = new BinaryTreeNode(el);
        else if (prev.el.compareTo(el) < 0)
            prev.right = new BinaryTreeNode(el);
        else prev.left  = new BinaryTreeNode(el);
    }

    @Override
    public void delete(Comparable el)
    {
        deleteByMerging(el);
    }

    public void deleteByMerging(Comparable el)
    {
        BinaryTreeNode tmp, node, p = root, prev = null;// find the node p
        while (p != null && !p.el.equals(el))// with element el;
        {
            prev = p;
            if (p.el.compareTo(el) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.el.equals(el))
        {
            if (node.right == null) // node has no right child: its left
                node = node.left;  // child (if any) is attached to its parent;
            else if (node.left == null) // node has no left child: its right
                node = node.right; // child is attached to its parent;
            else
            {                  // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right;      //    possible;
                tmp.right =        // 3. establish the link between the
                        node.right;    //    the rightmost node of the left
                //    subtree and the right subtree;
                node = node.left;  // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }

    public void deleteByCopying(Comparable el)
    {
        BinaryTreeNode node, p = root, prev = null;// find the node p
        while (p != null && !p.el.equals(el))// with element el;
        {
            prev = p;
            if (p.el.compareTo(el) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;

        if (p != null && p.el.equals(el))
        {
            if (node.right == null)             // node has no right child;
                node = node.left;
            else if (node.left == null)         // no left child for node;
                node = node.right;
            else
            {
                BinaryTreeNode tmp = node.left;       // node has both children;
                BinaryTreeNode previous = node;       // 1.
                while (tmp.right != null)
                {    // 2. find the rightmost
                    previous = tmp;            //    position in the
                    tmp = tmp.right;           //    left subtree of node;
                }
                node.el = tmp.el;              // 3. overwrite the reference
                //    of the element being deleted;
                if (previous == node)          // if node's left child's
                    previous.left  = tmp.left; // right subtree is null;
                else previous.right = tmp.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }

}
