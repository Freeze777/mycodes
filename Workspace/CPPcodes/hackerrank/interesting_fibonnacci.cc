#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<int,int> ii;
#define mod 1000000007
#define fill(a,x) memset(a,x,sizeof(a))
#define pb push_back
#define sz(x) (int)x.size()
#define F first
#define S second
#define FOR(i,a,b) for(int i = a; i<=b; ++i)
#define NFOR(i,a,b) for(int i = a; i>=b; --i)
#define fast ios_base::sync_with_stdio(false),cin.tie(0),cout.tie(0)
const ll INF = 1e18;
const int N = 1e5+10;
const int S = 2;

struct matrix{
  ll a[S][S];
};
matrix operator * (const matrix &m1,const matrix &m2){
  matrix m = {{}};
  FOR(i,0,S-1)FOR(j,0,S-1)FOR(k,0,S-1)m.a[i][k] = (m.a[i][k] + 1LL*m1.a[i][j]*m2.a[j][k]) % mod;
  return m;
}
matrix operator + (const matrix &m1,const matrix &m2){
  matrix m = {{}};
  FOR(i,0,S-1)FOR(j,0,S-1)m.a[i][j] = (m1.a[i][j]+m2.a[i][j])%mod;
  return m;
}
void makeid(matrix &m){
  FOR(i,0,S-1)FOR(j,0,S-1)m.a[i][j] = 0;
  FOR(i,0,S-1){
    m.a[i][i] = 1;
  }
}
matrix expo(matrix a,ll b){
  if(b == 0){
  	matrix ret;
  	makeid(ret);
  	return ret;
  }
  if(b == 1)return a;
  matrix ret = expo(a,b/2);
  ret = ret*ret;
  if(b&1)ret = ret*a;
  return ret;
}

 matrix lol;
ll bib(ll n){
	if(n <= 1)return 1;
	matrix he = expo(lol,n-1);
	int ret = (he.a[0][0] + he.a[0][1] + he.a[0][2] )%mod;
	return ret;

}
ll a[N];
ll func(matrix he){
	ll ret = (he.a[0][0] - he.a[0][1] + mod + he.a[0][2] )%mod;
	return ret;
}
int main(){
  fast;
   int t;cin>>t;
  while(t--){
     lol.a[0][0] = 1;
     lol.a[0][1] = 1;
     //lol.a[0][2] = 0;
     lol.a[1][0] = 1;
     lol.a[1][1] = 0;
     //lol.a[1][2] = 0;
    // lol.a[2][0] = 0;
    // lol.a[2][1] = 0;
    // lol.a[2][2] = 0;
     int n;
     cin >> n;
     FOR(i,0,n-1)cin >> a[i];
     ll ans = 0;
     matrix haha = expo(lol,a[0]);
     ans += haha.a[0][1];
     FOR(i,1,n-1){
      matrix ne = expo(lol,a[i]);
      haha = (haha*ne + ne);
      ans += haha.a[0][1];
      if(ans >= mod)ans -= mod;
     }
        cout << ans << "\n";
  }
  return 0;
}