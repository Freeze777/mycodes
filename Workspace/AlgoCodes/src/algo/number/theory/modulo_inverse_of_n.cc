#include<stdio.h>

long long mod=1000000007;

long long power(long long a,int b){ // function used to find (a^b)%mod
	long long ans=1;
	while(b){
		if(b&1)
			ans=(ans*a)%mod;
		a=(a*a)%mod;
		b>>=1;
	}
	return ans;
}

int main(){
	int a;
	scanf("%d",&a);
	printf("%lld\n",power(a,mod-2));
	return 0;
}
