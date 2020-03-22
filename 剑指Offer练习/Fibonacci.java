package 剑指Offer练习;

// 此题与青蛙跳台阶同，但是跳台阶是从0,1,2,3开始
class Fibonacci {
    static int calculate(int n) {
        int x_2 = 0;
        int x_1 = 1;
        if (n == 0)
            return x_2;
        if (n == 1)
            return x_1;
        int x = 0;
        for (int i = 2; i <= n; ++i) {
            x = x_1 + x_2;
            x_2 = x_1;
            x_1 = x;
        }

        return x;
    }
}