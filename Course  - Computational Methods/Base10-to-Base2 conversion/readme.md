# Requirements
  * Java 11

# Sample Execution & Output

If run without command line arguments, using
```shell
javac CS471_MA1.java && java CS471_MA1
```

the following usage message will be displayed:
```shell
Please enter commandline arguments
Usage: javac CS471_MA1.java && java CS471_MA1 [arbitrary number of inputs]
FORMATTING WARNING: you must append \"0\" before decimal point
Example: .55 must be: 0.55"
```

Example run will command line arguments:
```shell
javac CS471_MA1.java && java CS471_MA1 15.25 66.75 33.3333333 40 0.555 0.69
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
