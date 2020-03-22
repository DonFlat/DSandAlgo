/**
 * Red black tree rules;
 * 
 */
public class RedBlackTree<K, V> implements RedBlackI<k, v> {
    Node<K,V> root;
    int size;

    // Need a parent, to see aunt
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right, parent;
        boolean isLeftChild, black;
        // Constructor doesn't have value
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = parent = null;
            black = false;
            isLeftChild = false;
        }
    }

    public void add(K key, V value) {
        Node<K, V> node = new Node<K, V>(key, value);
        if (root == null) {
            root = node;
            root.black = true;
            ++size;
            return;
        }
        add(root, node);
        ++size;
        checkColour(newNode);
    }

    private void add(Node<K, V> parent, Node<K, V> newNode) {
        if (((Comparable<K>) newNode).key.compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
                return;
            }
            return add(parent.right, newNode);
        }
        if (parent.left == null) {
            parent.left = newNode;
            newNode.parent = parent;
            newNode.isLeftChild = true;
            return;
        }
        return add(parent.left, newNode);
    }

    public void checkColour(Node<K, V> node) {
        if (node == root)
            return;
        if (!node.black && !node.parent.black)
            correctTree(node);
        checkColour(node.parent); // What if correct introduce a new
    }

    // One note to remember, fix at grandparent
    public void correctTree(Node<K, V> node) {
        // We need to figure out where is aunt
        // so here is isLeftChild comes to effect
        // parent is left, then aunt is right
        if (node.parent.isLeftChild) {
            // Aunt is node.par.par.right
            // We don't know if there is aunt, maybe null
            // For black aunt, rotate. Result is "R-B-R", 
            // "B" is the grandparent
            if (node.parent.parent == null || node.parent.parent.right.black)
                return rotate(node);
            // Colour flip, when aunt is red. B-R-B
            if (node.parent.parent.right != null)
                node.parent.parent.right.black = true;
            node.parent.parent.black = false;
            node.parent.black = true;
            return;
        }
        // Aunt is grand's left...
    }
    /**
     * Rotation quick summary at RBT lesson 6
     * 1:44
     */
    public void rotate(Node<K, V> node) {
        if (node.isLeftChild) {
            if (node.parent.isLeftChild) {
                rightRotate(node.parent.parent);
                node.black = false;
                node.parent.black = true;
                if (node.parent.right != null)
                    node.parent.right.black = false;
                return;
            }
            rightLeftRotate(node.parent.parent);
            node.black = true;
            node.right.black = false;
            node.left.black = false;
            return;
        }
        // Handle right child...
    }

    public void leftRotate(Node<K, V> node) {
        Node<K, V> temp = node.right;
        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        if (node.parent == null) {
            // We are on root node
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            // ???
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
    }

    public void leftRightRotate(Node<K, V> node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    public int height() {
        if (root == null)
            return 0;
        return height(root) - 1;
    }

    public int height(Node<K, V> node) {
        if (node == null)
            return 0;
        // We are like make a promise here
        // When recursion finishes, current level will be added
        // Think like this: everytime it recurs, level + 1
        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;
        return leftHeight > rightHeight ? leftHeight : rightHeight;
    }

    public int blackNodes(Node<K, V> node) {
        if (node == null) 
            return 1;
        int rightBlack = blackNodes(node.right);
        int leftBlack = blackNodes(node.left);
        // If this happens, we need to do sth
        if (rightBlack != leftBlack) 

        if (node.black)
            leftBlack++;
        return leftBlack;
    }
}