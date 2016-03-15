#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int A[1000006];

int main() {

    int N, K, M;
    cin >> N >> K >> M;

    for(int i=1; i<=N; i++)
    {
        int x;
        cin >> x;

        A[x]++;
    }
    M = min(M,N);
    sort(A, A+1000001);

    int j = 1000000;

    for(int i=0; M && i<1000000; i++)
    {
        while(M && A[i])
            A[i]--, A[j]++, M--;
    }

    int ans = 0;

    for(int i=0; i<=1000000; i++)
        if(A[i]>=K+2)
            ans+= A[i]-(K+2)+1;

    cout << ans << endl;

    return 0;
}
