import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.net.*;

public class Ex31dot9Client2 {

	private static Buffer buffer = new Buffer();

	public static void main(String[] args) {
		//		new Ex31dot9Client2();
		new ClientRequestThread();
		new ClientResponseThread();
	}

	public Ex31dot9Client2() {
		new ClientRequestThread();
		new ClientResponseThread();

	}

	static class Buffer {

		private static Lock lock = new ReentrantLock();
		private static Condition nowRequest = lock.newCondition();
		private static Condition nowResponse = lock.newCondition();

		Scanner input;
		public Buffer() {
			input = new Scanner(System.in);
		}

		public void request() {
			lock.lock();
			try {
				System.out.println("ME: ");
				String msg = input.nextLine(); 
				//while (!(msg = input.nextLine()).isEmpty()) {
				//} 
				System.out.println("Request input "+msg+" at "+new Date());
				nowRequest.await();

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
				System.out.println("now response at "+new Date());

				nowResponse.await();

				nowRequest.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

	}

	static class ClientRequestThread extends Thread {
		Scanner input;
		PrintWriter request;
		public ClientRequestThread() {
			input = new Scanner(System.in);

			start();
		}

		@Override
			public void run() {
					while (true) {
				buffer.request();
					}

			}
	}

	static class ClientResponseThread extends Thread {

		public ClientResponseThread() {
			start();
		}

		@Override
			public void run() {
					while (true) {
				buffer.response();
					}
			}
	}

}
