from heapq import heappush,heappop 
class Solution(object):
    def nthUglyNumber(self, n):
        heap=[]
        seen=set()
        heappush(heap,1)
        for i in range(n):
            ans=heappop(heap)
            n1=ans*2
            n2=ans*3
            n3=ans*5
            if n1 not in seen:
                heappush(heap,n1)
                seen.add(n1)
            if n2 not in seen:
                heappush(heap,n2)
                seen.add(n2)
            if n3 not in seen:
                heappush(heap,n3)
                seen.add(n3)
        return ans
            
        
        