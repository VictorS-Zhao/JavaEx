import java.util.Scanner;

public class Ex5dot25 {
	public static double computePi(int n) {
		double pi = 0.0;
		for (int i = n; i > 0; i--) {
			pi += 4 * Math.pow(-1, i+1) / (i*2 - 1);
		}
		return pi;
	}

	public static void main(String[] args) {
		System.out.print("Enter a number: ");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		System.out.println("PI based on "+num+" is: "+computePi(num));
	}
}
