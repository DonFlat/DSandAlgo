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

    Node root;

    BST(int[] xs) {
        // Create root of the tree
        root = new Node(xs[0]);
        for (int i = 1; i < 7; ++i) {
            root = root.insert(xs[i], root);
        }
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
    }
}