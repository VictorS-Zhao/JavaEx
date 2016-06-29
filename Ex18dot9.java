import java.util.Scanner;

public class Ex18dot9 {
	public static void reverseDisplay(String value) {
		if (value.length() == 0) {
			return;
		}

		System.out.print(value.charAt(value.length() - 1));
		reverseDisplay(value.substring(0, value.length() - 1));
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("enter a string: ");
		String s = input.nextLine();
		System.out.println("reverse of "+s+" is: ");
		reverseDisplay(s);
		System.out.println("");
	}
}

