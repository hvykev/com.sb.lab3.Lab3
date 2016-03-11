// Copyright(c) 2016 Hung Ta
//
// A balanced BST - implementing DSW

package SE330;

public class BalancedBST extends BST {
    boolean bPrintTree = false;  // for debugging

    public BalancedBST() {
    }

    @Override
    public void insert(Comparable el) {
        super.insert(el);
        balance();
    }

    @Override
    public void delete(Comparable el) {
        super.delete(el);
        balance();
    }

    // Implement DSW to balance BST
    public void balance() {
        if (root != null) {
            if (bPrintTree) System.out.println("===== BEFORE balance() =====\n" + this);
            createBackbone();
            if (bPrintTree) System.out.println("----- BACKBONE -----\n" + this);
            createPerfectBST();
            if (bPrintTree) System.out.println("----- AFTER balance() -----\n" + this);
        }
    }

    private void createBackbone() {
        BinaryTreeNode grandParent = null;
        BinaryTreeNode parent = root;
        BinaryTreeNode leftChild;

        while (parent != null) {
            leftChild = parent.left;
            if (leftChild != null) {
                grandParent = rotateRight(grandParent, parent, leftChild);
                parent = leftChild;
            } else {
                grandParent = parent;
                parent = parent.right;
            }
        }
    }

    /////////////////////////////
    //   Before      After
    //     Gr          Gr
    //      \           \
    //     Par         Ch
    //    /  \        /  \
    //   Ch   U      L   Par
    //  /  \            /  \
    // L    R          R    U
    ////////////////////////////
    private BinaryTreeNode rotateRight(BinaryTreeNode grandParent, BinaryTreeNode parent, BinaryTreeNode leftChild) {
        if (grandParent != null) {
            grandParent.right = leftChild;
        } else {
            root = leftChild;
        }
        parent.left = leftChild.right;
        leftChild.right = parent;
        return grandParent;
    }

    private BinaryTreeNode rotateLeft(BinaryTreeNode grandParent, BinaryTreeNode parent, BinaryTreeNode rightChild) {
        if (grandParent != null) {
            grandParent.right = rightChild;
        } else {
            root = rightChild;
        }
        parent.right = rightChild.left;
        rightChild.left = parent;
        return rightChild;
    }

    private void createPerfectBST() {
        int n = 0;
        for (BinaryTreeNode tmp = root; null != tmp; tmp = tmp.right) {
            n++;
        }
        //m = 2^floor[lg(n+1)]-1, ie the greatest power of 2 less than n: minus 1
        int m = powerOf2LessThanN(n + 1) - 1;
        rotate(n - m);

        while (m > 1) {
            rotate(m /= 2);
        }
    }

    private int powerOf2LessThanN(int n) {
        int index = 0;
        while (1 < n) {
            n = (n >> 1);
            index++;
        }
        int x = index;
        return (1 << x);  //2^x
    }

    private void rotate(int bound) {
        BinaryTreeNode grandParent = null;
        BinaryTreeNode parent = root;
        BinaryTreeNode child = root.right;
        for (; bound > 0; bound--) {
            try {
                if (child != null) {
                    grandParent = rotateLeft(grandParent, parent, child);
                    parent = grandParent.right;
                    child = parent.right;
                } else {
                    break;
                }
            } catch (NullPointerException exc) {
                break;
            }
        }
    }

}
