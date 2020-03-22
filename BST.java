import java.util.*;
class BST {
    class Node {
        int value;
        Node left;
        Node right;

        Node(int val) {
            this.value = val;
        }

        Node insert(int val, Node root) {
            if (root == null) {
                return new Node(val);
            } else if (val < root.value) {
                root.left = insert(val, root.left);
            } else if (val > root.value) {
                root.right = insert(val, root.right);
            } else {
            }
            return root;
        }

        void pre(Node root) {
            if (root == null)
                return;
            System.out.println(root.value);
            pre(root.left); // Why does root.left.pre() won't work?
            pre(root.right);
        }

        void in(Node root) {
            if (root == null)
                return;
            in(root.left);
            System.out.println(root.value);
            in(root.right);
        }

        void post(Node root) {
            if (root == null)
                return;
            post(root.left);
            post(root.right);
            System.out.println(root.value);
        }
    }

    Node leftRotation(Node root) {
        if (root == null)
            return null;
        Node temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }

    Node rightRotation(Node root) {
        if (root == null)
            return null;
        Node temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    Node leftRightRotation(Node root) {
        root.left = leftRotation(root.left);
        return rightRotation(root);
    }

    Node rightLeftRotation(Node root) {
        root.right = rightRotation(root.right);
        return leftRotation(root);
    }

    boolean lookup(int target, Node root) {
        if (root == null)
            return false;
        if (target == root.value)
            return true;
        if (target < root.value)
            return lookup(target, root.left);
        else
            return lookup(target, root.right);
    }

    /**
        only think about there is only one node, the root is there
        maxD(root.left/right) will get 0. Finally + 1, the 1 represents
        the root node
     */
    int maxDepth(Node root) {
        if (root == null)
            return 0;
        int lDepth = maxDepth(root.left);
        int rDepth = maxDepth(root.right);
        return lDepth > rDepth ? (lDepth + 1) : (rDepth + 1);
    }

    /**
     * Count numbers of edges alongside the longest path in the tree
     */
    int maxDepthEdge(Node root) {
        // Just notice the difference between "by nodes"
        if (root == null)
            return -1;
        int lDepth = maxDepth(root.left) + 1;
        int rDepth = maxDepth(root.right) + 1;
        return lDepth > rDepth ? (lDepth) : (rDepth);
    }
    
    static int nodeNumber(Node root) {
        if (root != null) {
            int leftNum = nodeNumber(root.left);
            int rightNum = nodeNumber(root.right);
            return leftNum + rightNum + 1;
        } else
            return 0;
    }

    static int width(Node root) {
        if (root == null) 
            return 0;
        else if (root.left == null && root.right == null) 
            return 1;
        else 
            return width(root.left) + width(root.right);
        
    }

    int maxVal(Node root) {
        return 0;
    }

    int minVal(Node root) {
        if (root == null)
            return 0;
        Node temp = root;
        for (; temp.left != null; temp = temp.left)
            ;
        return temp.value;
    }

    /**
     * Find out all the path that sum up to target
     * Solution: Every level of recursion, use target-root.val,
     *          so that we don't need to sum all elements in the list
     * Just use one temp ArrayList, if meets target sum, use copy constructor?
     * to append the ArrayList of ArrayList
     * Use prefix traversal
     * end of each level recursion, remove last element, as this route is unable to 
     * make up the sum
     */
    private ArrayList<ArrayList<Integer>> totalList = new ArrayList<>();
    private ArrayList<Integer> oneList = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(Node root, int target) {
        if (root == null)
            return totalList;
        target -= root.value;
        oneList.add(root.value);
        if (target == 0 && root.left == null && root.right == null) {
            totalList.add(new ArrayList<Integer>(oneList));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        oneList.remove(oneList.size() - 1);
        return totalList;
    }
    
    /**
     * Duplicate a tree's node, put in its left
     * http://cslibrary.stanford.edu/110/BinaryTrees.html (Answer is also here)
     * Before inserting the new node, should keep its original left
     * Then link new node's left to original
     * Whole process done by post-order
     * @param root
     */
    void doubleTree(Node root) {
        if (root == null)
            return;
        doubleTree(root.left);
        doubleTree(root.right);
        Node oldLeft = root.left;
        root.left = new Node(root.value);
        root.left.left = oldLeft;
    }

    /**
     * What makes a BST?
     * max node at left sub should not greater than root
     * @param root
     * @return
     */
    boolean isBST(Node root) {
        if (root == null)
            return true;
        if (root.left != null && minVal(root.left) > root.value)
            return false;
        if (root.right != null && maxVal(root.right) < root.value)
            return false;
        return isBST(root.left) && isBST(root.right);
    }

    /**
     * A better solution other than traverse them
     * Just compare the root, recurse to same direction
     * @param root
     */
    boolean identicalTrees(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            return root1.value == root2.value &&
                    identicalTrees(root1.left, root2.left) &&
                    identicalTrees(root1.right, root2.right);
        } else
            return false;
    }

    /**
     * 经典问题：反转二叉树，
     * 前序遍历树，交换每个根的左右Subtree
     * @param root
     */
    void reverse(Node root) {
        if (root == null)
            return;
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        reverse(root.left);
        reverse(root.right);
    }

    Node root;

    BST(int[] xs) {
        // Create root of the tree
        root = new Node(xs[0]);
        for (int i = 1; i < 6; ++i) {
            root = root.insert(xs[i], root);
        }
    }

    Node deleteNode(int val) {
        
    }

    void preShow() {
        root.pre(root);
    }

    void inShow() {
        root.in(root);
    }
    public static void main(String[] args) {
        int[] xs = { 4, 2, 1, 3, 6, 5, 7 };
        BST t = new BST(xs);
        t.preShow();
        /*System.out.println(t.lookup(7, t.root));
        System.out.println(t.maxDepth(t.root));
        System.out.println(t.minVal(t.root)); */
        System.out.println(width(t.root));
    }
}