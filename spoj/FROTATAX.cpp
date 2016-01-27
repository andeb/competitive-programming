#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>

using namespace std;

int main() {
	//freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);

	double a, b, c, d;
	scanf("%lf %lf %lf %lf", &a, &b, &c, &d);

	double ret1 = b * 100 / a;
	double ret2 = d * 100 / c;

	printf(ret1 > ret2 ? "A" : "G");

	return 0;
}
