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
    int polyA[] = {1, 2, 3};
    recursive_FFT(polyA);

    return 0;
}

int* recursive_FFT(int* a)
{
    int i = 0, k = 0;
    int n = sizeof(a);
    double w, wn;
    static int y[sizeof(a)/2];
    static int a0[sizeof(a)/2], a1[sizeof(a)/2];
    int* y0;
    int* y1; // y0[sizeof(a)/2], y1[sizeof(a)/2];
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

