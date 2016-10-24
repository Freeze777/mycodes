n=int(input())
m=int(input())
s=input()

if m>=n:
    print("")
    exit(0)
    
stack=[]
i=0
while m>0 and i<n:
    while len(stack)>0 and m>0 and stack[-1]<s[i]:
        m-=1
        stack.pop()
    stack.append(s[i])
    i+=1
    
while i<n:
    stack.append(s[i])
    i+=1
while m:
   stack.pop()
   m-=1
print(''.join(stack))