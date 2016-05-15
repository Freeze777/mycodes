fact={0:1,1:1,2:2,3:6,4:24,5:120,6:720,7:5040,8:40320,9:362880}
n=100001
nums=[]
for i in range(10,n):
    sum=0
    t=i
    while t!=0:
        temp=t%10
        t=t//10
        sum+=fact[temp]
        
    if sum%i==0:
        nums.append(i)
        
num=int(input())
sum=0
for i in range(len(nums)):
    if nums[i] < num:
      sum+=nums[i]
    else:
      break;
      
      
print(sum)