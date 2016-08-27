import java.io.*;
import java.net.*;
import java.util.*;

public class Ex31dot1Server {
	public static void main(String[] args) {
		new Ex31dot1Server();
	}
	
	public Ex31dot1Server() {
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

		public HandleAClient(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());

				while(true) {
					double annualInterestRate = input.readDouble();
					int numOfYears = input.readInt();
					int loanAmount = input.readInt();

//					computePayment(annualInterestRate, numOfYears, loanAmount);
					double totalPayment = loanAmount*annualInterestRate/100*numOfYears + loanAmount;
					double monthlyPayment = totalPayment/numOfYears/12;
					output.writeDouble(monthlyPayment);
					output.writeDouble(totalPayment);
					output.flush();
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

}


