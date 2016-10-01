#include <bits/stdc++.h>
using namespace std;

#define PI                    acos(-1.0)
#define pi                    3.141592653589793
#define typeof __typeof__


typedef  unsigned long long      ull;
typedef  long long               ll;
typedef  map<string,int>         msi;
typedef  map<int,int>	         mii;
typedef  map<ll, ll>             mll;
typedef  map<char,int>           mci;
typedef  map<int,string>	     mis;
typedef  pair<int,int> 	         pii;
typedef  pair<string, int>       psi;
typedef  pair<string, string>    pss;
typedef  vector<int> 	         vi;
typedef  vector<string> 	     vs;
typedef  vector<char>	         vc;
typedef  vector<bool>            vb;
typedef  vector< pii >           vii;

#define ff                    first
#define ss                    second
#define mp                    make_pair
#define pb                    push_back

#define nn                    '\n'
#define SS                    stringstream
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);
#define sz(a)                 int((a).size())
#define space                 " "
#define Ignore                cin.ignore()
#define StringToInt           if ( ! (istringstream(s) >> n) ) n = 0;


#define ALL(x)              (x).begin(), (x).end() ///handy for function like "sort()"
#define FOR(i,x,y) 			for(ll i = (x) ; i <= (y) ; ++i)
#define REP(i,n) 			for(ll i = 0 ; i < n ; ++i)
#define ROF(i,x,y) 			for(ll i = (y) ; i >= (x) ; --i)
#define ITER(container, it) for(typeof(container.begin()) it = container.begin(); it != container.end(); it++)
#define IN(container,a)     (container).find(a) != (container).end()
#define IN_(container,x) 	(find(ALL(container),x) != (container).end()) 
#define UNIQUE(V) 			(V).erase(unique((V).begin(),(V).end()),(V).end())
#define FILL(a,v)  			memset(a, v, sizeof(a))


#define SQR(n)                ( n * n )
#define POPCOUNT __builtin_popcountll
#define RIGHTMOST __builtin_ctzll
#define LEFTMOST(x) (63-__builtin_clzll((x)))
#define MIN(a,b) ((a)<(b)?(a):(b))
#define MAX(a,b) ((a)>(b)?(a):(b))
#define NUMDIGIT(x,y) (((vlong)(log10((x))/log10((y))))+1)
#define SQ(x) ((x)*(x))
#define ABS(x) ((x)<0?-(x):(x))
#define FABS(x) ((x)+eps<0?-(x):(x))
#define NORM(x) if(x>=mod)x-=mod;
#define ODD(x) (((x)&1)==0?(0):(1))
inline ll POWER(ll a, ll n) {ll p = 1;while (n > 0) {if(n%2) {p = p * a;} n >>= 1; a *= a;} return p;}
inline ll POWER(ll a, ll n, ll m) {ll p = 1;while (n > 0) {if(n%2) {p = p * a; p %= m;} n >>= 1; a *= a; a %= m;} return p % m;}
inline ll PMOD(ll a,ll m){a%=m; return a>=0?a:a+m;}

template < class T > T multiply( T a, T b ){return a * b ;}
template < class T > T larger( T a, T b ){return ( a > b ? a : b );}
template < class T > T smaller( T a, T b ){return ( a < b ? a : b );}
template<class T> T gcd(T a,T b){if(b == 0)return a;return gcd(b,a%b);}
template<class T> T lcm(T a, T b ){return (a*b)/gcd(a,b);}
template < class T > string converter( T n ){SS x;x << n;return x.str();}

const double eps = 1e-9;
#define mod                   1000000007
#define SIZE                  2000001
#define imax numeric_limits<int>::max()
#define imin numeric_limits<int>::min()
#define llmax numeric_limits<ll>::max()
#define llmin numeric_limits<ll>::min()

#define sf                    scanf
#define pf                    printf
#define sf1(a)                scanf("%d", &a)
#define sf2(a,b)              scanf("%d %d",&a, &b)
#define sf1ll(a)              scanf("%I64d", &a)
#define sf2ll(a,b)            scanf("%I64d %I64d", &a, &b)

#define READ                  freopen("input.txt", "r", stdin);
#define WRITE                 freopen("output.txt", "w", stdout);

int main(){
 fast_io;
	int n;cin>>n;
   mii m;
   REP(i,n){
      int a;cin>>a;
      if(IN(m,a))m[a]++;
      else m[a]=1;
   }
   ll ans=0;
	ITER(m,it)ans+=((it->ss)/2);
   cout<<ans;

   return 0;
}