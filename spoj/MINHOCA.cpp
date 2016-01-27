#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>

using namespace std;

int main() {
	//freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);

	int m, n;
	scanf("%d %d", &m, &n);

	int r, s, t = 0;
	int array[n];
	std::fill_n(array, n, 0);

	for (int i = 0; i < m; ++i) {
		r = 0;
		for (int j = 0; j < n; ++j) {
			scanf("%d", &s);
			array[j] += s; r += s;
		}
		if (r > t)
			t = r;
	}
	for (int i = 0; i < n; ++i)
		if (array[i] > t)
			t = array[i];
	printf("%d", t);
	return 0;
}
