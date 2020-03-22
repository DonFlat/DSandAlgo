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

    /* public Node zipList(Node list1, Node list2) {
        // Consider case that there is one list empty
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        
        Node head = null;
        if (list1.val < list2.val) {
            head = list1;
            head.next = Merge(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge(list1, list2.next);
        }
        return head;
    } */
    
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

    // IMPORTANCE!!!
    /* Summary: after consider none&one node case, handle >= 2 nodes
        use three reference, store post first, then let cur points to prev
        then move forward prev and cur

        inside loop, do assign post...
    */
    public Node ReverseList(Node head) {
        if (head == null || head.next == null) // Consider no elem, one elem case
            return head;
        Node prev = null, cur = head, post = null; // Need three to record
        while(cur != null) { // In last iteration, cur will go to null, as post is at null
            post = cur.next; // Let post store next address, as cur's next will be set, cur->post will disconnect
            cur.next = prev; // turn prev cur->post to prev<-cur post
            prev = cur; // Forward prev
            cur = post; // forward cur
        }
        return prev;
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

    public static boolean detectLoop(Node head) {
        if (head == null)
            return false;
        Node slow = head;
        Node fast = head;
        // Analyse this exit condition
        // If no loop
        //  odd numbers of nodes, fast will stop at tail node
        //  even number, will stop just at null right after tail node
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    public void makeLoop() {
        Node cur = head;
        for (; cur.next != null; cur = cur.next)
            ;
        cur.next = head;
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
        System.out.println("After revserse:");
        myList.head = myList.ReverseList(myList.head);
        myList.showList();
        
        myList.makeLoop();

        System.out.println(detectLoop(myList.head));
        sc.close();
    }
}
