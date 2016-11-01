#include <cstdio>
#include <climits>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <map>
#include <cstring>
#define pb                  push_back
#define sf                  scanf
#define pf                  printf
using namespace std;

//const int MAX = 131072;
const int MAX = 101072;
const int LOG = 17;

unordered_map<int,vector<int>> G;
//map<int,vector<int>> G;
int root[MAX][LOG],pi[MAX], lvl[MAX];

void dfs(int par, int u, int depth) {
    int sz = G[u].size(), i, v;
    lvl[u] = depth;
    for(i = 0; i < sz; i++) {
        v = G[u][i];
        if(v != par) {
            pi[v] = u;
            dfs(u, v, depth+1);
        }
    }
}

void calcRoot(int n) {
    int i, j;
    memset(root, -1, sizeof root);
    for(i = 1; i <= n; i++) root[i][0] = pi[i];
    for(j = 1; 1<<j < n; j++)
        for(i = 1; i <= n; i++)
            if(root[i][j-1]!=-1)
                root[i][j] = root[root[i][j-1]][j-1];
}
//O(logn)
int lca(int p, int q) {
    int i, stp;
    if(lvl[p] < lvl[q]) swap(p, q);

    for(stp = 1; 1<<stp <= lvl[p]; stp++); stp--;

    for(i = stp; i >= 0; i--)
        if(lvl[p] - (1<<i) >= lvl[q])
            p = root[p][i];
    if(p == q) return p;
    for(i = stp; i >= 0; i--)
        if(root[p][i]!=-1 && root[p][i]!=root[q][i])
            p = root[p][i], q = root[q][i];
    return pi[p];
}

int main() {
     double t = clock();
    int n,q,u,v;
    sf("%d%d", &n,&q);
        //n-1 lines parents of 2 to n
        for(int i = 2; i <=n; i++) {
            sf("%d",&v);
            G[v].pb(i);
            G[i].pb(v);
        }
        //dfs from root- O(n)
        dfs(-1, 1, 0);
        //DP precomputation for lca- O(nlogn)
        calcRoot(n);

        while(q--){
            int k;
            sf("%d", &k);
            if(k==1){
                int a;sf("%d", &a);
                pf("%d\n",a);
            }else{
                int a,b;sf("%d%d",&a,&b);
                a=lca(a, b);
                k-=2;
                while(k-- && a!=1){
                    sf("%d",&b);
                    a=lca(a,b);
                }
                pf("%d\n",a);
            }

        }
       //pf("Execution Time= %f\n", (clock()-t)/CLOCKS_PER_SEC);
    return 0;
}
