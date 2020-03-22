class BinarySeach {
    /**
     * x is to be find
     * 
     * Smt to notice:
     * • when cannot find the x, lo > hi will be reached
     * • To prevent integer overflow, since it might happen if lo+hi > max(int)
     *   so use lo + (hi-lo)/2, intuitively move forward lo
     */
    static int search(int[] arr, int lo, int hi, int x) {
        if (lo > hi) {
            return -1;
        } else {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < x)
                return search(arr, mid+1, hi, x);
            else if (arr[mid] > x)
                return search(arr, lo, mid - 1, x);
            else
                return mid;
        }
    }
    public static void main(String[] args) {
        int[] xs = { 1, 2, 3, 4, 5, 6 };
        System.out.println(search(xs, 0, 5, 4));
    }
}