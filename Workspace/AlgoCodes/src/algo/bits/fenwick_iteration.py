#Codechef
#Fenwick Iterations
#
import sys
#sys.setrecursionlimit(1000000)
t=int(input())
#map(int,input().split())
#list(map(int,input().split()))
for i in range(t):
   s=input().strip().split()
   n=int(s[3])
   l1=""
   l2=""
   l3=""
   if s[2][-1]=='0': #lsb of L3 is 0
      print(1+s[0].count('1')+(n*(s[1].count('1')))+s[2].count('1'))
   else: #lsb of L3 is 1
      l3=bin(int(s[2],2)+1)[2:]
      #these were the culprits (== changed to <= all test cases passed)
      if len(l3)<=len(s[2]): # no carry from L3 to L1
         print(s[0].count('1')+(n*(s[1].count('1')))+l3.count('1')) 
      else:# carry from L3 to L2
         if s[1][-1]=='0': #lsb of L2 is 0
            print(1+s[0].count('1')+(n*(s[1].count('1')))+(l3.count('1')-1)) 
         else: #lsb of L2 is 1
            l2=bin(int(s[1],2)+1)[2:]
            if len(l2)<=len(s[1]):# no carry from l2 to l2'
               print(s[0].count('1')+((n-1)*(s[1].count('1')))+l2.count('1')+(l3.count('1')-1))
            else: #carry from l2 to l1
               if s[0][-1]=='0': #lsb of L1 is 0
                  print(1+s[0].count('1')+(n*(l2[1:].count('1')))+l3[1:].count('1'))
               else: # lsb if L1 is 1
                  l1=bin(int(s[0],2)+1)[2:]
                  print(l1.count('1')+(n*(l2[1:].count('1')))+l3[1:].count('1'))