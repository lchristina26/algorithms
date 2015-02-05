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

double* recursive_FFT(double* a, int n);

int main () 
{
    /* for fft */
    int x = 5, n = 5, i = 0, sum = 0;
    //double *a = (double *)malloc(2*n*sizeof(double));
    double* polyB = (double *)malloc(2*n*sizeof(double));
    double a[] = {4, 3, 3, 5, 7};
    /*for(i = 0; i < n; i++) {
        a[i] = ((rand() % 1) + 1);
        printf("Poly A at %d = %f\n", i, a[i]);
    }*/
    
    polyB = recursive_FFT(a, n);
    for (i = 0; i < n; i++) {
        printf("FFT Coefficient %d = %lf\n", i, polyB[i]); 
    } 
    return 0;
}

double* recursive_FFT(double* a, int n)
{
    int i = 0, k = 0;
    int size = n/2;
    double w, wn;
    double* y = (double *)malloc(2*n*sizeof(double));
    double* a0, *a1;
    double* y0;
    double* y1; 
    y0 = (double *)malloc((n*2)*sizeof(double));
    y1 = (double *)malloc((n*2)*sizeof(double));
    a0 = (double *)malloc((n*2)*sizeof(double));
    a1 = (double *)malloc((n*2)*sizeof(double));
    /*    int a0[size], a1[size], y[size], y0[size], y1[size];*/
    if (n == 1)
        return a;

    for (i = 0; i < n; i++) {
        if (!(i % 2)) {
            a0[i] = a[i];
        }
        else {
            a1[i] = a[i];
        }
    }
    y0 = recursive_FFT(a0, size);
    y1 = recursive_FFT(a1, size);
    w = 1;
    wn = exp(((2*M_PI))/n);
    for (k = 0; k <= n/2; k++) {
        printf("Wn = %f, W = %f\n", wn, w);
        y[k] = y0[k] + (w * y1[k]);
        printf("Real Val: %lf\n", y[k]);
        y[k + n/2] = y0[k] - (w * y1[k]);
        printf("Imagn Val: %lf\n", y[k + n/2]);
        w = w * wn;    
    }
    return y;
}

