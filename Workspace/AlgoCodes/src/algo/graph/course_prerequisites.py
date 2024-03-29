isCyclic=False#global variables must explicitly mentioned in body of functions

def dfs(adj,n):
    disc=set()
    visited=set()
    topSort=[]
    for i in range(n):
        if i not in visited:
            dfsHelper(adj,disc,visited,i,topSort)
    return topSort[::-1]
    
def dfsHelper(adj,disc,visited,src,topSort):
    disc.add(src)
    #print("dfs at ",src)
    #print("disc:",disc)
    if src in adj:
        for v in adj[src]:
            if v not in visited:
                if v in disc:
                   global isCyclic#if not mentioned will give horrible answers
                   isCyclic=True
                else:
                    dfsHelper(adj,disc,visited,v,topSort)
    visited.add(src)
    topSort.append(src)
    #print("visited",visited
    
    
class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        adj={}
        for t in prerequisites:
            if t[1] in adj:
                adj[t[1]].append(t[0])
            else:
                adj[t[1]]=[t[0]]
        l=dfs(adj,numCourses)
        if isCyclic:
            global isCyclic#leetcode has a problem,it creates one instance and runs all testcases.
            isCyclic=False
            return []
        else:
            return l       
        
        
        
        
        
        
        
        
        
        