/* Simple Fast Fourier Transforms Program 
   Spring 2015 Algorithms as described in "Introduction to Algorithms" by 
   Cormen, Leiserson, Rivest, and Stein 
   
   by Monica Thornton and Leah Thompson */

#include <math.h>
#include <stdio.h>
#include <string.h>

#define M_PI 3.14159265358979323846

int* recursive_FFT(int* a);

int main () 
{
    int polyA[3] = {1, 2, 3};
    recusrive_FFT(polyA, 3);

    return 0;
}

int* recursive_FFT(int* a, int n)
{
    int i = 0, k = 0;
    double w, wn;
    static int a0[(int)n/2 - 1], a1[(int)n/2 - 1], y[n], y0[(int)n/2 - 1], 
               y1[(int)n/2 - 1];

    wn = pow(2.718, ((2*M_PI)/n));

    if (n == 1)
        return a;
    for (i = 0; i < n; i++) {
        if (i % 2) {
            printf("%d, %d", i, i % 2);
            a0[i] = a[i];
        }
        else {
            printf("%d, %d", i, i % 2);
            a1[i] = a[i];
        }
    }
    y0 = recursive_FFT(a0);
    y1 = recursive_FFT(a1);

    for (k = 0; k <= n/2; k++) {
        y[k] = y0[k] + (w * y1[k]);
        y[k + n/2] = y0[k] - (w * y1[k]);
        w = w * wn;    
    }

    return y;
}

