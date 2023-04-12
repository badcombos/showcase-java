# Requirements
  * Java 11

# Sample Execution & Output
## Formatting
- FORMATTING WARNING: you must append "0" before decimal point
- Example: .55 must be: 0.55

## Usage
```shell
javac CS471_MA1.java && java CS471_MA1 [base] [inputs]
```
- [base] can be any interger number greater than 2
- [inputs] can be any arbitrary number of inputs, integer or decimal values

If run without command line arguments, using
```shell
javac CS471_MA2.java && java CS471_MA2
```

the following usage message will be displayed:
```shell
Please enter at least two commandline arguments
See readme.nd for details
```

### Example 1
Example run will command line arguments:
```shell
javac CS471_MA2.java && java CS471_MA2 2 15.25 66.75 33.3333333 40 0.555 0.69
```
will produce the following output:
```shell
Base 10         Base 2         
--------------  -------------- 
15.25           1111.01        
66.75           1000010.11     
33.3333333      100001.01010101
40              101000.0       
0.555           0.10001110     
0.69            0.10110000 
```

### Example 2
Example run will command line arguments:
```shell
javac CS471_MA2.java && java CS471_MA2 60 0.5 0.75 0.8 0.16666
```
will produce the following output:
```shell
Base 10         Base 60
--------------  --------------
0.5             0.30
0.75            0.45
0.8             0.48
0.16666         0.959583336000
```

### Example 3
This is the same as Example 2, but run with an `--show_symbols` optional flag. 
This flag uses standard letters to represent numbers above 9 for bases larger than base 10. 
(for example: using ABCDEF for 10-15 for base 16, aka hexadecimal)
* Please not that the maximum base that can be used with this flag is base 62.
* An error is displayed in console when this is exceeded.
```shell
javac CS471_MA2.java && java CS471_MA2 60 --show_symbols 0.5 0.75 0.8 0.16666
```
will produce the following output:
```shell
Base 10         Base 60
--------------  --------------
0.5             0.U
0.75            0.j
0.8             0.m
0.16666         0.9xwXa000
```

