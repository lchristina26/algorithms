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
int *multiply(int A[], int B[], int m)
{
   int *prod = (int *)malloc(2* m - 1);  
   int *temp = (int *)malloc(2* m - 1);  

   // Initialize the product polynomial
   int i; 	
   int j; 
   int k;
	
   if (m==1) {
	temp[0] = (A[0] * B[0]);
        return temp; 
   }
   
   int h = m/2;

   int AHigh[sizeof(h)];
   int BHigh[sizeof(h)];  	
   int ALow[sizeof(h - m % 2)];  	 
   int BLow[sizeof(h - m % 2)];  	 

   for (i = 0; i < h; i++) {
      	AHigh[i] = A[i+h];
      	BHigh[i] = B[i+h];
      	ALow[i] = A[i];
      	BLow[i] = B[i];
   }

   int addLowHighA[sizeof(h)];
   int addLowHighB[sizeof(h)];

   for(j = 0; j<(h); j++) {
       addLowHighA[j] = ALow[j]+AHigh[j];
       addLowHighB[j] = BLow[j]+BHigh[j];	     
   } 

  
       int *lowerPart = multiply(ALow, BLow, h);	 		
       int *middlePart = multiply(addLowHighA, addLowHighB, h);
       int *upperPart = multiply(AHigh, BHigh, h);

   for(k = 0; k < m-1; k++) {
      prod[k] += lowerPart[k];
      prod[k+h] += middlePart[k] - lowerPart[k] - upperPart[k];
      prod[k+2*h] += upperPart[k];
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
    int A[] = {5, 0, 10, 6, 2, 3, 8, 9};
    int B[] = {1, 2, 4, 0, 1, 0, 0, 10};

    int m = sizeof(A)/sizeof(A[0]);
    int n = sizeof(B)/sizeof(B[0]);

    //int *prodAB[] = {2*m - 1};	
 
    //printf("First polynomial is \n");
    //printPoly(A, m);
    //printf("\nSecond polynomial is \n");
    //printPoly(B, n);
 
    int *prod = multiply(A, B, m);
    //int prod =  multiply(A, B, m);
 
    end = clock();
    timeElapsed = (double)(end - begin) / CLOCKS_PER_SEC;

    printf("\nDivide and Conquer Product polynomial is \n");
    printPoly(prod, m+n-1);
    printf("\n");
    
    printf("\nThis multiplication took %f seconds \n", timeElapsed);

    return 0;
}


