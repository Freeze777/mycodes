#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
const int N = 30000000;
int lp[N + 1];
int phi[N + 1];
vector<int> pr;

void calc_sieve() {
	phi[1] = 1;
	for (int i = 2; i <= N; ++i) {
		if (lp[i] == 0) {
			lp[i] = i;
			phi[i] = i - 1;
			pr.push_back(i);
		} else {
			//Calculating phi
			if (lp[i] == lp[i / lp[i]])
				phi[i] = phi[i / lp[i]] * lp[i];
			else
				phi[i] = phi[i / lp[i]] * (lp[i] - 1);
		}
		for (int j = 0; j < (int) pr.size() && pr[j] <= lp[i] && i * pr[j] <= N;
				++j)
			lp[i * pr[j]] = pr[j];
	}
}
ll getPhi(ll n) {
	//Memomization
	if (n <= N){
	//	cout<<"hit"<<endl;
		return phi[n];
	}
	ll result = n;

	for (ll p = 2; p * p <= n; ++p) {
		if (n % p == 0) {
			while (n % p == 0)
				n /= p;
			result -= result / p;
		}
	}
	if (n > 1)
		result -= result / n;
	return result;
}
/*Notes
 * phi(n)=n is only valid for n=1;
 * so if there is a recursive funtion depending on phi(n)---> infinite loop
 * if gcd(m,n) = 1, phi(mn) = phi(m) * phi(n)
 * if p is prime, phi(p) = p - 1 (for p < 10^20)
 * if n is even, phi(2n) = 2 phi(n)
 *
 * */
