#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>
#include <bitset>
 
using namespace std;
 
int f91(int n) {
	if (n <= 100)
		return f91(f91 (n + 11));
	else
		return n - 10;
}
 
int main() {
	//freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);
 
	int n;
	while (scanf("%d", &n)) {
		if (n == 0) break;
		printf("f91(%d) = %d\n", n, f91(n));
	}
	return 0;
}
