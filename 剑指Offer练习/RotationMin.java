package 剑指Offer练习;

class RotationMin {
    public static int minNumberInRotateArray(int [] array) {
        if (array.length == 0) return 0;
        int front = 0, back = array.length - 1;
        int mid = front;
        while (array[front] >= array[back]) {
            if (front + 1 == back) {
                mid = back;
                break;
            }
            mid = front + (back - front) / 2;
            
            if (array[mid] < array[back]) {
                back = mid;
            } else if (array[mid] > array[front]) {
                front = mid;
            } else {
                front = mid;
            }
        }
        return array[mid];
    }
    //此题解法有误，考虑情况挺多的，参考讨论区
    public static void main(String[] args) {
        int[] arr = { 4, 2, 3, 3, 3 };
        System.out.println(minNumberInRotateArray(arr));
    }
}