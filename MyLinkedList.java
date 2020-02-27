import java.util.Scanner;

class MyLinkedList {
    /**
     * The inner class, node
     */
    public class Node {
        public int val;
        public Node next;

        public Node(int value) {
            this.val = value;
        }

        public void showValue() {
            System.out.println(val);
        }
    }

    public Node head;
    public MyLinkedList() {}
    /**
     * The constructor
     * @param headNode
     */
    public MyLinkedList(Node headNode) {
        this.head = headNode;
    }
    /**
     * Append a new node to end of list
     * @param n
     */
    public void append(Node newNode) {
        // Loop until last node of list
        Node curNode = head;
        for (; curNode.next != null; curNode = curNode.next)
            ;
        curNode.next = newNode;
    }
    
    /**
     * Delete all occurrence in the list
     * 1. Consider occurrence at head of list
     *      there might be repitition,
     *      hence use a loop to delete,
     *      until meet first unmatched node
     * 2. Delete remaining
     *      Loop until find matched node,
     *      meanwhile keep a prevNode,
     *      Once found, delete it,
     *      after delete, keep looping,
     *      so use an outer while to keep looping
     *      use an inner while to find matched node
     *      If inner loop finished looping, no result found
     *      Just return
     *      if found, delete at outer while's enclosure
     */
    public void delete(int value) {
        // Empty list
        if (head == null)
            return;
        Node prev = head, curNode = head.next;
        // consecutive occurrence at beginning
        while (head != null && head.val == value) {
            head = head.next;
            curNode = head;
        }
        // Delete other occurrence
        while (curNode != null) {
            // Look for occurrence
            while (curNode != null && curNode.val != value) {
                prev = curNode;
                curNode = curNode.next;
            }
            // No more occurrence
            if (curNode == null)
                return;
            // Found occurrence
            // Unlink it, then forward curNode
            prev.next = curNode.next;
            curNode = curNode.next;
        }
    }
    
    /**
     * Print list to console
     */
    public void showList() {
        if (head == null)
            return;
        Node curNode = head;
        for (; curNode.next != null; curNode = curNode.next) {
            System.out.print(curNode.val + "->");
        }
        System.out.print(curNode.val + "\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Initialise Scanner
        int in = sc.nextInt(); // Read first input

        MyLinkedList myList = new MyLinkedList(); // Instantiate outter class
        myList.head = myList.new Node(in); // Instantiate inner class, the head node

        // Create a new node, append to tail of the list
        while (sc.hasNext()) {
            myList.append(myList.new Node(sc.nextInt()));
        }
        // Print out the list
        myList.showList();
        System.out.println("After delete:");
        myList.delete(1);
        myList.showList();
        sc.close();
    }
}
