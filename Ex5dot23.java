import java.util.Scanner;

public class Ex5dot23 {
	public static void sum(int n) {
		double sum = 0.0;
		for (int i = 1; i <= n; i++) {
			sum += 1.0/i;
		}
		System.out.println("sum : "+sum);

		double sum2 = 0.0;
		for (int i = n; i > 0; i--) {
			sum2 += 1.0/i;
		}
		System.out.println("sum2: "+sum2);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int num = input.nextInt();
		sum(num);
	}
}
