n=int(input())
l=list(map(int,input().split(' ')))
l.sort()
#while len(l)>0:
print l.find(l[0]+4)
       