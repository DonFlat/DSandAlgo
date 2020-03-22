package 剑指Offer练习;

class NumOne {
    public static int numberOfOne(int n) {
        int mask = 1, count = 0;
        while (mask != 0) { //This could loops 32 times, as int has 32digit
            if ((mask & n) != 0)
                ++count;
            mask = mask << 1;
        }
        return count;
    }
}