#include <bits/stdc++.h>

typedef long long ll;
using namespace std;

#define all(x) x.begin(), x.end()
#define f(i,a,b) for(int i = (a); i <= (b); i++)
#define fd(i,a,b) for(int i = (a); i >= (b); i--)
#define mp make_pair
#define faster_io() ios_base::sync_with_stdio(false)
#define pb push_back
#define pii pair<int,int>
#define SZ(x) ((int)x.size())
#define vii vector<pair<int,int>>

const int INF = 1000000007;
const ll INFLL = 100000000000000000ll;
const ll MOD = 1000000007;


bool U[31705];
ll A, B;
unordered_set<ll> NP;
vector<ll> P;

int main()
{
   faster_io();
  // Generate list of primes <= sqrt(10^9)

  f(i,2,31700) if(!U[i]) for(int k = 2*i; k <= 31700; k += i) U[k] = true;
  f(i,2,31700) if(!U[i]) P.pb(i);

  NP.insert(1); // Mark 1 as not prime

  cin >> A >> B;

  // Generate all non-prime numbers in the range [A,B]

  for(int p : P)
  {
    int x = max(A/p,2LL);
    for(int k = p*x; k <= B; k += p) NP.insert(k);
  }

  // Check what numbers are prime in range [A,B]
  ll prev=-1;
  ll count=0;
  f(i,A,B){
    if(NP.find(i) == NP.end()){
      //i is a prime number in the range [A,B]
      if (prev==-1) {
        prev=i;
      }else{
        if(i-prev==2)count++;
        prev=i;
      }
    }
  }
  cout<<count<<endl;
  return 0;
}
