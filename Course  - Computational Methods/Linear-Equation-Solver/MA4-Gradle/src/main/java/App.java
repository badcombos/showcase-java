import java.util.Formatter;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		int a[][] = {{1,0,0},
					 {1,1,1},
					 {1,2,4}};
		int b[][] = {{0},
					 {1},
					 {4}};

		int[][] xtx = mult(transpose(a), a);
		int[][] xty = mult(transpose(a), b);

		// printMatrix(xtx);
		// printMatrix(xty);

		int d = det(xtx);
		ArrayList<Integer> coefficients = new ArrayList<>();

		System.out.println("");
		for (int i = 0; i < a.length; i++) {
			int[][] temp = crammer(xtx, xty, i);
			coefficients.add(det(temp)/d);
		}

		String output = String.format("(%d) + (%d)x + (%d)x^2", coefficients.get(0), coefficients.get(1), coefficients.get(2));
		System.out.println(output);

	}

	private static int[][] mult(int[][] a, int[][] b) {
		if (!canMultiply(a,b)){
			System.out.println("matricies cannot be multiplied");
			System.exit(1);
		}
		
		int rowsA = a.length;
		int rowsB = b.length;
		int colsB = b[0].length;

		int c[][] = new int[rowsA][colsB];
		for (int i = 0; i < rowsA; i++) {
			for (int j = 0; j < colsB; j++) {
				for (int k = 0; k < rowsB; k++)
					c[i][j] += a[i][k] * b[k][j];
			}
		}
		return c;
	}

	private static boolean canMultiply(int[][] a, int[][] b) {
		int colsA = a[0].length;
		int rowsB = b.length;
		return colsA == rowsB;
	}

	private static int[][] transpose(int[][] a) {
		int rows = a.length;
		int columns = a[0].length;
		int[][] transposed = new int[columns][rows];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				transposed[j][i] = a[i][j];
			}
		}

		return transposed;
	}

	// hard coding 3x3 matrix determinant solution equation, will replace with n by n determinant solver later
	private static int det(int[][] a) {
		int det = a[0][0] * (a[1][1]*a[2][2] - a[1][2]*a[2][1])
				- a[0][1] * (a[1][0]*a[2][2] - a[1][2]*a[2][0])
				+ a[0][2] * (a[1][0]*a[2][1] - a[1][1]*a[2][0]);
		return det;
	}

	private static int[][] crammer(int[][] m, int[][] y, int crammer_col) {
		int rows = m.length;
		int cols = m[0].length;
		int[][] out = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (j == crammer_col) {
					out[i][j] = y[i][0];
				}
				else {
					out[i][j] = m[i][j];
				}
			}	
		}
		return out;
	}

	private static void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}
	}

}
