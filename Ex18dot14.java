import java.util.Scanner;

public class Ex18dot14 {
	public static int count(String s) {
		int result = 0;

		if (s.length()>0) {
			result = count(s.substring(1))+(isUpperCase(s.charAt(0)) ? 1 : 0);
		}

		return result;
	}

	public static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String s = input.next();

		System.out.println("there is "+count(s)+" uppercase letter in "+s);
	}
}
