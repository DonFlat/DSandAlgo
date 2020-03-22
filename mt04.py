# 晨晨是个爱跑步的孩子，这一天，他准备跑正好k米。他所在的城市的道路可以看做n个点，m条无向边组成的图，每条边有一个固定的长度。
#
# 晨晨有强迫症，他跑步前往一个目的地一定要走最短路（当然有多条最短路就可以随意选择了）。
#
# 晨晨希望知道，他正好跑k米能走到的目的地的个数。注意，目的地可能在图中的点和边上，且该目的地距离晨晨的起点的最短路正好k米。
#
# 若k大于所有路径之和自然不存在这样的目的地，输出结果自然为0。

# 第一行输入三个数,n,m,s代表图中的点数，边数，以及晨晨的起点的编号
#
# 接下来m行，每行3个数u,v,w描述一条无向边，代表点u到点v有一条无向边，长度为w。
#
# 接下来一行一个数k，描述晨晨希望跑的距离。
# 3 3 1
# 1 2 2
# 2 3 3
# 1 3 4
# 4


def to_int(L):
    ret = list()
    for i in L:
        ret.append(int(i))
    return ret

def sol(s, k, L):
    ret = 1
    for ele in L:

        return sol(s, k, L)


if __name__ == '__main__':
    s = input()
    L = to_int(s.split())
    n = L[0] # vertex
    m = L[1] # #edge
    s = L[2] # start
    L = list()
    for _ in range(m):
        s = input()
        L.append(to_int(s.split()))
    sol(s, k, L)
