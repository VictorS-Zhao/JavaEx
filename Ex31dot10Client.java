import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.net.*;

public class Ex31dot10Client {
	private Socket socket;

	public static void main(String[] args) {
		new Ex31dot10Client();
	}

	public Ex31dot10Client() {
		try {
			socket = new Socket("localhost", 9999);

			new ClientRequestThread(socket);
			new ClientResponseThread(socket);

		} catch (UnknownHostException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	class ClientRequestThread extends Thread {
		Scanner input;
		PrintWriter request;
		Socket socket;

		public ClientRequestThread(Socket socket) {
			this.socket = socket;
			try {
			request = new PrintWriter(new DataOutputStream(socket.getOutputStream()));
			} catch (IOException ex) {
			}

			input = new Scanner(System.in);
			start();
		}

		@Override
		public void run() {
			while (true) {
				request.println(input.nextLine());
				request.flush();
			}
		}
	}

	class ClientResponseThread extends Thread {
		private Socket socket;
		private Scanner response;

		public ClientResponseThread(Socket socket) {
			this.socket = socket;
			try {
			response = new Scanner(new DataInputStream(socket.getInputStream()));
			} catch (IOException ex) {
			}

			start();
		}

		@Override
		public void run() {
			while (true) {
				System.out.println(response.nextLine());
			}
		}
	}

}
