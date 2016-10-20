    #include<bits/stdc++.h>
    #define whats(x) cerr << #x << " is " << x << endl;
    #define ff first
    #define ss second
    using namespace std;
    typedef long long ll;
    ll n,k;

    vector<bool>sieve(1e6,1);
    vector<int>primes;

    void generate(){
        sieve[0]=0;
        sieve[1]=0;
        for(int i=2;i*i<1e6;i++){
            if(sieve[i]){
                primes.push_back(i);
                int j=i;
                while(i*j<1e6){
                    sieve[i*j]=0;
                    j++;
                }
            }
        }
    }

    ll mulmod(ll a, ll b, ll c) {
        ll x=0; a=a%c;
        while (b) {
            if (b&1)x=(x+a)%c;
            a=(a<<1)%c;
            b>>=1;
        }
        return x%c;
    }

    ll fpow(ll x, ll n, ll mod) {
        ll ret=1;
        while (n) {
            if (n&1)ret=mulmod(ret,x,mod);
            x=mulmod(x,x,mod);
            n>>= 1;
        }
        return ret;
    }

    ll pollardrho(ll n)
    {
        srand (time(NULL));
        if (n==1) return n;
        if (n % 2 == 0) return 2;
        ll x = (rand()%(n-2))+2;
        ll y = x;
        ll c = (rand()%(n-1))+1;
        ll d = 1;
        while (d==1)
        {
            x = (fpow(x, 2, n) + c + n)%n;
            y = (fpow(y, 2, n) + c + n)%n;
            y = (fpow(y, 2, n) + c + n)%n;
            d = __gcd(abs(x-y), n);
            if (d==n) return pollardrho(n);
        }
        return d;
    }

    bool isPrime(ll n) {
        if(n==2)return 1;
        if(n<2 || n%2==0)return 0;
        ll d=n-1;
        int s=0;
        while(d%2==0) {
            s++;
            d>>=1;
        }
        // It's guranteed that these values will work for any number smaller than 3*10**18 (3 and 18 zeros)
        int a[9] = { 2, 3, 5, 7, 11, 13, 17, 19, 23 };
        for(int i = 0; i < 9; i++) {
            bool comp = fpow(a[i], d, n) != 1;
            if(comp) for(int j = 0; j < s; j++) {
                ll fp = fpow(a[i], (1LL << (ll)j)*d, n);
                if (fp == n - 1) {
                    comp = false;
                    break;
                }
            }
            if(comp) return false;
        }
        return true;
    }

   /* int main(){
        ios_base::sync_with_stdio(false),cin.tie(0),cout.tie(0);
        generate();
        cin>>n>>k;
        if(k>=n)return cout<<1<<endl,0;
        ll res=n;
        while(k--){
            n=res;
            if(isPrime(n)){res--;continue;}
            for(auto &it:primes){
                if(n%it==0){
                    while(n%it==0)n/=it;
                    res-=res/it;
                }
            }
            while(n>1 && !isPrime(n)){
                ll pfact=pollardrho(n);
                n/=pfact;
                res-=res/pfact;
            }
            if(n>1)res-=res/n;
        }
        cout<<res<<endl;
        return 0;
    }*/
#include <bits/stdc++.h>
#define rep(i,n) for(int (i)=0;(i)<(int)(n);++(i))
#define reu(i,l,u) for(int (i)=(int)(l);(i)<(int)(u);++(i))
#define each(it,o) for(auto it= (o).begin(); it != (o).end(); ++ it)
#define all(o) (o).begin(), (o).end()
#define mp(x,y) make_pair((x),(y))
#define mset(m,v) memset(m,v,sizeof(m))
#define INF 0x3f3f3f3f
#define INFL 0x3f3f3f3f3f3f3f3fLL
#define inrep int t;cin>>t; while(t--)
using namespace std;

typedef vector<int> vi;
typedef pair<int,int> pii;
typedef vector<pii > vpii;
typedef long long ll;
typedef vector<ll> vll;
typedef pair<ll,ll> pll;
typedef vector<pll > vpll;
typedef vector<string> vs;
typedef long double ld;

template<typename T> ostream& operator<< ( ostream &o,vector<T> v ) {
    if ( v.size() >0 )
        o<<v[0];
    for ( unsigned   i=1; i<v.size(); i++ )
        o<<" "<<v[i];
    return o<<endl;
}
template<typename U,typename V> ostream& operator<< ( ostream &o,pair<U,V> p ) {
    return o<<"("<<p.first<<", "<<p.second<<") ";
}
template<typename T> istream& operator>> ( istream &in,vector<T> &v ) {

    for ( unsigned   i=0; i<v.size(); i++ )
        in>>v[i];
    return in;
}

namespace Mul64 {

#define USE128 0
#define USEBOOST 0
#if USEBOOST
#include <boost/multiprecision/cpp_int.hpp>
    using namespace boost::multiprecision;
#endif

#if USE128
    typedef __int128 i128;
    inline ll mul ( ll x, ll y, ll mod ) {

        return ( i128 ) x*y%mod;
    }
    ll modPow ( ll a, ll k,ll mod ) {
        ll res=1;
        ll a2=a;
        while ( k ) {
            if ( k&1 ) {
                res= ( i128 ) res*a2%mod;
            }
            a2= ( i128 ) a2*a2%mod;
            k>>=1;

        }
        return res;
    }
#else
#if USEBOOST
    inline ll mul ( ll x, ll y, ll mod ) {
        return  static_cast<ll> ( int128_t ( x ) *y%mod );
    }

#else
    inline ll mul ( ll a, ll b, ll n ) {
        ll r = 0;
        if ( a < b )  swap ( a, b );
        while ( b ) {
            if ( b & 1 ) {
                r += a;
                if ( r >= n ) r -= n;
            }
            b >>= 1;
            a += a;
            if ( a >= n ) a -= n;
        }
        return r;
    }
#endif
    ll modPow ( ll a0, ll k,ll mod ) {
        ll res=1;
        ll a=a0;
        while ( k ) {
            if ( k&1 ) {
                res=mul ( res,a,mod );
            }
            a=mul ( a,a,mod );
            k>>=1;
        }
        return res;
    }
#endif
};


struct PRFactorize {
    int isSquare ( ll x ) {
        ll sq=sqrt ( x ) +0.5;
        if ( sq*sq==x ) return sq;
        return 0;
    }



    int modPow ( int a, ll k,int mod ) {
        ll res=1;
        while ( k ) {
            if ( k&1 ) {
                res=res*a%mod;
            }
            a= ( ll ) a*a%mod;
            k>>=1;
        }
        return res;
    }
    bool MRIsPrime ( int n ) {
        if ( n<2 ) return false;
        vi tests ( {2,3,61} );
        int d=n-1,s=0;
        while ( ( d&1 ) ==0 ) {
            d/=2;
            s++;
        }
        for ( int t: tests ) {
            if ( t>=n ) break;
            ll p=modPow ( t,d ,n );
            bool corr=1;
            if ( p ==1 ) continue;
            rep ( j,s ) {
                if ( p==n-1 ) {
                    corr=0;
                    break;
                }
                p=p*p%n;
            }
            if ( corr ) return 0;
        }

        return 1;
    }

    bool isPrime ( int n ) {
        return MRIsPrime ( n );
    }

    int PollardRhoFactorOnce ( int number, int l=1 ) {
        int sqr=isSquare ( number );
        if ( sqr ) return sqr;
        if ( isPrime ( number ) ) return number;
        int x_fixed = 2,cycle_size = 2,factor = 1;
        int x = 2;
        while ( factor == 1 ) {
            for ( int count=1; count <= cycle_size && factor <= 1; count++ ) {
                x = ( ( ll ) x*x +l ) %number;
                factor = abs ( __gcd ( x - x_fixed, number ) );
            }

            cycle_size *= 2;
            x_fixed = x;
        }
        if ( factor==number ) {
            return 0;
        }
        return factor;
    }


#if USE128
    typedef __int128 i128;
    inline ll mul ( ll x, ll y, ll mod ) {

        return ( i128 ) x*y%mod;
    }
    ll modPow ( ll a, ll k,ll mod ) {
        ll res=1;
        ll a2=a;
        while ( k ) {
            if ( k&1 ) {
                res= ( i128 ) res*a2%mod;
            }
            a2= ( i128 ) a2*a2%mod;
            k>>=1;

        }
        return res;
    }
#else
#if USEBOOST
    inline ll mul ( ll x, ll y, ll mod ) {
        return  static_cast<ll> ( int128_t ( x ) *y%mod );
    }

#else
    inline ll mul ( ll a, ll b, ll n ) {
        ll r = 0;
        if ( a < b )  swap ( a, b );
        while ( b ) {
            if ( b & 1 ) {
                r += a;
                if ( r >= n ) r -= n;
            }
            b >>= 1;
            a += a;
            if ( a >= n ) a -= n;
        }
        return r;
    }
#endif
    ll modPow ( ll a0, ll k,ll mod ) {
        ll res=1;
        ll a=a0;
        while ( k ) {
            if ( k&1 ) {
                res=mul ( res,a,mod );
            }
            a=mul ( a,a,mod );
            k>>=1;
        }
        return res;
    }
#endif




    bool MRIsPrime ( ll n ) {
        if ( n<2 ) return false;
        if ( n< ( 1LL<<31 ) ) return MRIsPrime ( ( int ) n );
//         vi tests ( {2, 3, 5, 7, 11, 13, 17, 19,  23} );
        vi tests= { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,  37};
        ll d=n-1,s=0;
        while ( ( d&1 ) ==0 ) {
            d/=2;
            s++;
        }
        for ( ll t: tests ) {
            if ( t>=n ) break;

            ll p=modPow ( t,d ,n );
            bool corr=1;
            if ( p ==1 ) continue;

            rep ( j,s ) {
                if ( p==n-1 ) corr=0;
                p=mul ( p,p ,n );
            }
            if ( corr ) return 0;
        }
        return 1;
    }

    bool isPrime ( ll n ) {
        if ( n< ( 1LL<<31 ) ) return isPrime ( ( int ) n );
        return MRIsPrime ( n );
    }

    ll PollardRhoFactorOnce ( ll number , int l=1 ) {
        int sqr=isSquare ( number );
        if ( sqr ) return sqr;
        if ( isPrime ( number ) ) return number;
        ll x_fixed = 2,cycle_size = 2,x = 2,factor = 1;
        while ( factor == 1 ) {
            for ( unsigned count=1; count <= cycle_size && factor <= 1; count++ ) {
                x =  mul ( x,x,number ) +l ;
                if ( x>number ) x-=number;
                if ( x<0 ) x+=number;
                factor = abs ( __gcd ( x - x_fixed, number ) );
            }
            cycle_size *= 2;
            x_fixed = x;
        }
        if ( factor==number ) {
            return 0;
        }
        return factor;
    }

    template<typename T>
    T PollardRhoFactor ( T number ) {

        for ( int l=3; l<100; l+=2 ) {
            T f= PollardRhoFactorOnce ( number,l );
            if ( f>0 ) {
                return f;
            }
        }
        assert ( 0 );
        return 0;
    }
    int getFactor ( int n ) {
        assert ( n>0 );
        return PollardRhoFactor ( n );
    }

    ll getFactor ( ll n ) {
        assert ( n>0 );
        if ( n< ( 1L<<31 ) ) return getFactor ( ( int ) n );
        return PollardRhoFactor ( n );
    }

    template<typename T>
    vector<T> getFactors ( T t ) {
        if ( t<=1 ) return vector<T>();
        vector<T> res;
        vector<T> factors= {t};
        while ( factors.size() ) {
            vector<T> nfac;
            for ( T t:factors ) {
                T f=getFactor ( t );
                bool ip=isPrime ( f );
                while ( t%f==0 ) {
                    t/=f;
                    if ( ip )
                        res.push_back ( f );
                    else nfac.push_back ( f );
                }

                if ( t>1 ) {
                    if ( isPrime ( t ) ) res.push_back ( t );
                    else nfac.push_back ( t );
                }
            }
            swap ( factors,nfac );
        }
        return res;
    }
};

PRFactorize pr;
ll phi ( ll arg ) {
    vll fac=pr.getFactors ( arg );
    sort ( all ( fac ) );
    fac.resize ( unique ( all ( fac ) )-fac.begin() );
    for ( ll x: fac ) {
        arg=arg/x* ( x-1 );
    }
    return arg;
}

ll run(ll n,ll k){
    for ( ll i=0; i<k; i++ ) {
        n=phi ( n );
        if ( n==1 ) break;
    }
    return n;
}
// void testLL() {
//     clock_t start=clock();
//     int nRuns=1000;
//
//     reu ( i,1,nRuns ) {
//         int t2=rand() % ( 1<<20 );
//         ll t= ( ll ) t2* ( rand() % ( 1<<20 ) );
//         ll res=run ( t, 30 );
//         cout<<t<<" "<<res<<endl;
//
//
//     }
//     cout<<"per Run: "<<double ( clock()-start ) /CLOCKS_PER_SEC*1000/nRuns<<endl;
// //     cout<<vector<pair<int,pair<ll,pll>>> ( all ( runs ) );
// }

int main() {

    ios_base::sync_with_stdio ( false );
    ll n,k;
    cin>>n>>k;

    cout<<run(n,k)<<endl;


}
