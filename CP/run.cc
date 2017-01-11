#include <bits/stdc++.h>
using namespace std;
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define ll long long int
#define LEFTMOST(x) (63-__builtin_clzll((x)))

int main(){
  fast_io;
  ll t,x;cin>>t;
  while(t--){
    cin>>x;
    ll mask=(1LL<<(1+LEFTMOST(x)))-1;
    cout<<((~x)&mask)<<endl;
  }
  return 0;
}
