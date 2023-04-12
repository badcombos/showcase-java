import java.util.Formatter;
import java.util.ArrayList;

public class CS471_MA1 {
	private static final int MAX_DIGITS = 8;

	public static void main(String[] args) {
		if (args.length == 0){ // no command line arguments usage warning
			System.out.println("Please enter commandline arguments");
			System.out.println("Usage: javac CS471_MA1.java && java CS471_MA1 [arbitrary number of inputs]");
			System.out.println("FORMATTING WARNING: you must append \"0\" before decimal point");
			System.out.println("Example: .55 must be: 0.55");
			System.exit(1);
		}

		Formatter f = new Formatter(); //table formatting done using java's builtin formatter class
		f.format("%-15s %-15s\n", "Base 10", "Base 2");  

		ArrayList<String> base2 = new ArrayList<String>();
		for (int i = 0; i < args.length ;i++ ) {
			base2.add(decToBin(args[i]));
		}

		f.format("%-15s %-15s\n", "--------------", "--------------");
		for (int i = 0; i < base2.size() ;i++ ) {
			f.format("%-15s %-15s\n", args[i], base2.get(i));
		}
		System.out.println(f);
	}

	public static String decToBin(String input) {
		String[] arr = input.split("\\."); // backslashes needed because "." is a regular expression
		String temp = "";

		String tempWhole = decToBinIntegral(arr[0]);
		String tempfrac = (arr.length > 1) ? decToBinFrac(arr[1]) : "0"; //because number does not have to be fractional

		return tempWhole + "." + tempfrac;
	}

	public static String decToBinIntegral(String input) {
		if (input.equals("0"))
			return "0";

		int num = Integer.parseInt(input);
		String temp = "";

		while (num != 0) {
			int remainder = num % 2;

			temp = ((remainder == 1) ? "1" : "0") + temp;
			num = num / 2; 
		}
		return temp;
	}

	public static String decToBinFrac(String input) {
		String temp = "";
		int precision = MAX_DIGITS;
		double fractional = Double.parseDouble("."+input);

		while (fractional!= 0.0 && precision != 0) {
			fractional = fractional * 2;

			int bit = Character.getNumericValue((""+fractional).charAt(0));

			fractional = fractional - bit;

			temp = temp + ((bit == 1) ? "1" : "0");

			precision--;
		}
		return temp;

	}
	
}
