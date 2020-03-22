package 剑指Offer练习;

import java.util.ArrayList; // 原题使用ArrayList
import java.util.Stack; // 使用Stack的解法需要import

class ReverseStoreList {
    class ListNode {
        int val;
        ListNode next = null;
    }
    // 使用Stack的解法 17ms 9388k
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> elems = new Stack<>();
        for (ListNode cur = listNode; cur != null; cur = cur.next) {
            elems.push(cur.val);
        }
        ArrayList<Integer> al = new ArrayList<>();
        while (!elems.empty()) {
            al.add(elems.pop());
        }
        return al;
    }
    // 递归解法 14ms 9052k
    private void recursiveAdd(ListNode listNode, ArrayList al) {
        if (listNode == null) return;
        recursiveAdd(listNode.next, al);
        al.add(listNode.val);
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> al = new ArrayList<>();
        recursiveAdd(listNode, al);
        return al;
    }
}