import java.util.Scanner;

public class Ex6dot6 {
	public static void print(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n - i; j++) {
			System.out.print("\t");
			}

			for (int k = i; k > 0; k--) {
			System.out.print("\t"+k);
			}

			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.print("Enter an integer: ");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		print(num);
	}
}

