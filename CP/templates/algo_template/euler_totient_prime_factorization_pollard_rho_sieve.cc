#include <bits/stdc++.h>
using namespace std;
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define ll long long int
#define rep(i,n) 			for(ll i = 0 ; i < n ; ++i)
typedef vector<ll> vll;
#define pb                    push_back
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
ll isSquare ( ll x ) {
  ll sq=sqrt ( x ) +0.5;
  if ( sq*sq==x ) return sq;
  return 0;
}
ll modPow ( ll a, ll k,ll mod ) {
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
bool MRIsPrime ( ll n ) {
  if ( n<2 ) return false;
  vll tests ( {2,3,61} );
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

bool isPrime ( ll n ) {
  return MRIsPrime ( n );
}

ll PollardRhoFactorOnce ( ll number, ll l=1 ) {
  ll sqr=isSquare ( number );
  if ( sqr ) return sqr;
  if ( isPrime ( number ) ) return number;
  ll x_fixed = 2,cycle_size = 2,factor = 1;
  ll x = 2;
  while ( factor == 1 ) {
    for ( ll count=1; count <= cycle_size && factor <= 1; count++ ) {
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


int main(){
  fast_io;
  generate();
  ll n,mod;cin>>n;
  mod=n;
  n--;
  vll powers;
  ll phi=n;

  for(auto &it:primes){
    if(n%it==0){
      while(n%it==0)n/=it;
      phi-=phi/it;
      powers.pb((mod-1)/it);
    }
  }
  while(n>1 && !isPrime(n)){
    ll pfact=PollardRhoFactorOnce(n);
    powers.pb((mod-1)/pfact);
    n/=pfact;
    phi-=phi/pfact;
  }
  if(n>1){
    powers.pb((mod-1)/n);
    phi-=phi/n;
  }
  ll i;
  for (i = 2; i <= mod-1; i++) {
    bool flag=true;
    for(ll f:powers){
      flag=flag && (modPow(i,f,mod)!=1);
      if(!flag)break;
    }
    if(flag)break;
  }
  cout<<i<<" "<<phi<<endl;

  return 0;
}
