package 剑指Offer练习;

import java.util.Stack;

public class StackToQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /* Push all element from stack1 to stack2
        will reverse order of pop
        so always enqueue new element to stack1
        when dequeue, pour all elements to stack2 then pop stack2
    */
    // 14ms 9408ms
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}