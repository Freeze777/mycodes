#include<bits/stdc++.h>
using namespace std;
const int sz=100005;
const int inf=(1<<28);
template<typename t> t MIN3(t a,t b, t c){return min(a,min(b,c));}
int BLOCK[400];
int arr[sz];
int getId(int indx,int blockSZ)
{
    return indx/blockSZ;
}
void init(int sz)
{
    for(int i=0; i<=sz; i++)BLOCK[i]=inf;
}
void update(int val,int indx,int blockSZ)
{
    int id=getId(indx,blockSZ);
    BLOCK[id]=min(BLOCK[id],val);
}
int query(int L,int R,int blockSZ)
{
    int lid=getId(L,blockSZ);
    int rid=getId(R,blockSZ);
    if(lid==rid)
    {
        int ret=inf;
        for(int i=L; i<=R; i++)ret=min(ret,arr[i]);
        return ret;
    }
    int m1=inf,m2=inf,m3=inf;
    for(int i=L; i<(lid+1)*blockSZ; i++)m1=min(m1,arr[i]);
    for(int i=lid+1;i<rid;i++)m2=min(m2,BLOCK[i]);
    for(int i=rid*blockSZ; i<=R; i++)m3=min(m3,arr[i]);
    return MIN3(m1,m2,m3);
}
int main()
{
    int N,Q;
    scanf("%d %d",&N,&Q);
    int blockSZ=sqrt(N);
    init(blockSZ);
    for(int i=0; i<N; i++)
    {
        int x;
        scanf("%d",&x);
        arr[i]=x;
        update(x,i,blockSZ);
    }
    while(Q--)
    {
        int x,y;
        scanf("%d %d",&x,&y);
        printf("%d\n",query(x,y,blockSZ));
    }
    return 0;
}

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <vector>
#include <stack>
#include <queue>
#include <sstream>
#include <map>
#include <bitset>
#include <sstream>
#include <cassert>
#include <climits>
#include <fstream>
#include <string>
using namespace std;

#define zero(n) memset(n,0,sizeof(n))
//end of header files

#define ONLINE_JUDGE

#define llu long long unsigned
#define lld long long
#define ld long
//end of definitions
#ifndef ONLINE_JUDGE
#define getchar_unlocked() getchar()
#define putchar_unlocked(x) putchar(x)
#endif // ONLINE_JUDGE


int s_d()    {register int ip=getchar_unlocked(),ret=0,flag=1;for(;ip<'0'||ip>'9';ip=getchar_unlocked())if(ip=='-'){flag=-1;ip=getchar_unlocked();break;}for(;ip>='0'&&ip<='9';ip=getchar_unlocked())ret=ret*10+ip-'0';return flag*ret;}
ld s_ld()    {register int ip=getchar_unlocked(),flag=1;ld ret=0;for(;ip<'0'||ip>'9';ip=getchar_unlocked())if(ip=='-'){flag=-1;ip=getchar_unlocked();break;}for(;ip>='0'&&ip<='9';ip=getchar_unlocked())ret=ret*10+ip-'0';return flag*ret;}
lld s_lld()    {register int ip=getchar_unlocked(),flag=1;lld ret=0;for(;ip<'0'||ip>'9';ip=getchar_unlocked())if(ip=='-'){flag=-1;ip=getchar_unlocked();break;}for(;ip>='0'&&ip<='9';ip=getchar_unlocked())ret=ret*10+ip-'0';return flag*ret;}
llu s_llu()    {register int ip=getchar_unlocked();llu ret=0;for(;ip<'0'||ip>'9';ip=getchar_unlocked());for(;ip>='0'&&ip<='9';ip=getchar_unlocked())ret=ret*10+ip-'0';return ret;}

//fast output

//no line break
void p_d(int n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=10;char output_buffer[10];do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<10);}
void p_ld(ld n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=11;char output_buffer[11];do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<11);}
void p_lld(lld n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=21;char output_buffer[21];do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<21);}
void p_llu(llu n)     {int i=21;char output_buffer[21];do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<21);}

//new line
void pln_d(int n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=10;char output_buffer[11];output_buffer[10]='\n';do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<11);}
void pln_ld(ld n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=11;char output_buffer[12];output_buffer[11]='\n';do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<12);}
void pln_lld(lld n)     {if(n<0){n=-n;putchar_unlocked('-');}int i=21;char output_buffer[22];output_buffer[21]='\n';do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<22);}
void pln_llu(llu n)     {int i=21;char output_buffer[22];output_buffer[21]='\n';do{output_buffer[--i]=(n%10)+'0';n/=10;}while(n);do{putchar_unlocked(output_buffer[i]);}while(++i<22);}

inline void scan(string &ret){
    register int a=getchar_unlocked();
    ret="";
    while(a==' '||a=='\n'||a==EOF)
        a=getchar_unlocked();
    while(a!=' '&&a!='\n'&&a!=EOF){
        ret+=(char)a;
        a=getchar_unlocked();
    }
}

/*####################################*/

struct _func {
	int L;
	int R;
};
/*
llu getMid(llu s, llu e) {  return (e +s)/2;  }
void build_tree(llu *tree, int node, int a, int b, int *arr) {
  	if(a > b) return;
  	
  	if(a == b) { 
        tree[node] = (llu) arr[a];
		return;
	}
	llu mid = getMid(a, b);
	
	build_tree(tree, node*2, a, mid, arr);
	build_tree(tree, node*2+1, 1+mid, b, arr);
	
	tree[node] = tree[node*2] + tree[node*2+1];
}
void update_tree(llu *tree, int node, int a, int b, int x, int value) {
    if(a > x || b < x) {
		return;
    }
  	if(a == b) {
  		tree[node] = (llu) value;
    	return;
	}
	llu mid = getMid(a, b);
	update_tree(tree, node*2, a, mid, x, value);
	update_tree(tree, 1+(node*2), mid+1, b, x, value);
	tree[node] = tree[node*2] + tree[(node * 2) +1];
}
llu query_tree(llu *tree, int node, int a, int b, int i, int j) {
    if (i > j || a > j || b < i) return 0;
	if (i <= a && b<= j) {
		return tree[node];
	}
		
    llu mid = getMid(a, b);
	llu q1 = query_tree(tree, node*2, a, mid, i, j); 
	llu q2 = query_tree(tree, 1+(node*2), mid + 1, b, i, j); 
	
	return q1 + q2;
}*/
const int maxn = 100022;
const int sqrtN = 320;
long long t[maxn];
void add(int i, long long value) {
  for (i++; i < maxn; i += -i & i) t[i] += value;
}
long long sum(int i) {
  long long res = 0;
  for (i++; i > 0; i -= i & -i) res += t[i];
  return res;
}
long long sum(int l, int r) { return sum(r) - sum(l - 1); }

int b[sqrtN+1][maxn];

void updateBucket (llu *bucket, int pos, int newX, int prevX, int sqrtN, int N) {
	for (int i = 0; i < sqrtN; ++i) {
		bucket[i] += ((newX - prevX) * 1ll * b[i][pos]);
		//cout<<" "<<bucket[i];
	}
	//cout<<endl;
}

llu queryBucket (llu *bucket, _func *func, int low, int high, int sqrtN, int N) {
	llu ans = 0;

	  while (low <= high && low % sqrtN != 0) {
        ans += sum(func[low].L, func[low].R);
        low++;
      }
      while (low <= high && high%sqrtN != sqrtN - 1) {
        ans += sum(func[high].L, func[high].R);
        high--;
      }
      //cout<<"low = "<<low<<" high = "<<high<<endl;
 
      if (low < high)
        for (int i = low/sqrtN; i <= high / sqrtN; ++i) ans += bucket[i];

	return ans;
}

int main() {
	int N = s_d();

	int *arr = new int[N+1];
	int sqrtN = sqrt(N);
	llu *bucket = new llu[sqrtN];

	for (int i = 1; i <= N; ++i) {
		arr[i] = s_d();
		add(i, arr[i]);
	}
	_func *func = new _func[N+1];
	int ratio;
	for (int i = 0; i < N; ++i) {
		func[i].L = s_d();
		func[i].R = s_d();
		ratio = (i/sqrtN);
		//cout<<"ratio = "<<ratio<<endl;
		++b[ratio][func[i].L];
		--b[ratio][func[i].R+1];
	}

	for (int i = 0; i < sqrtN; ++i) {
		for (int j = 2; j <= N; ++j) {
			b[i][j] += b[i][j-1];
			//bucket[i] += (llu) (b[i][j]*arr[j]);
		}
	}

	for (int i = 0; i < sqrtN; ++i) {
		 bucket[i] = 0;
		for (int j = 1; j <= N; ++j) {
			bucket[i] += (arr[j] * 1ll * b[i][j]);
		}
	}
/*
	for (int i = 1; i <= N; ++i) {
		cout<<"i = "<<i<<" = "<<b[1][i]<<endl;
	}cout<<"=========================="<<endl;
	for (int i = 1; i <= N; ++i) {
		cout<<"i = "<<i<<" = "<<b[2][i]<<endl;
	}cout<<"=========================="<<endl;
	for (int i = 1; i <= N; ++i) {
		cout<<"i = "<<i<<" = "<<b[3][i]<<endl;
	}cout<<"=========================="<<endl;
*/
	/*
	llu tempSize = (llu)(ceil(log2(N)));
	llu max_size = 2*(llu)pow(2, tempSize);
    llu* tree = new llu[max_size+1];
    build_tree(tree, 1, 1, N, arr);*/

	int Q = s_d();
	int option, x, y;
	llu ans;
	for (int i =0; i < Q; ++i) {
		option = s_d();
		x = s_d();
		y = s_d();
		if (option == 1) {
	  //update_tree(tree, 1, 1, N, x, y);
		    updateBucket(bucket, x, y, arr[x], sqrtN, N);
		    add(x, y - arr[x]);
		    arr[x] = y; 
		} else {
		    --x;
		    --y;
		    ans = queryBucket(bucket, func, x, y, sqrtN, N);
		    pln_llu(ans);
	    }
	}
}