/* Simple Fast Fourier Transforms Program 
   Spring 2015 Algorithms as described in "Introduction to Algorithms" by 
   Cormen, Leiserson, Rivest, and Stein 
   to compile enter: 'gcc -o fft fft.c -lm'   
   by Monica Thornton and Leah Thompson */

#include <stdlib.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

#define M_PI 3.14159265358979323846

int* recursive_FFT(int* a, int n);

int main () 
{
    int i = 0;
    int polyA[] = {1, 2, 3};
    int* polyB;
    polyB = recursive_FFT(polyA, 3);
    
    for ( ; i < 3; i++) {
        printf("%d\n", *polyB);
        polyB++;
    }
    return 0;
}

int* recursive_FFT(int* a, int n)
{
    int i = 0, k = 0;
    int size = n/2;
    double w, wn;
    int* y = (int *)malloc(size);
    int* a0, *a1;
    int* y0;
    int* y1; // y0[sizeof(a)/2], y1[sizeof(a)/2];
    y0 = (int *)malloc(size);
    y1 = (int *)malloc(size);
    a0 = (int *)malloc(size);
    a1 = (int *)malloc(size);

    wn = pow(2.718, ((2*M_PI)/n));
    if (n == 1)
        return a;
    
    for (i = 0; i < n; i++) {
        if (i % 2) {
            //printf("%d, %d", i, i % 2);
            a0[i] = a[i];
        }
        else {
            //printf("%d, %d", i, i % 2);
            a1[i] = a[i];
        }
    }
    y0 = recursive_FFT(a0, size);
    printf("hellow");
    y1 = recursive_FFT(a1, size);

    for (k = 0; k <= n/2; k++) {
        y[k] = y0[k] + (w * y1[k]);
        y[k + n/2] = y0[k] - (w * y1[k]);
        w = w * wn;    
    }
    return y;
}

