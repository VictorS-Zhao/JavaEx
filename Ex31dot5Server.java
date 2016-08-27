import java.io.*;
import java.net.*;
import java.util.*;

public class Ex31dot5Server {
	public static void main(String[] args) {
		new Ex31dot5Server();
	}
	
	public Ex31dot5Server() {
		try {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("Sever started at "+new Date());

			while (true) {
				Socket socket = ss.accept();
				InetAddress ia = socket.getInetAddress();
				System.out.println("Client "+ia.getHostName()+"/"+ia.getHostAddress()+" connected at "+new Date());

				HandleAClient task = new HandleAClient(socket);
				new Thread(task).start();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	class HandleAClient implements Runnable {
		private Socket socket;
		private ObjectInputStream input;
		private ObjectOutputStream output;

		public HandleAClient(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				input = new ObjectInputStream(socket.getInputStream());
				output = new ObjectOutputStream(socket.getOutputStream());

				while(true) {
					Object object = input.readObject();
					Loan loan = (Loan)object;
					
					double annualInterestRate = loan.getRate();
					int numOfYears = loan.getNumOfYears();
					int loanAmount = loan.getLoanAmount();

//					computePayment(annualInterestRate, numOfYears, loanAmount);
					double totalPayment = loanAmount*annualInterestRate/100*numOfYears + loanAmount;
					double monthlyPayment = totalPayment/numOfYears/12;

					loan.setTotalPayment(totalPayment);
					loan.setMonthlyPayment(monthlyPayment);
					output.writeObject(loan);
					output.flush();
				}
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				System.err.println(ex);
			} finally {
				try {
				input.close();
				output.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}


