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

//int* recursive_FFT(int* a, int n);
int *poly(int *ab, int x, int n);
int *poly_c(int *a, int *b, int x, int n);
int revBit(int num);
int reverseBits(int* a, int n, int* A);
int *i_fft(int *a, int x, int n);

int main () 
{
    /* for fft */
    int x = 5, n = 6, i = 0, sum = 0;
    int polyA[] = {1, 2, 3, 4, 5, 6};// = (int *)malloc(sizeof(int));
    int *a = (int *)malloc(sizeof(int));
    int *b = (int *)malloc(sizeof(int));
    int *A = (int *)malloc(sizeof(int));
    int *B = (int *)malloc(sizeof(int));
    int *C = (int *)malloc(sizeof(int));
    int* polyB;

    for(i = 0; i < n; i++) {
        a[i] = rand() % 100;
        b[i] = rand() % 100;
    }
    A = poly(b, x, n);
    B = poly(a, x, n);
    C = poly_c(A, B, x, n);
    for(i = 0; i < n; i++) {
        printf("%d\n", C[i]);
        sum = sum + C[i];
    }
    printf("Sum of Poly Mult = %d\n", sum);
    for (i = 0; i < n; i++) {
        polyA[i] = rand() % 100;
        printf("%d %d\n",i, polyA[i]);
    }

    //polyB = recursive_FFT(polyA, n);
    /*    for (i = 0; i < n; i++) {
          sum = sum + polyB[i];
          } 
          printf("%d\n", sum); */
    return 0;
}

/*int* i_FFT(int* a, int x, int n)
{
    int i = 0, k = 0;
    int size = n/2;
    double w, wn;
    int* y = (int *)malloc(sizeof(int));
    int* a0, *a1;
    int* y0;
    int* y1; 
    double q = round(log(n)/log(2));
    y0 = (int *)malloc(sizeof(int));
    y1 = (int *)malloc(sizeof(int));
    a0 = (int *)malloc(sizeof(int));
    a1 = (int *)malloc(sizeof(int));
    wn = pow(2.718, ((2*M_PI)/n));
    
    /*    int a0[size], a1[size], y[size], y0[size], y1[size];*/
   /* if (n == 1)
        return a;

    for (i = 0; i < n; i++) {
        if (i % 2) {
            a0[i] = a[i];
        }
        else {
            a1[i] = a[i];
        }
    }
    y0 = recursive_FFT(a0, size);
    y1 = recursive_FFT(a1, size);

    for (k = 0; k <= n/2; k++) {
        y[k] = y0[k] + (w * y1[k]);
        y[k + n/2] = y0[k] - (w * y1[k]);
        w = w * wn;    
    }
    return y;
}*/
int *i_fft(int* a, int x, int n)
{
    int s, k, j, t, m, u;
    double wm;
    int w = 1;
    int *bigA = (int *)malloc(sizeof(int));
    reverseBits(a, n, bigA);
    for (s = 1; s <= (log(n)/log(2)); s++) {
        m = pow(2, s);
        wm = exp((2 * M_PI * s) / m);
    }
    for (j = 0; j < n - 1; j++) {
        
        for (k = j; k < n - 1; k++) {
            t = w*bigA[k+m/2];
            u = bigA[k];
            bigA[k] = u + t;
            bigA[k+m/2] = u - t;
        }
    }
    w = w * wm;
    return bigA;
}

int *poly (int *ab, int x, int n)
{
    int j;
    int *p = (int *)malloc(sizeof(int));
    for (j = 0; j < n -1; j++)
        p[j] = ab[j] * pow(x, j);
    return p;
}

int *poly_c (int *a, int*b, int x, int n)
{
    int j;
    int *pC = (int *)malloc(sizeof(int));
    for (j = 0; j < n -1; j++)
        pC[j] = (a[j] + b[j]) * pow(x, j);
    return pC;
}

int reverseBits(int *a,int n, int *A)
{
    int k;
    for (k = 0; k < n; k++) {
        A[revBit(k)] = a[k];
    }
}
int revBit(int num)
{
    int  NO_OF_BITS = sizeof(num) * 8;
    int reverse_num = 0, i, temp;

    for (i = 0; i < NO_OF_BITS; i++)
    {
        temp = (num & (1 << i));
        if(temp)
            reverse_num |= (1 << ((NO_OF_BITS - 1) - i));
    }

    return reverse_num;
}
