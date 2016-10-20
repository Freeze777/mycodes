"""
Undirected graph

n: number of vertices
ith edge is from g_from[i] to g_to[i] with weigth g_weight[i]
g_from[i] g_to[i] g_weight[i] (i.e edges: u v weight)
find shortest from start to end
use extra edge of weight w_extra whenever needed to get min cost path

"""
def  minCost(n, g_from, g_to, g_weight, start, end, w_extra):
   #number of edges
   m=len(g_from)
   #edge list to store weight O(1) time access
   edge={}
   #adjacency list
   adj={}
   #making adjacency list from given arrays
   for i in range(m):
      if g_from[i] not in adj:
         adj[g_from[i]]=set()      
      if g_to[i] not in adj:
         adj[g_to[i]]=set()
      adj[g_to[i]].add(g_from[i])
      adj[g_from[i]].add(g_to[i])
      edge[(g_from[i],g_to[i])]=g_weight[i]
      edge[(g_to[i],g_from[i])]=g_weight[i]
   #distance array storing distance from start
   dist=[1e9]*(n+1)

   dist[start]=0
   #0/1 variable to indicate whether extra edge was taken or not
   taken=0
   dfs(start,end,adj,dist,w_extra,taken,edge,n)   
   return dist[end]

#do not used visited array because this is not a normal dfs
def dfs(start,end,adj,dist,w_extra,taken,edge,n):
   #see whether start is there in the adj list
   if start in adj:
      l=adj[start]
   else:
      l=set()
   #see whether there is edge from current vertex to end
   if end in l:
      #if yes then update distance
      d=dist[start]+edge[(start,end)]
      dist[end]=min(dist[end],d)
   elif not taken:
      #if no then take the extra edge if not taken already
      d=dist[start]+w_extra 
      dist[end]=min(dist[end],d)
   #get all vertices that doesnt have edges with current vertex
   vrtx=set(range(1,n+1))
   vrtx-=set([start])
   vrtx-=l
   #take extra edge for all nodes which do not have edge with current node and do dfs with taken=1, do dfs only if distance is small than current
   for e in vrtx:
      if dist[e]>dist[start]+w_extra:
         dist[e]=dist[start]+w_extra
         dfs(e,end,adj,dist,w_extra,1,edge,n)
   #current node and do dfs with taken=0 for connected vertices with taken-0 only if distance is smaller
   for e in l:
      if dist[e]>dist[start]+edge[(start,e)]:
         dist[e]=dist[start]+edge[(start,e)]
         dfs(e,end,adj,dist,w_extra,0,edge,n)
