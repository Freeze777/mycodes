#include <iostream>
#include <string>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <vector>
#include <utility>
#include <iomanip>
#include <sstream>
#include <bitset>
#include <cstdlib>
#include <iterator>
#include <algorithm>
#include <cstdio>
#include <cctype>
#include <cmath>
#include <math.h>
#include <ctime>
#include <cstring>
using namespace std;

#define PI                    acos(-1.0)
#define pi                    3.141592653589793
#define SQR(n)                ( n * n )

#define ff                    first
#define ss                    second
#define mp                    make_pair
#define pb                    push_back

#define SET(a)                memset( a, -1,    sizeof a )
#define CLR(a)                memset( a,  0,    sizeof a )
#define MEM(a,val)            memset( a,  val,  sizeof(a) )

#define nn                    '\n'
#define SS                    stringstream
#define ull                   unsigned long long
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);
#define sz(a)                 int((a).size())
#define space                 " "
#define all(x)                (x).begin(), (x).end()
#define Ignore                cin.ignore()
#define StringToInt           if ( ! (istringstream(s) >> n) ) n = 0;

#define for(i,x,y) for(ll i = (x) ; i <= (y) ; ++i)
#define rep(i,n) 	for(ll i = 0 ; i < n ; ++i)
#define rof(i,x,y) for(ll i = (y) ; i >= (x) ; --i)
#define iter(container, it) for(typeof(container.begin()) it = container.begin(); it != container.end(); it++)
#define UNIQUE(V) (V).erase(unique((V).begin(),(V).end()),(V).end())

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

template < class T > T multiply( T a, T b ){return a * b ;}
template < class T > T larger( T a, T b ){return ( a > b ? a : b );}
template < class T > T smaller( T a, T b ){return ( a < b ? a : b );}
template<class T> T gcd(T a,T b){if(b == 0)return a;return gcd(b,a%b);}
template<class T> T lcm(T a, T b ){return (a*b)/gcd(a,b);}
template < class T > string converter( T n ){SS x;x << n;return x.str();}


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

const double eps = 1e-9;
#define mod                   1000000007
#define INF                   2147483647
#define SIZE                  2000001
#define imax numeric_limits<int>::max()
#define imin numeric_limits<int>::min()
#define ldmax numeric_limits<ld>::max()
#define ldmin numeric_limits<ld>::min()
#define lldmax numeric_limits<lld>::max()
#define lldmin numeric_limits<lld>::min()

#define sf                    scanf
#define pf                    printf

#define sf1(a)                scanf("%d", &a)
#define sf2(a,b)              scanf("%d %d",&a, &b)
#define sf3(a,b,c)            scanf("%d %d %d", &a, &b, &c)
#define sf4(a,b,c, d)         scanf("%d %d %d %d", &a, &b, &c, &d)

#define sf1ll(a)              scanf("%I64d", &a)
#define sf2ll(a,b)            scanf("%I64d %I64d", &a, &b)
#define sf3ll(a,b,c)          scanf("%I64d %I64d %I64d", &a, &b, &c)
#define sf4ll(a,b,c, d)       scanf("%I64d %I64d %I64d %I64d", &a, &b, &c, &d)

inline ll power(ll a, ll n) {ll p = 1;while (n > 0) {if(n%2) {p = p * a;} n >>= 1; a *= a;} return p;}
inline ll power(ll a, ll n, ll m) {ll p = 1;while (n > 0) {if(n%2) {p = p * a; p %= m;} n >>= 1; a *= a; a %= m;} return p % m;}

#define READ                  freopen("input.txt", "r", stdin);
#define WRITE                 freopen("output.txt", "w", stdout);

int main(){



   return 0;
}





