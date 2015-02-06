package divideAndConquerMult;

import java.util.Random;

//program to multiply two polynomials using the naive pointwise O(n^2 method)
public class PolyMult {

	// A[] & B[] hold coefficients of the polynomials (from smallest (degree 0)
	// to largest (degree m-1 or n-1)
	// m and n are the sizes of A[] and B[]
	static int[] multiply(int A[], int B[], int m, int n) {
		// allots enough space for the array of integer coefficients
		int productArray[] = new int[2 * m - 1];

		int i;
		int j;

		// for each new degree, sets prod[] to 0
		for (i = 0; i < m + n - 1; i++)
			productArray[i] = 0;

		for (i = 0; i < m; i++) {
			// Multiply coefficient of first and second polynomials
			for (j = 0; j < n; j++)
				productArray[i + j] += A[i] * B[j];
		}
		// returns the product array, which contains the integer coefficients for each degree x
		return productArray;
	}

	// Print the polynomial (for checking output, can be commented out with
	// larger sizes to avoid hang ups
	static void printPolynomial(int poly[], int n) {
		for (int i = 0; i < n; i++) {
			System.out.format("%d", poly[i]);
			if (i != 0) {
				System.out.format("x^%d", i);
			}
			if (i != n - 1) {
				System.out.format(" + ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// variable to keep track of actual run time
		long timeElapsed;

		int size = 1048576;

		// Array holding coefficients corresponding to degrees in ascending
		// order (i.e. {5, 0, 10, 6} = 5 + 10x^2 + 6x^3
		int A[] = new int[size];
		int B[] = new int[size];
		
		int m = A.length;
		int n = B.length;

		// seed for random number generator (to insure array values are good for
		// comparison)
		Random rand1 = new Random(5);
		Random rand2 = new Random(6);
		
		
		// fills the arrays with randomly generated numbers
		for (int i = 0; i < size; i++) {
			A[i] = rand1.nextInt(11);
			B[i] = rand2.nextInt(11);
		}
		//starts the timer immediately before calling the multiply function
		long begin = System.nanoTime();
		
		//multiplies the polynomials
		int prod[] = multiply(A, B, m, n);

		//ends the timer, gets the elapsed time
		long end = System.nanoTime();
		timeElapsed = (end - begin);

		printPolynomial(A, m);
		printPolynomial(B, n);
		
		//prints the formatted results
		System.out.format("\nPolynomial Multiplication Product (for size %d polynomial is \n",size);
		printPolynomial(prod, m + n - 1);
		System.out.print("\n");

		System.out.format("\nThis multiplication took " + timeElapsed * 1.0e-6
				+ " milliseconds \n");

	}

}
