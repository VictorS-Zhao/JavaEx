import java.util.Scanner;

public class Ex18dot10 {
	public static int count(String s, char a) {
		int result = 0;
		if (s.length()>0) {
			result = count(s.substring(1), a) + ((s.charAt(0)==a) ? 1 : 0);
		}
		
		return result;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String s = input.nextLine();
		System.out.print("Enter a char: ");
		char c = input.nextLine().charAt(0);

		System.out.println(c+" appears "+count(s,c)+" time(s) in "+s);
	}
}

