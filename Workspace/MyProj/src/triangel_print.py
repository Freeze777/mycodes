n=10
width=4*n
counter=1
for i in range(1,n+1):
   print(" "*(2)*(n-i),end="")
   for j in range(i):#occupies 4i characters
      if counter>=10:
         print(counter,end="  ")
      else:
         print(counter,end="   ")
      counter+=1      
   print(" "*(2)*(n-i),end="")
   print()