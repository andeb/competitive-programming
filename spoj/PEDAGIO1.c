#include<stdio.h>

int main() {

 int l, d, k, p;
 scanf("%d %d", &l, &d);
 scanf("%d %d", &k, &p);
 printf("%d", (l*k) + ((l/d)*p));

 return 0;
}