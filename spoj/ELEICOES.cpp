#include <algorithm>
#include <iostream>
#include <list>
#include <stdio.h>
#include <string.h>

using namespace std;

int main() {
	// freopen("input.txt", "rt", stdin);
	std::ios::sync_with_stdio(false);

	int size;
	scanf("%d", &size);
	int array[size];

	for (int i = 0; i < size; i++)
		scanf("%d", &array[i]);

	std::sort(array, array + size);

	int last = -1, max = 0, counter = 0, temp, result;
	for (int i = 0; i <= size; ++i) {
		temp = i == size ? -1 : array[i];
		if (temp != last) {
			if (counter > max) {
				result = last;
				max = counter;
			}
			last = temp;
			counter = 1;
		} else {
			++counter;
		}
	}
	cout << result;

	return 0;
}
