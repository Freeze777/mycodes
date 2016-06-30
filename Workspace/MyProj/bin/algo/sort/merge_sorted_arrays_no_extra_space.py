class Solution(object):
    #l1 has enough space to accomodate l2
    def merge(self, l1, m, l2, n):
        k=m+n-1
        i=m-1
        j=n-1
        while k>=0:
            if i<0 and j>=0:
               l1[k]=l2[j]
               j-=1
            elif i>=0 and j<0:
               l1[k]=l1[i]
               i-=1
            elif l1[i]>l2[j]:
                l1[k]=l1[i]
                i-=1
            else:
                l1[k]=l2[j]
                j-=1
            k-=1
        