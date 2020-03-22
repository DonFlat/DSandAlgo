import java.util.Collection;
import java.util.Collections;

class SortingAlgos {
    /**
     * Insertion Sort
     * Best case: Already sorted O(n). 
     *  Each iteration, current elem only compare with rightmost one in sorted list
     * Worst case: O(n^2) reversed list
     * Avg: O(n^2)
     * STABLE
     * Inplace: O(1) additional memo space
     * @param array
     */
    static void insertion(int[] array) {
        int currentElem = 0;
        int indexBeforeCur = 0;
        for (int i = 0; i < array.length; ++i) {
            currentElem = array[i];
            indexBeforeCur = i - 1;
            /* Just imagine visual algo for this */
            while (indexBeforeCur >= 0 && array[indexBeforeCur] > currentElem) {
                array[indexBeforeCur + 1] = array[indexBeforeCur];
                --indexBeforeCur;
            }
            // When above loop exits, indexBeforeCur is at the elem that just less
            // than currentElem
            // Above loop shifts elem right, since curElem was extracted
            array[indexBeforeCur + 1] = currentElem;
        }
    }

    /**
     * Worst: basically insertion sort
     * avg: depends on gap, O(n^5/4)
     * not stable
     * inplace
     */
    void shell()

    /**
     * Find smallest elems, swap to front of array
     * All: O(n^2) O(n) swap
     * O(1) aux space complexity
     * Unstable consider [2, 2, 1]
     * @param array
     */
    static void selection(int[] array) {
        // Loop through all list
        int minIndex = 0;
        int temp = 0;
        // Start looping through whole list
        // Until second last elem, inclusive
        for (int i = 0; i < array.length-1; ++i) {
            minIndex = i; // Assume first elem in unsorted array be min
            // Loop through unsorted
            // Find out smallest elem in unsorted array
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[minIndex]) // Meet another less elem in unsorted
                    minIndex = j; // Let that minIndex represents smallest one
            }
            // Found least elem, swap to front of array
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
    /**
     * Use a do while loop
     * When loop stops, the case should be no more swap, so swapped is false
     * loop exiting case is swap == false
     * at start of loop, let swapped by false, once swapped, turn to true
     * STABLE, try to image visual algo. You cannot change relative position
     * of two elem by swapping
     * Worst O(n^2) reverse sorted
     * Best O(n) already sorted 
     * O(1) aux space
     * @param array
     */
    static void bubble(int[] array) {
        boolean swapped = true;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    //swap(array[i], array[i + 1]);
                    swapped = true;
                }
            }
        } while (swapped == true);
    }

    /**
     * Notes:
     * should initialise a temp array as long as the sum length of two subarrs
     * You know how to merge next
     * When temp array was filled
     * Be cautious [end-start] is just an subarr, not from 0
     * when filling original array, attention the starting index of temp/original
     * array
     * Just image merging the two subarrays divided from original array
     * 
     * NOTICE THE INDEX. 1.HOW TO OBTAIN ELEMS# between two indices INCLUSIVELY
     * 2. startIndex - midIndex - midIndex+1 - endIndex
     * These shitty numerical relationships
     * @param arr
     * @param start
     * @param mid
     * @param end
     */
    static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                ++i;
            } else {
                temp[k] = arr[j];
                ++j;
            }
            ++k;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            ++k;
            ++i;
        }
        while (j <= end) {
            temp[k] = arr[j];
            ++k;
            ++j;
        }
        for (i = start; i <= end; ++i) {
            arr[i] = temp[i - start];
        }
    }
    
    /**
     * Notes on Merge sort
     * As binary search, you should have first and last index to obtain middle index
     * When program executes to merge function
     * two subarray must have at least one length > 1
     * 
     * Time complexity: Best, Avg, Worst, nlogn
     * Space: n
     * STABLE
     * Why it is stable?
     * ---- When merging, if l <= r, let l insert into temp array first
     */
    static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex)
            return;
        int midIndex = startIndex + (endIndex - startIndex) / 2;
        mergeSort(arr, startIndex, midIndex);
        mergeSort(arr, midIndex + 1, endIndex);
        merge(arr, startIndex, midIndex, endIndex);
    }

    /**
     * Worst n^2, avg best nlogn
     * unstable
     *      imagine [1,1,1,5,5,5,1,4]. let 4 is pivot, when comes to 1 right next to 5
     *      1 and first 5 will swap, so unstable
     * @param arr
     * @param start
     * @param end
     */
    static void quick(int[] arr, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(arr, start, end);
            quick(arr, start, partitionIndex - 1);
            quick(arr, partitionIndex + 1, end);
        }
    }
    
    /**
     * This can avoid unbalanced partition, n^2 -> nlogn
     * @param arr
     * @param start
     * @param end
     * @return
     */
    static int randomisedPartition(int[] arr, int start, int end) {
        int pivotIndex = random(start, end);
        swap(arr[pivotIndex], arr[end]);
        return partition(arr, start, end);
    }


    static int partition(int[] arr, int start, int end) {
        int pivot = arr[end]; // Set last elem as pivot
        int partitionIndex = start; // Finally, this index is the index of the elem which right next to pivot
        /* Discuss this paritionIndex, suppoese have [1, 2, 7, 4, 3, 5]
            at first, i and pI both @ index=0.
            as 1 < 5. will trigger swap, however swap acutally did nothing
            so both i and pI increase 1. 2 (index=1) the same
            When it reach 7(index=2), pI has been raised to 2 in last iteration when 
            i == 2. But this round no swap, so pI will stay @ 7, i + 1
            now i at 4. So swap, [1,2,4,7,3,5] is result. pI will add to 3 (val=7)
            
         */
        for (int i = start; i < end; ++i) { // Currently [end] is pivot, no need go there
            if (arr[i] <= pivot) {
                /* swap(arr[i], arr[partitionIndex]); */
                int temp = arr[i];
                arr[i] = arr[partitionIndex];
                arr[partitionIndex] = temp;

                ++partitionIndex;
                // You literally don't move povitIndex when elem is > pivot
                // What the fuck are you thinking about?
                // There is no more need to think
                // Just think when swap needed, increase pI. Otherwise, don't
            }
        }
        /* swap(arr[end], arr[partitionIndex]); */
        int temp2 = arr[end];
        arr[end] = arr[partitionIndex];
        arr[partitionIndex] = temp2;
        return partitionIndex;
    }

    /**
     * 
     */
    void counting(int[] arry) {

    }

    /**
     * Sort zipcode
     * 
     * Sort on each digit by counting sort
     * On each digit, should use stable sort, otherwise will fail
     */
    void radix(int[] array) {

    }

    public static void main(String[] args) {
        int[] xs = { 5, 2, 1, 8, 9, 0, 10 };
        quick(xs, 0,6);
        for (int x : xs)
            System.out.println(x);
    }
}