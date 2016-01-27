#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>
#include <bitset>

using namespace std;

int const MAX_BITS = 1000 * 1000 + 1;

int main() {
	// freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);

	int n;
	scanf("%d", &n);

	std::bitset<MAX_BITS> bits;

	int c[n];
	std::fill_n(c, n, 0);

	int t, d1=0, d2=0, l, e = 0;
	bool noSolution = false;
	for (int i = 0; i < n; ++i) {
		l = 0;
		for (int j = 0; j < n; ++j) {
			scanf("%d", &t);
			// printf("%d\n", t);
			if (t > n * n || bits.test(t)) {
				noSolution = true;
				goto loop;
			}
			bits.set(t);
			c[i] += t;
			l    += t;
			if (        i == j) d1 += t;
			if ((n-1) - i == j) d2 += t;

			// fast check
			if (e != 0 && (d1 > e || d2 > e)) {
				noSolution = true;
				goto loop;
			}
		}
		if (e == 0)
			e = l;
		else if (l != e) {
			noSolution = true;
			goto loop;
		}
	}
	for (int i = 0; i < n; ++i)
		if (e != c[i]) {
			noSolution = true;
			goto loop;
		}
	loop:
	if (noSolution)
		printf("%d", 0);
	else
		printf("%d", e);
	return 0;
}
