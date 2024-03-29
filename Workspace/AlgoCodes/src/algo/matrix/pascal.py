class Solution(object):
    def generate(self, n):
        if n==0:
            return [];
        if n==1:
            return [[1]]
        if n==2:
            return [[1],[1,1]]
        l=[[1],[1,1]]
        for i in range(3,n+1):
            l.append([0]*i)
            t=l[i-1]
            t[0]=1
            t[i-1]=1
            p=l[i-2]
            for j in range(1,i-1):
                t[j]=p[j]+p[j-1]
        return l