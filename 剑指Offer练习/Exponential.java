package 剑指Offer练习;

// 需要考虑exp为负时
// 需要考虑base或exp其中之一为0时
class Exponential {
    static double power(double base, int exponent) {
        if (base == 0 && (exponent == 1 || exponent == 0))
            return 0;
        
        int absExp = 0;
        if (exponent < 0)
            absExp = -exponent; 
        else 
            absExp = exponent;
        double result = 1.0;
        for (int i = 1; i <= absExp; ++i) {
            result *= base;
        }
        if (exponent < 0)
            result = 1 / result;
            
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 3));
    }
}