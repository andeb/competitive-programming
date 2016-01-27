#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>

using namespace std;

bool isPrime(int r) {
	if (r <= 3) {
		return true;
	}
	if (r % 2 == 0) {
		return false;
	}
	for (int i = 3; i * i <= r; i += 2) {
		if (r % i == 0) {
			return false;
		}
	}
	return true;
}

int main() {
	// freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);

	int soma;
	char str[10001];
	while (scanf("%s", str) == 1) {
		soma = 0;
		for (unsigned int i = 0; i < strlen(str); ++i) {
			if (str[i] >= 'a' && str[i] <= 'z') {
				soma += str[i] - 'a' + 1;
			} else {
				soma += str[i] - 'A' + 27;
			}
		}

		// printf("%d\n", soma);

		bool p = isPrime(soma);
		printf("%s%s%s\n", "It is ", p ? "" : "not ", "a prime word.");
	}

	return 0;
}
