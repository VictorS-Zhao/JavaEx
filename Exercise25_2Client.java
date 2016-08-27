import java.io.*;
import java.util.*;
import java.net.*;

public class Exercise25_2Client {
	private DataInputStream fromServer;
	private DataOutputStream toServer;

	private PrintWriter request;
	private Scanner response;

	public static void main(String[] args) {
		new Exercise25_2Client();
	}

	public Exercise25_2Client() {
		createClient2();
	}

	public void createClient2() {
		try {
			Socket socket = new Socket("localhost", 8000);
			response = new Scanner(new DataInputStream(socket.getInputStream()));

			request = new PrintWriter(new DataOutputStream(socket.getOutputStream()));

		while (true) {
			System.out.print("Enter radius: ");
			Scanner input = new Scanner(System.in);
			double radius = input.nextDouble();
			
			request.println(radius);
			request.flush();

			double area = response.nextDouble();
			System.out.println("Area received from the server is "+area);
		}


		} catch (IOException ex) {
			System.err.println(ex);
		}


	}

	public void createClient() {
		try {
			Socket socket = new Socket("localhost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());

			toServer = new DataOutputStream(socket.getOutputStream());

		while (true) {
			System.out.print("Enter radius: ");
			Scanner input = new Scanner(System.in);
			double radius = input.nextDouble();
			
			toServer.writeDouble(radius);
			toServer.flush();

			double area = fromServer.readDouble();
			System.out.println("Area received from the server is "+area);
		}


		} catch (IOException ex) {
			System.err.println(ex);
		}
	}


	
}

