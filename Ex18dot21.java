import java.util.Scanner;

public class Ex18dot21 {

public static String dec2Bin(int value) {
	if (value == 0) {
		return "";
	} else {
		return dec2Bin(value/2)+dec2BinChar(value%2);
	}
}

public static char dec2BinChar(int value) {
	if (value == 0) {
		return '0';
	}

	return '1';
}

public static void main(String[] args) {
	System.out.println("1/2: "+1/2);
	Scanner input = new Scanner(System.in);
	System.out.print("Enter a decimical number: ");
	int num = input.nextInt();
	System.out.println("the binary of decimal number "+num+" is: "+dec2Bin(num));
}
}
