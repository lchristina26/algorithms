/* Simple Polynomial Multiplication Program, works in O(n^2) time
   Spring 2015 Algorithms as described at 
   to compile enter: 'gcc -o polyMult polyMult.c -lm'   
   by Monica Thornton and Leah Thompson */

// Simple C program to multiply two polynomials
#include <stdlib.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

 
// A[] & B[] hold coefficients of first polynomial (from smallest (degree 0) to largest (degree m or n-1)
// m and n are sizes of A[] and B[]
int *multiply(int A[], int B[], int m, int n)
{
   int *prod = (int *)malloc(m+n-1);  

   // Initialize the product polynomial
   int i; 	
   int j; 
	
   for (i = 0; i<m+n-1; i++)
     prod[i] = 0;
 
   // Multiply two polynomials term by term
 
   // Take ever term of first polynomial
   for (i=0; i<m; i++)
   {
     // Multiply the current term of first polynomial
     // with every term of second polynomial.
	     
	for (j=0; j<n; j++)
         prod[i+j] += A[i]*B[j];
   }
 
   return prod;
}
 
// A utility function to print a polynomial
void printPoly(int poly[], int n)
{
    int i;	
    for (i=0; i<n; i++)
    {
       printf("%d", poly[i]);
       if (i != 0)
        printf("x^%i", i);
       if (i != n-1)
       printf(" + ");
    }
}
 
int main()
{
    clock_t begin, end;
    double timeElapsed;  
 
    begin = clock(); 	
    // Array holding coefficients corresponding to degrees in ascending order (i.e. {5, 0, 10, 6} = 5 + 10x^2 + 6x^3
    int size = 8192;
    
    int A[size];
    int B[size];


    int m = sizeof(A)/sizeof(A[0]);
    int n = sizeof(B)/sizeof(B[0]);
    
    int i = 0;
    int j = 0;

    for (i=0; i < size; i++) {
        A[i] = rand() % 11;
    }

    for (j=0; j < size; j++) {
        B[j] = rand() % 11;
    }
 
    int *prod = multiply(A, B, m, n);
 
    end = clock();
    timeElapsed = (double)(end - begin) / CLOCKS_PER_SEC;

    printf("\nPoly Mult Product (for size %d polynomial is \n", size);
    printPoly(prod, m+n-1);
    printf("\n");
    
    printf("\nThis multiplication took %f seconds \n", timeElapsed);

    return 0;
}


