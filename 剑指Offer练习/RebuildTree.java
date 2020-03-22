package 剑指Offer练习;

class RebuildTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    // Prefix: head of list is the root
    // Infix: Left of root which obtained from Prefix is left subtree, same for right
    // 207ms 24096k
    // Perhaps it can be better if pass index instead of copying a new array?
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) return null;
        int rootValue = pre[0];
        // Find out index of root in infix
        int i = 0;
        for (; i < in.length; ++i) {
            if (in[i] == rootValue) break;
        }
        int[] leftSubtreeIn = java.util.Arrays.copyOfRange(in, 0, i);
        int[] rightSubtreeIn = java.util.Arrays.copyOfRange(in, i+1, in.length);
        
        int leftLen = leftSubtreeIn.length;
        int rightLen = rightSubtreeIn.length;
        
        int[] leftSubtreePre = java.util.Arrays.copyOfRange(pre, 1, 1+leftLen);
        int[] rightSubtreePre = java.util.Arrays.copyOfRange(pre, 1+leftLen, pre.length);
        
        TreeNode root = new TreeNode(rootValue);
        root.left = reConstructBinaryTree(leftSubtreePre, leftSubtreeIn);
        root.right = reConstructBinaryTree(rightSubtreePre, rightSubtreeIn);
        return root;
    }

}