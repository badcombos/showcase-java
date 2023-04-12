import java.util.Formatter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class App {

	private static final int MAX_LOOP = 30;
	private static final double KNOWN_VALUE = 0.54030231;

	public static Formatter formatOutput(){
		int x = 1;
		String padding = "%-7s %-5s %-15s %-15s %-15s\n";

		Formatter f = new Formatter(); //table formatting done using java's builtin formatter class
		f.format(padding, "h", "x", "Approx. f’(x)", "Known f’(x)", "Abs. Error");

		f.format(padding, "-------", "-----", "--------------", "--------------", "--------------");
		for (int i = 0; i < MAX_LOOP+1 ;i++ ) {
			double derv = computeDerv(x, i);
			double err = computeAbsError(derv, KNOWN_VALUE);
			f.format(padding, "2^-"+i, x, (""+derv), (""+KNOWN_VALUE), (""+err));
		}
		return f;
	}

	public static void main(String[] args) {

		System.out.println("Approximating the Derivative");
		System.out.println(formatOutput());

	}

	// computes derivative of sin
	public static double computeDerv(int x, int i){
		double delta = Math.pow(2, -i);

		return truncateTo8DecimalPlaces((Math.sin(x + delta) - Math.sin(x)) / delta);

	}

	// computes absolute error between two doubles
	public static double computeAbsError(double experimentalValue, double theoreticalValue) {
		return truncateTo8DecimalPlaces(Math.abs(experimentalValue - theoreticalValue));
	}

	//truncates+rounds to 8 digits because our known value is given to 8 digits
	//RoundingMode.HALF_EVEN aka "bankers rounding" used because it "statistically minimizes cumulative error when applied repeatedly over a sequence of calculations" -from https://docs.oracle.com/javase/7/docs/api/java/math/RoundingMode.html
	public static double truncateTo8DecimalPlaces(double value) {
		BigDecimal bigDecimal = new BigDecimal(value).setScale(8, RoundingMode.HALF_EVEN); 
		return bigDecimal.doubleValue();
	}


}
