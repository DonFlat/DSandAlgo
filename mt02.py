# 给出一个长度为n的由正整数构成的序列，你需要从中删除一个正整数，很显然你有很多种删除方式，你需要对删除这个正整数以后的序列求其最长上升子串，请问在所有删除方案中，最长的上升子串长度是多少。
#
# 这里给出最长上升子串的定义：即对于序列中连续的若干个正整数，满足a_{i+1}>a_i，则称这连续的若干个整数构成的子串为上升子串，在所有的上升子串中，长度最长的称为最长上升子串。

# 输入第一行仅包含一个正整数n，表示给出的序列的长度。(1<=n<=100000)
#
# 接下来一行有n个正整数，即这个序列，中间用空格隔开。(1<=a_i<=100000)

# 输出仅包含一个正整数，即删除一个数字之后的最长上升子串长度。

def to_int(L):
    ret = list()
    for i in L:
        ret.append(int(i))
    return ret

# current_last inital value as L[0]-1
def LIS(n, L):
    ret = 0
    curr = 1
    for i in range(1, n):
        if L[i]>L[i-1]:
            curr+=1
        else:
            ret = max(ret, curr)
            curr = 1
    else:
        ret = max(ret, curr)
    return ret


def sol(n, L):
    # scan all possible cut
    ret = 0
    for i in range(1,n-1):
        if (L[i]<=L[i-1] and L[i+1]>L[i-1]) or (L[i]>=L[i+1] and L[i+1]>L[i-1]):
            ret = max(ret, LIS(n-1, L[:i]+L[i+1:]))
    return ret

if __name__ == '__main__':
    s = input()
    n = int(s)
    s = input()
    L = to_int(s.split())

    print(sol(n, L))