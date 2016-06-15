n=int(input())
for  i in range(n//2):
   print("*"*((n-(2*i+1))//2),end="")
   print("D"*(2*i+1),end="")
   print("*"*((n-(2*i+1))//2),end="")
   print()
   
for  i in range(n//2+1,n+1):
   print("*"*((n-(2*(n-i)+1))//2),end="")
   print("D"*(2*(n-i)+1),end="")
   print("*"*((n-(2*(n-i)+1))//2),end="")
   print()
   