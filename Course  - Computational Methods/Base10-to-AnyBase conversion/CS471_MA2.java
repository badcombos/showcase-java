import java.util.Formatter;
import java.util.ArrayList;

public class CS471_MA2 {
	private static final int MAX_DIGITS = 8;

	private static final String CONSOLE_TEXT_1 = "Please enter at least two commandline arguments\nSee readme.nd for details";
	private static final String CONSOLE_TEXT_2 = "Base number exceeded for this mode\nSee readme.nd for details";
	private static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // length = max base 

	private static boolean LetterOuputToggle = false;

	public static Formatter formatOutput(int base, ArrayList<String> arr_beg, ArrayList<String> arr_fin){
		Formatter f = new Formatter(); //table formatting done using java's builtin formatter class
		f.format("%-15s %-15s\n", "Base 10", "Base " + base);  

		f.format("%-15s %-15s\n", "--------------", "--------------");
		for (int i = 0; i < arr_beg.size() ;i++ ) {
			f.format("%-15s %-15s\n", arr_beg.get(i), arr_fin.get(i));
		}
		return f;
	}

	public static void main(String[] args) {
		if (args.length < 2){ // command line arguments usage warning
			System.out.println(CONSOLE_TEXT_1);
			System.exit(1);
		}

		LetterOuputToggle = args[1].equals("--show_symbols");

		int base = Integer.parseInt(args[0]);
		if (LetterOuputToggle && (base > DIGITS.length())) {
			System.out.println(CONSOLE_TEXT_2);
			System.exit(1);
		}

		ArrayList<String> arr_beg = new ArrayList<String>();
		ArrayList<String> arr_fin = new ArrayList<String>();


		for (int i = ((LetterOuputToggle)?2:1); i < args.length ;i++ ) {
			arr_beg.add("" + args[i]);
			arr_fin.add(decToBase(base, args[i]));
		}

		System.out.println(formatOutput(base, arr_beg, arr_fin));
	}

	public static String decToBase(int base, String input) {
		String[] arr = input.split("\\."); // backslashes needed because "." is a regular expression
		String temp = "";

		String tempWhole = decToBaseIntegral(base, arr[0]);
		String tempfrac = (arr.length > 1) ? decToBaseFrac(base, arr[1]) : "0"; //because number does not have to be fractional

		return tempWhole + "." + tempfrac;
	}

	public static String decToBaseIntegral(int base, String input) {
		if (input.equals("0"))
			return "0";

		int num = Integer.parseInt(input);
		String temp = "";

		while (num != 0) {
			int remainder = num % base;

			temp = DIGITS.charAt(remainder) + temp;
			num = num / base; 
		}
		return temp;
	}

	public static String decToBaseFrac(int base, String input) {
		String temp = "";
		int precision = MAX_DIGITS;
		double fractional = Double.parseDouble("."+input);

		while (fractional!= 0.0 && precision != 0) {
			fractional = fractional * base;

			int i = (int) fractional;

			temp = temp + ((LetterOuputToggle) ? (""+DIGITS.charAt(i)) : (""+i));

			fractional = fractional - i;

			precision--;
		}
		return temp;
	}
}