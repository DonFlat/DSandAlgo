import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * This is a max heap, max val is at root
 */
class IntHeap {
    ArrayList<Integer> heap;
    int size;

    IntHeap() {
        this.heap = new ArrayList<>();
    }

    IntHeap(int size) {
        this.heap = new ArrayList<>(size);
    }

    void insert(int val) {
        heap.add(val);
        percolationUp();
    }

    void percolationUp() {
        int k = heap.size() - 1; // k is the position of newly added elem
        while (k >= 0) {
            int parentOfK = (k - 1) / 2;
            if (heap.get(parentOfK) < heap.get(k)) {
                Collections.swap(heap, parentOfK, k);
                k = parentOfK;
            } else
                break;
        }
    }

    int delete() throws NoSuchElementException {
        if (heap.size() == 0)
            throw new NoSuchElementException();
        if (heap.size() == 1)
            return heap.remove(0);
        else {
            int v = heap.get(0);
            heap.set(0, heap.remove(heap.size() - 1));
            percolateDown();
            return v;
        }
    }

    void percolateDown() {
        int k = 0; // Position of the smallest elem
        int l = 2 * k + 1;
        while (l < heap.size()) { // if l is out of bound, implying no need to go further
            int r = l + 1;
            int maxInLR = l;
            if (r < heap.size()) { // There is a right child
                if (heap.get(l) < heap.get(r)) // Left child less than right
                    ++maxInLR;
            }
            if (heap.get(k) < heap.get(maxInLR)) {
                Collections.swap(heap, k, maxInLR);
                k = maxInLR;
                l = 2 * k + 1;
            } else
                break;
        }
    }

    void printHeap() {
        for (int x : heap)
            System.out.println(x);
        System.out.println("Over");
    }
    
    // I already have a heap builder. Why use heapify?
    // When build heap, use my interface
    // Deleting and percolate down is same
    /**
     * heap sort, use the array's elems build a max heap at first
     * Then reversely filling array by polling from heap
     * 
     * Best avg worst nlogn 
     * Space O(1)
     * Unstable
     * @param arr
     */
    static void heapSort(int[] arr) {
        IntHeap h = new IntHeap();
        for (int x : arr)
            h.insert(x);
        //h.printHeap();
        for (int i = arr.length - 1; i >= 0; --i) {
            int temp = h.delete();
            //System.out.println(temp);
            arr[i] = temp;
        }
    }

    // I think you still need to use heapify ?? WTF?
    public static void main(String[] args) {
        int[] xs = { 10, 20, 11, 5, 8, 3, 2, 0, 1 };
        heapSort(xs);
        for (int x : xs)
            System.out.println(x);
        
    }
}