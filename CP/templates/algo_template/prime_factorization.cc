#include <bits/stdc++.h>
using namespace std;
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define MAX 1000001
#define ll long long
bool v[MAX];
int len, sp[MAX],dp_grundy[MAX];
void sieve(){
  sp[1]=1;
  dp_grundy[1]=0;
  dp_grundy[2]=1;
  for (ll i = 2; i < MAX; i += 2) sp[i] = 2;//even numbers have smallest prime factor 2
  for (ll i = 3; i < MAX; i += 2){
    if (!v[i]){
      sp[i] = i;
      for (ll j = i; (j*i) < MAX; j += 2){
        if (!v[j*i]){
           v[j*i] = true;
           sp[j*i] = i;
         }
      }
    }
  }
  for (int i = 3; i < MAX; i++) dp_grundy[i]=dp_grundy[i/sp[i]]+1;
}
int main(){
  fast_io;
  sieve();
  int t;cin>>t;
  while(t--){
    int n,p,nim_sum=0;cin>>n;
    while(n--){
      cin>>p;
      nim_sum^=dp_grundy[p];
    }
    cout<<((nim_sum==0)?"2":"1")<<endl;
  }
  return 0;
}
