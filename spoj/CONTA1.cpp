#include <map>
#include <stdio.h>

int main() {
	//freopen("CONTA1.in", "r", stdin);

	int v;
	scanf("%d", &v);

	int t = 7;
	if (v > 10) {
		v -= 10;

		if (v > 20) {
			t += 20;
			v -= 20;

			if (v > 70) {
				t += 70 * 2;
				v -= 70;

				t += v * 5;
			} else {
				t += v * 2;
			}
		} else {
			t += v;
		}
	}

	printf("%d", t);

}
