
def topoSort(adj,n):
    stack=[]
    visited=set()
    disc=set()
    for i in range(n):
        if i not in visited:
            topoSortUtil(adj,i,stack,disc,visited)
            
    return stack

def topoSortUtil(adj,src,stack,disc,visited):
    disc.add(src)
    if src in adj:
        for v in adj[src]:
            if v not in visited:
                if v not in disc:
                    topoSortUtil(adj,v,stack,disc,visited)
    
    
    if src not in visited:
        stack.append(src)
    visited.add(src)            
        

for t in prerequisites:
    if t[1] in adj:
        adj[t[1]].append(t[0])
    else:
        adj[t[1]]=[t[0]]
dfs(adj,numCourses)
if isCyclic:
    return []
else:
    l=topoSort(adj,numCourses)
    l.reverse()
    return l
        
        
 
        