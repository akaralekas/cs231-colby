/*
 * File: BinaryTree.java
 * Author: Anthony Karalekas
 * Help: CP Majgaard
 * Worked with: Brendan Doyle and Steven Parrott
 * Date: Nov. 22, 2015
 * Assignment: Project 8
 */


public class BinaryTree{

    private TreeNode root;


    public BinaryTree() { 
    	root = null; 
    }

    public int total() {
        if (this.root == null) {
            return 0;
        }
        else {
            return this.root.total();
        }
    }

    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree();
        tree1.root = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        System.out.println(tree1.total());

        BinaryTree tree2 = new BinaryTree();
        tree2.root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6, new TreeNode(7), new TreeNode(8))));
        System.out.println(tree2.total());
    }

}

class TreeNode{

    public static final NullTreeNode NULL_NODE = new NullTreeNode();

    public int data;
    public TreeNode left, right;

    public TreeNode(int d, TreeNode l, TreeNode r) {
        this.data = d;
        this.left = l;
        this.right = r;
        if (this.left == null) {
            this.left = NULL_NODE;
        }
        if (this.right == null) {
            this.right = NULL_NODE;
        }
    }

    public TreeNode(int d) {
        this(d, null, null);
    }


    public int total() {
        return this.data + this.left.total() + this.right.total();
    }
}

class NullTreeNode extends TreeNode{

    public NullTreeNode() { super(0, null, null); }

    public int total() { return 0; }
}

