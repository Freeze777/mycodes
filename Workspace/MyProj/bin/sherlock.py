count =[0]*26
s=raw_input()

for c in s:
    index=ord(c)-ord('a')
    count[index]+=1;

count2alpha={}
for i in range(len(count)):
    if count[i] in count2alpha:
        count2alpha[count[i]]+=1
    else:
        count2alpha[count[i]]=1

if 0 in count2alpha:        
    count2alpha.pop(0)    

if len(count2alpha)>2:
    print("NO")
elif len(count2alpha)==2:
    if 1 in count2alpha.values():
        print("YES")
    else:
        print("NO")
else:
    print("YES")