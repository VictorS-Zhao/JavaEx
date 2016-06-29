import java.util.Scanner;

public class Ex18dot8 {
	
	public static void reverseDisplay(int value) {
		if (0 != value) {
			System.out.print(value%10);
			value = value/10;
			reverseDisplay(value);
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a integer: ");
		int value = input.nextInt();
		reverseDisplay(value);
	}
}

