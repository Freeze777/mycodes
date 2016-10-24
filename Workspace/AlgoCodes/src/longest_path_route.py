#!/bin/python3
"""
4 5
S#.#.
..#..
.....
....F
"""
import time
import sys
from random import random,shuffle
sys.setrecursionlimit(10000000)
direction_dic={(1,0):'D',(-1,0):'U',(0,-1):'L',(0,1):'R'}
def isSafe(t,graph,n,m):
   if t[0]>=n or t[0]<0 or t[1]>=m or t[1]<0:
      return False
   if graph[t[0]][t[1]]=='#':
      return False
   return True
  
def dfs(src,curPath,dest,graph,n,m,visited,ans,prob):
   #print('dfs at ', src)
   if src == dest:
      ans[0]=(ans[0] if len(ans[0])>len(curPath) else curPath)
      return
   direction=[(1,0),(-1,0),(0,-1),(0,1)]
   shuffle(direction)
   for t in direction:
      v=(src[0]+t[0],src[1]+t[1])
      if isSafe(v,graph,n,m) and (not visited[v[0]][v[1]]):
         visited[v[0]][v[1]]=True         
         dfs(v,curPath+direction_dic[t],dest,graph,n,m,visited,ans,prob)         
         if random()>prob:
            visited[v[0]][v[1]]=False
         
n,m=map(int,input().split())
graph=[]
visited=[]
src=-1
dest=-1
ans=['']
for i in range(n):
   inp=input()
   graph.append(inp)
   visited.append([False]*m)
   if src==-1:
      idx=inp.find('S')
      if idx!=-1:
         src=(i,idx)
   if dest==-1:
      idx=inp.find('F')
      if idx!=-1:
         dest=(i,idx)
visited[src[0]][src[1]]=True
#print(src,dest)
#probs=[0.0,0.25,0.5,0.75,1.0]
#prob=probs[2]
prob=m*n*0.000001
#start_time = time.time()
dfs(src,'',dest,graph,n,m,visited,ans,prob)
print(ans[0],end=" ")
#print(prob,end=" ")
#print("%s sec" % (time.time() - start_time))