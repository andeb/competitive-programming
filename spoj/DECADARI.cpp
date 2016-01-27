#include <stdio.h>
#include <stdlib.h>

const int MILLI = 100;
const int SECON_TO_MILLI = MILLI;
const int MINUT_TO_MILLI = SECON_TO_MILLI * 60;
const int HORAS_TO_MILLI = MINUT_TO_MILLI * 60;
const int TOTAL_MILLIS_100 = 10000000;
const int TOTAL_MILLIS_24 = 8640000;

const int MOD = 100;
const int MASK_H = 1000000;
const int MASK_M = 1000000;
const int MASK_S = 10000;
const int MASK_L = 100;

int main() {
	int i;
	while (scanf("%d", &i) != EOF) {
		int CC = i % MOD;
		i /= MOD;
		int SS = i % MOD;
		i /= MOD;
		int MM = i % MOD;
		i /= MOD;
		int HH = i;

		double t = (HH * HORAS_TO_MILLI) + (MM * MINUT_TO_MILLI) +
				   (SS * SECON_TO_MILLI) + CC;
		double r = (t * TOTAL_MILLIS_100) / TOTAL_MILLIS_24;

		int L_DEC = (int) r % MOD;
		r /= 100;
		int S_DEC = (int) r % MOD;
		r /= 100;
		int M_DEC = (int) r % MOD;
		r /= 10;
		int H_DEC = (int) r / 10;

		printf("%d%s%d%s%d%s%d\n", H_DEC,
				M_DEC < 10 ? "0" : "" , M_DEC,
				S_DEC < 10 ? "0" : "" , S_DEC,
				L_DEC < 10 ? "0" : "" , L_DEC);
	}

	return 0;
}
