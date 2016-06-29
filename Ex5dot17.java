import java.util.Scanner;

public class Ex5dot17 {
	public static void showPyramid2(int n) {
		for (int row = 1; row <= n; row++) {
			for (int i = 0; i < n -row; i++) {
				System.out.print("\t");
			}

			for (int j = 0; j < row-1; j++) {
//				System.out.print("\t"+(int)Math.pow(2.0, (double)j));
				System.out.printf("%8d", (int)Math.pow(2.0, (double)j));
			}

			for (int k = row; k > 0; k--) {
//				System.out.print("\t"+(int)Math.pow(2.0, (double)k-1));
				System.out.printf("%8d", (int)Math.pow(2.0, (double)k-1));
			}
		
			System.out.println();
		}
	}

	public static void showPyramid(int n) {
		for (int row = 1; row <= n; row++) {
			for (int i = 0; i < n - row; i++) {
			System.out.print("\t");
			}

			for (int j = row; j > 0; j--) {
				System.out.print("\t"+j);
			}
		
			for (int k = 2; k <= row; k++) {
				System.out.print("\t"+k);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of lines: ");
		int num = input.nextInt();
		showPyramid(num);

		showPyramid2(num);
	}
}

