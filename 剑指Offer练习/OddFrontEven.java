package 剑指Offer练习;

class OddFrontEven {
    static void reOrder(int[] array) {
        int temp = 0;
        if (array.length == 0)
            return;
        int front = 0, back = array.length - 1;
        while (front < back) {
            // Look for an even number in array's frontier
            while (front < back && (array[front] % 2 != 0)) {
                ++front;
            }
            // Look for an odd number in array's back
            while (front < back && (array[back] % 2 == 0)) {
                --back;
            }

            if (front < back) {
                temp = array[front];
                array[front] = array[back];
                array[back] = temp;
            }
        }
    }
}