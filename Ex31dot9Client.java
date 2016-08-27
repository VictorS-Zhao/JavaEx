import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.net.*;

public class Ex31dot9Client {
	private Socket socket;
	private PrintWriter request;
	private Scanner response; 
	private Scanner input;

	private static Lock lock = new ReentrantLock();
	private static Condition nowRequest = lock.newCondition();
	private static Condition nowResponse = lock.newCondition();
 
	private static int flag = 0;

	public static void main(String[] args) {
		new Ex31dot9Client();
	}

	public Ex31dot9Client() {
		try {
			socket = new Socket("localhost", 9999);
			request = new PrintWriter(new DataOutputStream(socket.getOutputStream()));
			response = new Scanner(new DataInputStream(socket.getInputStream()));

			input = new Scanner(System.in);
			PrintWriter output = new PrintWriter(System.out);
			new ClientRequestThread(input, request);
			new ClientResponseThread(response, output);

		} catch (UnknownHostException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void request() {
		lock.lock();
		try {
			while (flag % 2 != 0)
				nowRequest.await();
			System.out.println("ME: ");
			String msg = input.nextLine(); 
			//while (!(msg = input.nextLine()).isEmpty()) {
			request.println(msg);
			request.flush();
			//} 

			flag++;
			nowResponse.signal();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void response() {
		lock.lock();
		try {
			while (flag % 2 == 0)
				nowResponse.await();
			String newMsg = response.nextLine(); 
//			String newMsg;
//			while (!(newMsg = response.nextLine()).isEmpty()) {
				System.out.println(newMsg);
//			}

			flag++;
			nowRequest.signal();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	class ClientRequestThread extends Thread {
		Scanner input;
		PrintWriter request;
		public ClientRequestThread(Scanner input, PrintWriter request) {
			this.input = input;
			this.request = request;
			start();
		}

		@Override
		public void run() {
			while (true) {
				lock.lock();
//				try {
//					while (flag % 2 != 0)
//						nowRequest.await();
//					System.out.println("ME: ");
//					String msg = input.nextLine(); 
//					//while (!(msg = input.nextLine()).isEmpty()) {
//					request.println(msg);
//					request.flush();
//					//} 
//
//					flag++;
//					nowResponse.signal();
//
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//				} finally {
//					lock.unlock();
//				}
//
//
				request();
			}
		}
	}

	class ClientResponseThread extends Thread {
		private Scanner response;
		private PrintWriter output;

		public ClientResponseThread(Scanner response, PrintWriter output) {
			this.response = response;
			this.output = output;
			start();
		}

		@Override
		public void run() {
			while (true) {
//		lock.lock();
//		try {
//			while (flag % 2 == 0) 	
//				nowResponse.await();
//			String newMsg = response.nextLine(); 
//			while (!(newMsg = response.nextLine()).isEmpty()) {
//				System.out.println(newMsg);
//			}
//
//			flag++; 
//			nowRequest.signal();
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		} finally {
//			lock.unlock();
//		}
//
			response();
			}
		}
	}

}
