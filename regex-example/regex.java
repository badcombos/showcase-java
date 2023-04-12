import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

	private static String regex(String s, String pattern){
		Matcher m  = (Pattern.compile(pattern)).matcher(s);
		m.find();
		return m.group(0);
	}
	public static void main(String[] args) {
		int len = 2;

		String s = "1,2,3,4,5,6,7,8,9,10";

		String pattern = "";
		for (int i = 0; i < len; i++ ) {
			pattern += "\\b([0-9]|[1-9][0-9])\\b,";
		}
		pattern = pattern.substring(0, pattern.length()-1);
		String a = regex(s, pattern);
		System.out.println(a);
	}
}


	