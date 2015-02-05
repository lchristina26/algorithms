package divideAndConquerMult;

import java.util.Random;

public class PolyMult {

 
	// A[] & B[] hold coefficients of first polynomial (from smallest (degree 0) to largest (degree m or n-1)
	// m and n are sizes of A[] and B[]
	static int[] multiply(int A[], int B[], int m, int n)
	{
	   int prod[] = new int[2* m - 1];  

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
	static void printPoly(int poly[], int n)
	{	
	    for (int i=0; i<n; i++)
	    {
	       System.out.format("%d", poly[i]);
	       if (i != 0)
	        System.out.format("x^%d", i);
	       if (i != n-1)
	       System.out.format(" + ");
	    }
	}
	 
    public static void main(String[] args) 
	{

	    long timeElapsed;  
	 
	    long begin  = System.nanoTime(); 	
	    
	    // Array holding coefficients corresponding to degrees in ascending order (i.e. {5, 0, 10, 6} = 5 + 10x^2 + 6x^3
	    int size = 1048576;
	    
	    int A[] = new int[size];
	    int B[] = new int [size];


	    int m = A.length;
	    int n = B.length;
	    
        Random rand = new Random(5);
	    
	    for (int i=0; i < size; i++) {
	        A[i] = rand.nextInt(11);
	    }

	    for (int i=0; i < size; i++) {
	        B[i] = rand.nextInt(11);
	    }
	 
	    int prod[] = multiply(A, B, m, n);
	 
	    long end = System.nanoTime(); 
	    timeElapsed = (end - begin);

	   // System.out.format("\nPoly Mult Product (for size %d polynomial is \n", size);
	    //printPoly(prod, m+n-1);
	    //System.out.print("\n");
	    
	    System.out.format("\nThis multiplication took " + timeElapsed * 1.0e-6 + " milliseconds \n");

	}

}
