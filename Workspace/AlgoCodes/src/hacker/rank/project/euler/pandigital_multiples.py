n=101

nums_9=[]
nums_8=[]
s8=([1,2,3,4,5,6,7,8])
s8=([1,2,3,4,5,6,7,8,9])
for i in range(2,n):
    set8=set()
    for k in range(1,9):
        t=k*i;
        while t!=0:
            r=t%10
            t=t//10
            if r>0 and r<9:
                if r in set8:
                    break
                else:
                    set8.add(r)
                    if len(set8.intersection(s8))==8:
                        nums_8.append(i)
                        break
            else:
                  break
    
    for k in range(1,9):               
        t=k*i;
        set9=set()
        while t!=0:
            r=t%10
            t=t//10
            if r>0 and r<10:
                if r in set9:
                    break
                else:
                    set9.add(r)
                    if len(set9.intersection(s9))==9:
                        nums_9.append(i)
                        break
            else:
                break
            
            
print(nums_8)
print(nums_9)        
    
                
        
    
    