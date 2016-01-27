#include<stdio.h>
  
int main() { 
        int n, g, a, t;
        while (scanf("%d", &n), n!=0) {
                g=0; a=0, t=0;
                for (; n>0; --n) {
                        scanf("%d", &t);
                        a += t;
 
                        if (a>g)g=a;
                        if (a<0)a=0;
                }
 
        if (g == 0)
            printf("Losing streak.\n");
        else
            printf("The maximum winning streak is %d.\n", g);
         }

    return 0;
}

