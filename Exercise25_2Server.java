import java.io.*;
import java.util.*;
import java.net.*;

public class Exercise25_2Server {
	
	public static void main(String[] args) {
		new Exercise25_2Server();
	}

	public Exercise25_2Server() {

		createServer2();
	}

	public void createServer2() {
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("Server started at " + new Date());

			Socket socket = ss.accept();
			InetAddress inetAddress = socket.getInetAddress();
			System.out.println("Client "+inetAddress.getHostName()+"/"+inetAddress.getHostAddress()+" connected to ME!");

			Scanner input = new Scanner(new DataInputStream(socket.getInputStream()));
			PrintWriter output = new PrintWriter(new DataOutputStream(socket.getOutputStream()));

			while (true) {
				while (input.hasNext()) {
				double radius = input.nextDouble();
				System.out.println("Radius received from client: "+radius);

				double area = radius * radius * Math.PI;

				System.out.println("The area of Circle: "+area);
				output.println(area);
				output.flush();
				}
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void createServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);

			Socket socket = serverSocket.accept();

			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

			while (true) {
				double radius = inputFromClient.readDouble();
				System.out.println("Radius received from client: "+radius);

				double area = radius * radius * Math.PI;

				System.out.println("The area of Circle: "+area);
				outputToClient.writeDouble(area);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}

