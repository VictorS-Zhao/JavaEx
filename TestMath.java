import java.util.Scanner;

public class TestMath {
	public static void main(String[] args) {
//		float n = 2.5f;
//		float n2 = -2.5f;

//		System.out.println("ceil(n): "+Math.ceil(n));
//		System.out.println("ceil(n2): "+Math.ceil(n2));
//		System.out.println("floor(n): "+Math.floor(n));

		Scanner input = new Scanner(System.in);
		System.out.print("Enter a double value: ");
		double n = input.nextDouble();

		System.out.println("ceil("+n+"): "+Math.ceil(n));
		System.out.println("floor("+n+"): "+Math.floor(n));
		System.out.println("rint("+n+"): "+Math.rint(n));
		System.out.println("round("+n+"): "+Math.round(n));

		System.out.print("Input a number: ");
		int n1 = input.nextInt();
		System.out.print("Input another number: ");
		int n2 = input.nextInt();
		System.out.println("ceil(1.0*"+n1+"/"+n2+"): "+Math.ceil(1.0*n1/n2));
		System.out.println("round("+n1+"/"+n2+"): "+Math.round(n1/n2));
	}
}
