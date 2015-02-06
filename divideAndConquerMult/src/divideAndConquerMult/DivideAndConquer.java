package divideAndConquerMult;

import java.util.Random;

//program to multiply two polynomials using the O(n^log_2 3 method), which cuts the number of multiplications
//down from 4 to 3
public class DivideAndConquer {

	// A[] & B[] hold coefficients of the polynomials (from smallest (degree 0)
	// to largest (degree m-1) - for this we figure
	// m and n are the sizes of A[] and B[]
	static int[] multiply(int A[], int B[], int m) {
		// allots enough space for the array of integer coefficients
		int productArray[] = new int[(2 * m) - 1];

		// the base case for the recursion, where the size of the array is 1
		if (m == 1) {
			int temp[] = { A[0] * B[0] };
			return temp;
		}

		// the halfway point of the array, where to divide for high/low arrays
		int h = m / 2;

		// splits the array into 4 smaller sub arrays
		int AHigh[] = new int[h];
		int BHigh[] = new int[h];
		int ALow[] = new int[(h - m % 2)];
		int BLow[] = new int[(h - m % 2)];

		// iterates through the arrays, filling them appropriately
		for (int i = 0; i < h; i++) {
			AHigh[i] = A[i + h];
			BHigh[i] = B[i + h];
			ALow[i] = A[i];
			BLow[i] = B[i];
		}

		int addLowHighA[] = new int[h];
		int addLowHighB[] = new int[h];

		for (int i = 0; i < h; i++) {
			addLowHighA[i] = ALow[i] + AHigh[i];
			addLowHighB[i] = BLow[i] + BHigh[i];
		}

		// recursively performs 3 multiplications
		int[] lowerPart = multiply(ALow, BLow, h);
		int[] middlePart = multiply(addLowHighA, addLowHighB, h);
		int[] upperPart = multiply(AHigh, BHigh, h);

		// gets the proper integer coefficients
		for (int i = 0; i < m - 1; i++) {
			productArray[i] += lowerPart[i];
			productArray[i + h] += middlePart[i] - lowerPart[i] - upperPart[i];
			productArray[i + 2 * h] += upperPart[i];
		}

		// returns the product array, which contains the integer coefficients
		// for each degree x
		return productArray;
	}

	// Print the polynomial (for checking output, can be commented out with
	// larger sizes to avoid hang ups
	static void printPolynomial(int poly[], int n) {
		for (int i = 0; i < n; i++) {
			System.out.format("%d", poly[i]);
			if (i != 0)
				System.out.format("x^%d", i);
			if (i != n - 1)
				System.out.print(" + ");
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
		int prod[] = multiply(A, B, m);
		
		//ends the timer, gets the elapsed time
		long end = System.nanoTime();
		timeElapsed = (end - begin);

		//prints the formatted results
		// System.out.format("\nDivide and Conquer Mult Product (for size %d polynomial is \n",
		// size);
		//printPolynomial(prod, m + n - 1);
		// System.out.print("\n");

		System.out.format("\nThis multiplication took " + timeElapsed * 1.0e-6
				+ " milliseconds \n");

	}
}
