#https://www.hackerrank.com/contests/university-codesprint/challenges/kindergarten-adventures
def findMaxOverlapPoint(arrl,exit):
   n=len(arrl)
   arrl.sort()
   exit.sort() 
   guests_in = 1
   max_guests = 1
   time = arrl[0]
   i = 1
   j = 0 
   while (i < n and j < n):
      if (arrl[i] <= exit[j]):
         guests_in+=1
         if (guests_in > max_guests):
            max_guests = guests_in
            time = arrl[i];
         i+=1
      else:
         guests_in-=1
         j+=1
   return time

n=int(input())
intrvl_s=[]
intrvl_e=[]
l=list(map(int,input().split()))
for i in range(n):
   if l[i]>=n:
      continue
   if l[i]==0:
      intrvl_s.append(0)
      intrvl_e.append(n-1)
      continue
   start=i+1
   end=(i-l[i])
   if end<0:
      intrvl_s.append(start)
      intrvl_e.append(end%n)
   else:
      intrvl_s.append(start)
      intrvl_e.append(n-1)
      intrvl_s.append(0)
      intrvl_e.append(end)
#for i in range(len(intrvl_s)):
#   print(intrvl_s[i],intrvl_e[i])
print(findMaxOverlapPoint(intrvl_s,intrvl_e)+1)


