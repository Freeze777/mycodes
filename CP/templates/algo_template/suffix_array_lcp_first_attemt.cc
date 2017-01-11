#include <bits/stdc++.h>
using namespace std;
#define MAX 50009
#define PB push_back
#define MP make_pair
int srank[20][MAX];
#define fast_io               ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
struct triplet
{
public:
  int pos;
  int firstHalf,secondHalf;
};
bool compare(const triplet &a , const triplet &b)
{
  return a.firstHalf == b.firstHalf ?a.secondHalf < b.secondHalf :a.firstHalf < b.firstHalf;
}
int * suffix_array(triplet t[] , char s[], int length ){
  int pos = 0;
  int *arr = (int*)calloc(length+9,sizeof(int));
  for(int i=0;i<length;i++)
  srank[0][i] = s[i] - 'A';
  for(int cnt = 1,stp = 1;(cnt>>1) < length ; cnt<<=1,stp++)
  {
    for(int i = 0;i<length ; i++)
    {
      t[i].firstHalf = srank[stp-1][i];
      t[i].secondHalf = i+cnt < length ? srank[stp-1][i+cnt] : -1;
      t[i].pos = i;
    }
    sort(t,t+length,compare);
    int rnk = 0;
    for(int i = 0 ; i<length ; i++)
    {
      if((i > 0) && (t[i-1].firstHalf == t[i].firstHalf && t[i].secondHalf == t[i-1].secondHalf))
      rnk = srank[stp][t[i-1].pos];
      else
      rnk = i;
      srank[stp][t[i].pos] = rnk;
    }
  }
  pos = ceil(log(length)/log(2));
  for(int i=0;i<length;i++)
  arr[srank[pos][i]] = i;
  return arr;
}
int LCP(int i,int j,int n)
{
  int res = 0;
  if(i==j)
  return n - i;
  for(int stp = ceil(log(n)/log(2)) ; stp>=0 && i < n && j < n ; stp--)
  if(srank[stp][i] == srank[stp][j])
  {
    res += 1<<stp;
    i += 1<<stp;
    j += 1<<stp;
  }
  return res;
}
int LCParray(char s[],int p[],int n)
{
  int sum = 0;
  for(int i = 1 ; i < n ; i++)
  sum += (LCP(p[i-1],p[i],n));
  return sum;
}
int main()
{
  fast_io;
  int len,q;
  cin>>len>>q;
  string s;
  cin>>s;
  //char *str=s.c_str();

  for(int j=0;j<q;j++){
    int left,right;
    cin>>left>>right;
    int n = right-left+1;
    char str[n];
    for(int i=0;i<n;i++)str[i]=s[left+i];
    triplet t[n + 9];
    int *p = suffix_array(t,str,n);
    long long lcp_sum = 0,suffix_sum = 0;
    lcp_sum = LCParray(str,p,n);
    for(int i=0;i<n;i++)
    suffix_sum += p[i];
    long long ans=(long long)n*n - lcp_sum - suffix_sum;
    cout<<ans<<endl;
  }

  return 0;
}

