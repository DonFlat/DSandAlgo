#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Revsering a string, a char at index i, its corresponding char at
    back of the string has index (len-i-1), just imagine i = 0 <----> 
    i = len-1-0. i = 1 <---> i = len-1-1

    In loop, you only need to stop at len/2. Don't worry about even/odd strlen
    • even strlen, Last iteration will be at last index in first half of string
      Consider "Tute"
    • Odd, Last iteration is at just left index to the right middle index
        just right index to the right middle index will be the one swapped with above
        middle one don't swap
        Think about "Hello" as example, strlen == 5 
        i = 2, 5-2-1=2
    • In summary, just go first half of string, swap i with len-i-1
*/
int main() {
    char str[] = "Tute";
    int len = 4;
    char temp = 's';
    printf("%lu %lu\n", strlen(str), sizeof(str));
    for (int i = 0; i < len / 2; ++i)
    {
        temp = str[i];
        str[i] = str[len - i - 1];
        str[len - i - 1] = temp;
    }
    printf("%s\n", str);

    return 0;
}