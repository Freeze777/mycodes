#include <cmath>
#include <iostream>
using namespace std;
int minOfadds(int n, int d) {
	int prev = n, mini = n;
	int t = 0;
	do {
		t = 0;
		int dd = d;
		while (dd >= 0) {
			int div = pow(10, dd);
			int l = ((prev / div) + 1) % 10;
			prev %= div;
			t = t * 10 + l;
			dd--;
		}
		mini = min(t, mini);
		prev = t;
	} while (t != n);
	return mini;
}
int main() {
	int d;
	cin >> d;
	d--;
	int n;
	cin >> n;
	int t = n, mini = n;
	do {
		t = (t % 10) * pow(10, d) + (t / 10);
		mini = min(minOfadds(t, d), mini);
	} while (t != n);
	int dd = ceil(log10(mini));
	d++;
	int diff = d - dd;
	while (diff--)
		cout << 0;
	cout << mini;
	return 0;
}
