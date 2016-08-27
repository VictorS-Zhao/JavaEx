import java.io.*;
import java.util.*;
import java.net.*;

public class Ex31dot1Client {
	public static void main(String[] args) {
		new Ex31dot1Client();
	}

	public Ex31dot1Client() {
		try {
		
		Socket socket = new Socket("localhost", 8888);
		DataInputStream response = new DataInputStream(socket.getInputStream());
		DataOutputStream request = new DataOutputStream(socket.getOutputStream());

		while (true) {
			Scanner input = new Scanner(System.in);

			System.out.print("Enter annual interest rate: ");
			Double rate = input.nextDouble();

			System.out.print("Enter number of years: ");
			int numOfYears = input.nextInt();

			System.out.print("Enter loan amount: ");
			int loanAmount = input.nextInt();

			request.writeDouble(rate);
			request.writeInt(numOfYears);
			request.writeInt(loanAmount);
			request.flush();

			double monthlyPayment = response.readDouble();
			double totalPayment = response.readDouble();
			System.out.println("Total payment: "+totalPayment);
			System.out.println("Monthly payment: "+monthlyPayment);
		}
		} catch (IOException ex) {
			System.err.println(ex);
		}	
	}
}
