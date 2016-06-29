import java.util.Scanner;

public class Ex6dot4 {
	public static int reverse(int num) {
		String s = "";
		int n = num;
		while (n > 0) {
			s += n % 10;
			n /= 10;
		}

		return Integer.valueOf(s);
	}

	public static void main(String[] args) {
		System.out.print("Enter an integer number: ");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		System.out.println("The reverse of "+num+" is: "+reverse(num));
	}
}

