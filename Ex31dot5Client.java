import java.io.*;
import java.util.*;
import java.net.*;

public class Ex31dot5Client {
	private ObjectInputStream response;
	private ObjectOutputStream request;
 
	public static void main(String[] args) {
		new Ex31dot5Client();
	}

	public Ex31dot5Client() {
		try {
			Socket socket = new Socket("localhost", 8888);
			request = new ObjectOutputStream(socket.getOutputStream());
			response = new ObjectInputStream(socket.getInputStream());

			while (true) {
				Scanner input = new Scanner(System.in);

				System.out.print("Enter annual interest rate: ");
				Double rate = input.nextDouble();

				System.out.print("Enter number of years: ");
				int numOfYears = input.nextInt();

				System.out.print("Enter loan amount: ");
				int loanAmount = input.nextInt();

				request.writeObject(new Loan(rate, numOfYears, loanAmount));
				request.flush();
//			request.close();	

				Loan loan = (Loan)response.readObject();
				double monthlyPayment = loan.getMonthlyPayment();
				double totalPayment = loan.getTotalPayment();
				System.out.println("Total payment: "+totalPayment);
				System.out.println("Monthly payment: "+monthlyPayment);
//			response.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println(ex);
		} finally {
//			try {
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
		}
	}
}
