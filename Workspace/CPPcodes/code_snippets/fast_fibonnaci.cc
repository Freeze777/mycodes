#include <bits/stdc++.h>

using namespace std;

#define MOD 1000000007;
long long int a, b, c, d;

void fast_fib(long long int n, long long int ans[]) {
	if (n == 0) {
		ans[0] = 0;
		ans[1] = 1;
		return;
	}
	fast_fib((n / 2), ans);
	a = ans[0]; /* F(n) */
	b = ans[1]; /* F(n+1) */
	c = 2 * b - a;
	if (c < 0)
		c += MOD
	;
	c = (a * c) % MOD
	; /* F(2n) */
	d = (a * a + b * b) % MOD
	; /* F(2n + 1) */
	if (n % 2 == 0) {
		ans[0] = c;
		ans[1] = d;
	} else {
		ans[0] = d;
		ans[1] = c + d;
	}
}

int main() {
	long long int n; /* nth value to be found */
	scanf("%lld", &n);
	long long int ans[2] = { 0 };
	fast_fib(n, ans);
	printf("%lld\n", ans[0]);
	return 0;
}

#include <stdio.h>
#include <vector>
#include <map>
#include <iostream>
#include <algorithm>
using namespace std;

#define long long long
const long M = 1000000007; // modulo

map<long, long> F;
vector<long> T[1234];

long f(long n, int Depth) {
	T[Depth].push_back(n);
	if (F.count(n))
		return F[n];
	long k = n / 2;
	if (n % 2 == 0) { // n=2*k
		return F[n] = (f(k, Depth + 1) * f(k, Depth + 1)
				+ f(k - 1, Depth + 1) * f(k - 1, Depth + 1)) % M;
	} else { // n=2*k+1
		return F[n] = (f(k, Depth + 1) * f(k + 1, Depth + 1)
				+ f(k - 1, Depth + 1) * f(k, Depth + 1)) % M;
	}
}

int main() {
	long n;
	F[0] = F[1] = 1;
	if (cin >> n) {
		if (n == 0)
			cout << 0 << endl;
		else
			cout << f(n - 1, 0) << endl;
	}
	for (int i = 0; i < 1234; i++)
		if (T[i].size()) {
			sort(T[i].begin(), T[i].end());
			T[i].erase(unique(T[i].begin(), T[i].end()), T[i].end());
			printf("Depth[%d] : ", i);
			for (int j = 0; j < T[i].size(); j++)
				printf("%lld ", T[i][j]);
			printf("\n");
		}
}
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
int findLCA(int a, int b) {
	int I, I1, I2, _2i = 1, n, m, r, rm;
	int sigama1, sigama2, sigama, delta;
	m = (int) (log10(a) / log10(2));
	n = (int) (log10(b) / log10(2));
	I = n - m;
	_2i = 1;
	_2i <<= I;
	r = (int) floor(b / ((double) _2i));
	if (r == a)
		return a / 2;
	else if (r < a) {
		int t = a;
		a = r;
		r = t;
	}
	delta = r - a;
	I1 = (int) floor(log10(delta) / log10(2)) + 1;
	_2i = 1;
	_2i <<= I1;
	sigama1 = (int) floor(a / ((double) _2i));
	sigama2 = (int) floor(r / ((double) _2i));
	if (sigama1 == sigama2)
		return sigama2;
	a = sigama1;
	_2i = 1;
	for (int i = 1;; i++) {
		_2i <<= 1;
		rm = a & (_2i - 1);
		if (rm < _2i / 2) {
			I2 = i;
			break;
		}
	}
	sigama = (int) floor(a / ((double) _2i));
	return sigama;
}

int main() {
	/* Enter your code here. Read input from STDIN. Print output to STDOUT */
	return 0;
}

