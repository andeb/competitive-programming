//============================================================================
// Name        : spoj.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <vector>

using namespace std;

int main() {

	while (true) {
		int n;
		cin >> n;

		if (n == 0) {
			break;
		}

		vector<int> p(n);
		vector<int>::iterator it;

		for (int i = 0; i < n; i++) {
	        cin >> p[i];
		}
		sort(p.begin(), p.end());

		int m = 0;
		for (int i = 0, j = n / 2; i < j; i++) {
			if (p[i] + p[n - i - 1] > m)
				m = p[i] + p[n - i - 1];
		}

		std::cout << m << endl;
	}

	return 0;
}
