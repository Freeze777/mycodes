isCyclic=False

def dfs(adj,n):
    disc=set()
    visited=set()
    for i in range(n):
        if i not in visited:
            dfsHelper(adj,disc,visited,i)
    
def dfsHelper(adj,disc,visited,src):
    disc.add(src)
    #print("dfs at ",src)
    #print("disc:",disc)
    if src in adj:
        for v in adj[src]:
            if v not in visited:
                if v in disc:
                   global isCyclic
                   isCyclic=True
                else:
                    dfsHelper(adj,disc,visited,v)
    visited.add(src)
    #print("visited",visited)
            
        
        
        