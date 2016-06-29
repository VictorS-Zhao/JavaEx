import java.util.Scanner;

public class Ex6dot2 {
	public static int sumDigits(long n) {
		int sum = 0;
		long num = n;
		while (num > 0) {
			sum += num % 10;
			num = num / 10;
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.print("Enter an integer number: ");
		Scanner input = new Scanner(System.in);
		long num = input.nextLong();
		System.out.println("The sum of digits of "+num+" is: "+sumDigits(num));
	}
}

