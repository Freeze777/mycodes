def isSafe(t):
   if t[0]>=n or t[0]<0 or t[1]>=m or t[1]<0:
      return False
   if graph[t[0]][t[1]]=='#':
      return False
   return True
def dfs(src,curPath):
   if src == d:
      ans[0]=(ans[0] if len(ans[0])>len(curPath) else curPath)
      return
   next_tuple=[(abs(d[0]-(src[0]+x[0]))+abs(d[1]-(src[1]+x[1])),x) for x in direction]
   next_tuple.sort(reverse=True)
   for nxt in next_tuple:
      t=nxt[1]
      v=(src[0]+t[0],src[1]+t[1])
      if isSafe(v) and (not visited[v[0]][v[1]]):
         visited[v[0]][v[1]]=True         
         dfs(v,curPath+direction_dic[t])
         
import time
from random import random,shuffle
import resource, sys
resource.setrlimit(resource.RLIMIT_STACK, (2**29,-1))
sys.setrecursionlimit(10**6)
direction=[(1,0),(-1,0),(0,-1),(0,1)]
direction_dic={(1,0):'D',(-1,0):'U',(0,-1):'L',(0,1):'R'}
n,m=map(int,raw_input().split())
graph=[]
visited=[]
s=-1
d=-1
ans=['']
for i in range(n):
   inp=raw_input()
   graph.append(inp)
   visited.append([False]*m)
   if s==-1:
      idx=inp.find('S')
      if idx!=-1:
         s=(i,idx)
   if d==-1:
      idx=inp.find('F')
      if idx!=-1:
         d=(i,idx)
visited[s[0]][s[1]]=True
prob=m*n*0.001
dfs(s,'')
print ans[0]