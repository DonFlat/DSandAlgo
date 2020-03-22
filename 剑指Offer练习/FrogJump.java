package 剑指Offer练习;

// 使用小矩形覆盖大矩形也是如此
class FrogJump {
    public int JumpFloorII(int target) {
        if (target == 0) return 1;
        if (target == 1) return 1;
        int x_1 = 1, x_2 = 1;
        int x = 0;
        for (int i = 2; i <= target; ++i) {
            x = 2 * x_1;
            x_1 = x;
        }
        return x;
    }
}