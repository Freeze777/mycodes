#http://codeforces.com/contest/719/problem/B

n=int(input())
l=list(input())
d={'r':'b','b':'r'}
cnt=0
i=n//2-1
j=n//2
if l[i]==l[j]:
   l[i]=d[l[i]]
   cnt+=1
i-=1
j+=1
while i>=0 and j<n:
   if l[i]==l[j]:
      if l[i]==l[i+1]:
         l[i]=d[l[i]]
      else:
         l[j]=d[l[j]]
      cnt+=1 
   else:
       if l[i]==l[i+1]:
         l[i],l[j]=l[j],l[i]
         cnt+=1
   j+=1
   i-=1
if n%2==1:
   if l[n-1]!=l[0]:
      l[n-1]=d[l[n-1]]
      cnt+=1
print(cnt)
#print(l)