#https://www.codechef.com/OCT16/problems/CHDOGS#
t=int(input())
for i in range(t):
    s,v=map(float,input().split())
    t=(2*s)/(3*v)
    print("%.9f" % round(t,9)) 