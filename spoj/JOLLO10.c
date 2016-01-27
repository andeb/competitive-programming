#include <stdio.h>
#include <stdlib.h>

void cx(int *q, int *w, int iy);
void cy(int *q, int *w, int ix);
int f(int *q, int x);
int next(int *q, int w);

void cx(int *q, int *w, int iy) {
	switch (iy) {
	case 0:
		printf("%d\n", next(w, q[2]));
		break;
	case 1:
		printf("%d\n", next(w, q[2]));
		break;
	case 2:
		printf("%d\n", next(w, q[1]));
		break;
	}
}

void cy(int *q, int *w, int ix) {
	switch (ix) {
	case 0:
		printf("%d\n", -1);
		break;
	case 1:
		printf("%d\n", -1);
		break;
	case 2:
		printf("%d\n", next(w, q[1]));
		break;
	}
}

int next(int *q, int w) {
	e: for (;;) {
		if (++w == 53)
			return -1;
		int i;
		for (i = 0; i <= sizeof(q); i++) {
			if (q[i] == w)
				goto e;
		}
		break;
	}
	return w;
}

int compare(const void *a,const void *b) {
    const int *ia = (const int *)a;
    const int *ib = (const int *)b;
    return *ia  - *ib;
}

int f(int *q, int x) {
	if (x < q[0])
		return 0;
	if (x < q[1])
		return 1;
	if (x < q[2])
		return 2;
	return 3;
}

int main() {
	// freopen("JOLLO10.in", "r", stdin);

	while (1) {
		int a, b, c, x, y;
		scanf("%d %d %d %d %d", &a, &b, &c, &x, &y);
		if (a == 0 && b == 0 && c == 0 && x == 0 && y == 0)
			break;

		int q[] = { a, b, c };
		int w[] = { a, b, c, x, y };

		size_t numbers_len = sizeof(q)/sizeof(int);
		qsort(q, numbers_len, sizeof(int), compare);

		int ix = f(q, x);
		int iy = f(q, y);

		if (ix == 3 && iy == 3) {
			printf("%d\n", next(w, 0));
		} else {
			if (ix == 3 || iy == 3)
				cx(q, w, ix < iy ? ix : iy);
			else if (ix == 2 || iy == 2)
				cy(q, w, ix < iy ? ix : iy);
			else
				printf("%d\n", -1);
		}

	}

	return 0;
}

