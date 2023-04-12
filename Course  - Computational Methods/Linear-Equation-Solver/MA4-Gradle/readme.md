# Getting Started

**Language:** {java}

# Methodology
From taking differential equations I learned about using Crammer's method of solving a system of linear equations, rather than the traditional method of row matrix operations and backsolving. This method should be easier to implement.

# My Pseudocode
```
def main() {
	//compute x^tx and x^ty
	matrix a = x^t x
	matrix b = x^t y

	//because only square matricies have determinents, we pad the matrix with 0's (if needed)
	a = square_matrix(a);
	b = square_matrix(b);

	//compute determinant of x^tx
	float d = det(a)

	//empty array to hold solved coefficients
	array coefficients = new array{num_cols} 

	for every column i in matrix a:
		temp = det(crammer(i))/d
		coefficients[i] = temp
}

def square_matrix(matrix a) {
	matrix m = new matrix
	int max = MAX(row, col)
	for every i in 0 to max:
		for every j in 0 to max:
			if (i < max) or (j < max)
				m[i][j] = a[i][j]
			else
				m[i][j] = 0
	return m
}

def crammer(matrix a, matrix b, int col) {
	matrix m = a
	for every row i in matrix m:
		m[i][col] = b[i]
	return m
}

```

# Requirements
  * Java 11
  * Gradle

# Compilation & Execution Instructions
## Compilation
```shell
gradle build
```
## Running Program
```shell
gradle run
```
The program takes no command line arguments because the input is fixed/hard coded. 
Output is: 
```shell

(0) + (0)x + (1)x^2
```