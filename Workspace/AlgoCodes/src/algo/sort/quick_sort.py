#lomuto partitioning
def partition(l,low,high):
   pivot=l[high]
   pIndex=low
   for i in range(low,high):
      if l[i]<=pivot:#doesnt matter if its <= or <
         l[i],l[pIndex]=l[pIndex],l[i]
         pIndex+=1
   l[high],l[pIndex]=l[pIndex],l[high]
   return pIndex

def print_list(l):
   print(' '.join(map(str,l)))
   
def quick_sort(l,low,high):
   if low<high:
      pIndex=partition(l,low,high)
      print_list(l)
      quick_sort(l,low,pIndex-1)
      quick_sort(l,pIndex+1,high)
      
      
n=int(input())
l=list(map(int,input().split()))
quick_sort(l,0,n-1)