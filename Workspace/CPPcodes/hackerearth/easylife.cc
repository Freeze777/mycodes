// Generalized Spanning Trees
// Source: CodeChef May 2010 Algorithm Challenge
// CodeChef ID: GST
// Comments:
// Maximum Density Subgraph in O(log n * maxflow(n,m))

#include <algorithm>
#include <cassert>
#include <cmath>
#include <cstdio>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long LL;
typedef LL data;
const data INF = (1LL << 62) - 1;
const int MAX_EDGES = 2000;
const int MAXV = 1000; // maximum number of vertices.
int N, M; // # vertices, #edges.
struct edge {
	int x, y, rev;
	data f;
	LL cap;
	edge(int a = 0, int b = 0, LL c = 0, int r = 0) {
		x = a, y = b, f = 0, cap = c, rev = r;
	}
};
edge E[MAX_EDGES]; // edge list.
vector<int> aj[MAXV], r_aj[MAXV]; // adjacency/reverse adjacency list.
data dist[MAXV], aug[MAXV]; // distances for bfs.
edge* pa[MAXV]; // predecessors.
int nod[MAXV], cur_node[MAXV];  // nod[k]: no. nodes with dist==k.

void rev_bfs(int t) { // reverse bfs starting at t.
	for (int i = 0; i < N; i++)
		dist[i] = N;
	nod[N] = N - 1, nod[0] = 1;
	queue<int> q;
	q.push(t);
	dist[t] = 0;
	while (!q.empty()) {
		int u = q.front();
		q.pop();
		for (int k = 0; k < r_aj[u].size(); k++) {
			edge &e = E[r_aj[u][k]];
			if (dist[e.x] < N || e.f == e.cap)
				continue;
			q.push(e.x), nod[N]--, dist[e.x] = 1 + dist[u], nod[dist[e.x]]++;
		}
	}
}
data augment(int s, int t) {
	int wk = t, wk1;
	edge* e;
	while (s != wk)
		e = pa[wk], wk1 = e->x, e->f += aug[t], E[e->rev].f -= aug[t], wk = wk1;
	return aug[t];
}
int retreat(int &i, int s) {
	int min_dist = N - 1;
	for (int j = 0; j < aj[i].size(); j++) {
		edge& e = E[aj[i][j]];
		if (e.f < e.cap && dist[e.y] < min_dist)
			min_dist = dist[e.y];
	}
	int tmp = dist[i];
	nod[dist[i]]--, dist[i] = 1 + min_dist, nod[dist[i]]++;
	if (i != s)
		i = pa[i]->x;
	return nod[tmp];
}
LL maxflow(int s, int t) {
	for (int i = 0; i < M; i++)
		E[i].f = 0;
	for (int i = 0; i < N; i++)
		cur_node[i] = 0, aug[i] = INF;
	LL flow = 0;
	rev_bfs(t);
	for (int j, i = s; dist[s] < N;) {
		for (j = cur_node[i]; j < aj[i].size(); j++) {
			edge& e = E[aj[i][j]];
			if (e.f < e.cap && dist[i] == dist[e.y] + 1)
				break;
		}
		if (j < aj[i].size()) {
			edge& e = E[aj[i][j]];
			cur_node[i] = j, pa[e.y] = &e, aug[e.y] = min(aug[i], e.cap - e.f), i =
					e.y;
			if (i == t) {
				flow += augment(s, t), i = s;
				for (int i = 0; i < N; i++)
					aug[i] = INF;
			}
		} else {
			cur_node[i] = 0;
			{
				if (0 == retreat(i, s))
					break;
			}
		}
	}
	return flow;
}
void init(int a) { // Remember always to init!!!
	N = a, M = 0;
	for (int i = 0; i < N; i++)
		aj[i].clear(), r_aj[i].clear();
}
void add_edge(int x, int y, LL c) {
	E[M] = edge(x, y, c, M + 1), E[M + 1] = edge(y, x, 0, M);
	aj[x].push_back(M), aj[y].push_back(M + 1);
	r_aj[x].push_back(M + 1), r_aj[y].push_back(M), M += 2;
}

// Identify a minimum cut by visiting all vertices reachable from the
// source (but not including the source) and placing them in res.
void find_min_cut(int u, int num, vector<int>& res, vector<int>& vis) {
	if (vis[u])
		return;
	vis[u] = 1;
	if (num != 0)
		res.push_back(u);
	for (int i = 0; i < aj[u].size(); ++i)
		if (E[aj[u][i]].f < E[aj[u][i]].cap)
			find_min_cut(E[aj[u][i]].y, num + 1, res, vis);
}

// Returns the sum of all edges in the subgraph induced by subset.
LL get_weight(const vector<int>& subset, const vector<vector<int> >& G,
		const vector<vector<LL> >& W) {
	vector<int> seen(G.size(), 0);
	for (int i = 0; i < subset.size(); ++i)
		seen[subset[i]] = 1;
	LL res = 0;
	for (int i = 0; i < subset.size(); ++i) {
		int u = subset[i];
		for (int j = 0; j < G[u].size(); ++j) {
			int v = G[u][j];
			if (seen[v])
				res += W[u][j];
		}
	}
	return res / 2;
}

// Returns a maximum density subgraph of the graph G with edge weights in W.
// Note: we assume that there are no self-loops.
vector<int> maximum_density_subgraph(const vector<vector<int> >& G,
		const vector<vector<LL> >& W) {
	int n = G.size();
	LL sum_weights = 0;
	vector<LL> deg(n, 0);
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < W[i].size(); ++j)
			deg[i] += W[i][j];
		sum_weights += deg[i];
	}
	sum_weights /= 2;
	LL l = 0, u = sum_weights, mul2 = 0;

	LL p = 1;
	while (p * 2 <= u)
		p *= 2;
	u = p;

	vector<int> res;
	while ((((u - l) * n * (n - 1)) >> mul2) >= 1) {
		//printf("L: %lld U: %lld\n", l,u);
		//printf("\tTRY GUESS %.3lf\n", (u+l)/double(1<<(mul2+1)));
		LL g = u + l;
		bool odd = (u + l) & (1LL);

		init(n + 2); // Construct max-flow network
		const int S = n, T = n + 1;

		for (int i = 0; i < n; ++i) {
			add_edge(S, i, (sum_weights << mul2));
			add_edge(i, T, ((sum_weights - deg[i]) << mul2) + g);
			for (int j = 0; j < G[i].size(); ++j)
				add_edge(i, G[i][j], (W[i][j] << mul2));
		}

		LL mincut = maxflow(S, T);
		//printf("\tMINCUT: %lld SUMW*N %lld\n", mincut, (sum_weights*n)<<mul2);
		assert(mincut <= (sum_weights * n) << mul2);
		if (mincut == ((sum_weights * n)) << mul2) {
			if (odd)
				u = g, l <<= 1, ++mul2;
			else
				u = g >> 1;
		} else {
			if (odd)
				l = g, u <<= 1, ++mul2;
			else
				l = g >> 1;
			res.clear();
			vector<int> vis(n + 2, 0);
			find_min_cut(S, 0, res, vis);
			LL weight = get_weight(res, G, W);
			//printf("NOW MAX WEIGHT %lld SIZE %d %.3lf\n", weight, res.size(), weight/double(res.size()));
		}
	}
	return res;
}

// TESTING CODE #1
//{{{
#include <set>

// Try all subgraphs and return one with maximum density
vector<int> brute_force(const vector<vector<int> >& G,
		const vector<vector<LL> >& W) {
	int n = G.size();
	LL bestw = 0, bestn = 1;
	vector<int> ans;
	for (int i = 0; i < (1 << n); ++i) {
		vector<int> cur;
		for (int j = 0; j < n; ++j)
			if ((1 << j) & i)
				cur.push_back(j);
		LL w = get_weight(cur, G, W);
		if (w * bestn > bestw * cur.size())
			bestw = w, bestn = cur.size(), ans = cur;
	}
	return ans;
}

void test() {
	int n = 1 + (rand() % 12);
	vector<vector<int> > G(n);
	vector<vector<LL> > W(n);
	vector<set<int> > seen(n);
	for (int i = 0; i < n; ++i) {
		int am = rand() % (n - G[i].size());
		while (am-- > 0) {
			while (1) {
				int x = rand() % n;
				if (i != x && !seen[i].count(x)) {
					seen[i].insert(x);
					seen[x].insert(i);
					G[i].push_back(x);
					G[x].push_back(i);
					int w = rand() % 10;
					W[i].push_back(w);
					W[x].push_back(w);
					//printf("ADD EDGE %d => %d W: %d\n", i,x, w);
					break;
				}
			}
		}
	}

	vector<int> bf = brute_force(G, W);
	//printf("STARTING COM n=%d\n", n);
	vector<int> me = maximum_density_subgraph(G, W);
	//printf("ENDING COM\n");
	if (get_weight(me, G, W) * bf.size() != get_weight(bf, G, W) * me.size()) {
		//printf("FAIL ON GRAPH OF SIZE %d\n", G.size());
		//printf("BF HAS WEIGHT %lld SZ: %d %.3lf\n", get_weight(bf,G,W), bf.size(), get_weight(bf,G,W)/double(bf.size()));
		//printf("ME HAS WEIGHT %lld SZ: %d %.3lf\n", get_weight(me,G,W), me.size(), get_weight(me,G,W)/double(me.size()));
		assert(0);
	}
}
//}}}
// END TESTING CODE #1

const int FAIL_SUM = 1;
const int FAIL_SUBSET = 2;
const int GST = 3;

LL gcd(LL a, LL b) {
	return b == 0 ? a : gcd(b, a % b);
}
LL lcm(LL a, LL b) {
	return a / gcd(a, b) * b;
}

// Returns true iff the density if > D
bool density_gt(LL D, const vector<vector<int> >& G,
		const vector<vector<LL> >& W, vector<int>& res) {
	int n = G.size();
	LL sum_weights = 0;
	vector<LL> deg(n, 0);
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < W[i].size(); ++j)
			deg[i] += W[i][j];
		sum_weights += deg[i];
	}
	sum_weights /= 2;

	// Initial guess
	LL g = D;
	init(n + 2); // Construct max-flow network
	const int S = n, T = n + 1;

	for (int i = 0; i < n; ++i) {
		add_edge(S, i, sum_weights);
		add_edge(i, T, sum_weights - deg[i] + 2 * g);
		for (int j = 0; j < G[i].size(); ++j)
			add_edge(i, G[i][j], W[i][j]);
	}

	LL mincut = maxflow(S, T);
	if (mincut == sum_weights * n) {
		// The maximum density is <= D
		return 0;
	}

	// The maximum density is > D.
	res.clear();
	vector<int> vis(n + 2, 0);
	find_min_cut(S, 0, res, vis);
	return 1;
}

void faster_solve(int n, const vector<pair<int, int> >& e,
		const vector<pair<LL, LL> >& ew) {
	int m = e.size();
	vector<vector<int> > G(n);
	vector<vector<LL> > W(n);

	for (int k = 0; k < m; ++k) {
		for (int i = 0; i < n; ++i)
			G[i].clear(), W[i].clear();
		for (int i = 0; i < m; ++i) {
			int a = e[i].first, b = e[i].second;
			G[a].push_back(b);
			G[b].push_back(a);
			W[a].push_back(1);
			W[b].push_back(1);
		}
		vector<int> res;
		density_gt(-1, G, W, res);

	}
}
// SLOWER SOLUTION
//{{{
void solve(int n, const vector<pair<int, int> >& e,
		const vector<pair<LL, LL> >& ew) {
	int m = e.size();
	vector<vector<int> > G(n);
	vector<vector<LL> > W(n);
	for (int i = 0; i < m; ++i) {
		int a = e[i].first, b = e[i].second;
		G[a].push_back(b);
		G[b].push_back(a);
		W[a].push_back(1);
		W[b].push_back(1);
	}
	vector<int> res = maximum_density_subgraph(G, W);
	LL weight = get_weight(res, G, W);
	if (weight > res.size()) {
		cout << ">1" << endl;
	} else {
		LL g=gcd(weight,res.size());
		cout<<(weight/g)<<"/"<<(res.size()/g)<<endl;
		//cout << weight << "/" << res.size() << endl;
	}
}
//}}}
// END SLOWER SOLUTION

int main() {
	//while (1) GST_test();

	int n, m;
	scanf("%d %d", &n, &m);
	vector<pair<int, int> > e(m);
	vector<pair<LL, LL> > ew(m);
	for (int i = 0; i < m; ++i) {
		scanf("%d %d", &e[i].first, &e[i].second);
		--e[i].first;
		--e[i].second;
		ew[i].first = 1;
		ew[i].second = 1;
	}

	solve(n, e, ew); // faster_solve(n, e, ew);

}

