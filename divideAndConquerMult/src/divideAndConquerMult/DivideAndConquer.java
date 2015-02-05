package divideAndConquerMult;

import java.util.Random;

public class DivideAndConquer {
	 
	static int[] multiply(int A[], int B[], int m)
	{
	   int prod[] = new int[(2* m) - 1];  
 	
	   if (m==1) {
		   int temp[] = {A[0] * B[0]};
	      return temp; 
	   }
	   
	   int h = m/2;

	   int AHigh[] = new int[h];
	   int BHigh[] = new int [h];  	
	   int ALow[] = new int [(h - m % 2)];  	 
	   int BLow[] = new int [(h - m % 2)];  	 

	   for (int i = 0; i < h; i++) {
	      	AHigh[i] = A[i+h];
	      	BHigh[i] = B[i+h];
	      	ALow[i] = A[i];
	      	BLow[i] = B[i];
	   }

	   int addLowHighA[] = new int [h];
	   int addLowHighB[] = new int [h];

	   for(int i = 0; i<h; i++) {
	       addLowHighA[i] = ALow[i]+AHigh[i];
	       addLowHighB[i] = BLow[i]+BHigh[i];	     
	   } 

	       int[] lowerPart = multiply(ALow, BLow, h);	 		
	       int[] middlePart = multiply(addLowHighA, addLowHighB, h);
	       int[] upperPart = multiply(AHigh, BHigh, h); 
	       
	       
	   for(int i = 0; i < m-1; i++) {
	      prod[i] += lowerPart[i];
	      prod[i+h] += middlePart[i] - lowerPart[i] - upperPart[i];
	      prod[i+2*h] += upperPart[i];
	   }	
	    
	    return prod;	
	}
	 
	// A utility function to print a polynomial
	static void printPoly(int poly[], int n)
	{
	    int i;	
	    for (i=0; i<n; i++)
	    {
	       System.out.format("%d", poly[i]);
	       if (i != 0)
	        System.out.format("x^%d", i);
	       if (i != n-1)
	       System.out.print(" + ");
	    }
	}
	 
    public static void main(String[] args) 
	{
		   long timeElapsed;  
			 
		    long begin  = System.nanoTime(); 	
		    
		    // Array holding coefficients corresponding to degrees in ascending order (i.e. {5, 0, 10, 6} = 5 + 10x^2 + 6x^3
		    int size = 65536;
		    
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
		 
		    //int prod[] = multiply(A, B, m);
		 
		    multiply(A, B, m);
		    
		    long end = System.nanoTime(); 
		    timeElapsed = (end - begin);

		    //System.out.format("\nDivide and Conquer Mult Product (for size %d polynomial is \n", size);
		    //printPoly(prod, m+n-1);
		    //System.out.print("\n");
		    
		    System.out.format("\nThis multiplication took " + timeElapsed * 1.0e-6 + " milliseconds \n");

		}
}
