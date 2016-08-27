import java.io.*;
import java.util.*;
import java.net.*;

public class Ex31dot9Server {
	public static void main(String[] args) {
		new Ex31dot9Server();
	}

	private Hashtable<Socket, PrintWriter> outputStreams = new Hashtable<Socket, PrintWriter>();

	public Ex31dot9Server() {
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("MultiThreadServer started at "+new Date());

			while (true) {
				Socket socket = ss.accept();
				InetAddress ia = socket.getInetAddress();
				System.out.println("Client "+ia.getHostName()+"/"+ia.getHostAddress()+" connected at "+new Date());
				PrintWriter output = new PrintWriter(new DataOutputStream(socket.getOutputStream()));

				outputStreams.put(socket, output);

				new ServerThread(this, socket);		
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}

	}

	public void sendToAll(String msg) {
		for (Enumeration e = outputStreams.elements(); e.hasMoreElements();) {
			PrintWriter output = (PrintWriter)e.nextElement();
			System.out.println("Send msg to "+output);
			output.println(msg);
			output.flush();
		}
	}

	class ServerThread extends Thread {
		private Ex31dot9Server server;
		private Socket socket;

		public ServerThread(Ex31dot9Server server, Socket socket) {
			this.server = server;
			this.socket = socket;
			start();
		}
			
		@Override
		public void run() {
			try {
				Scanner input = new Scanner(new DataInputStream(socket.getInputStream()));
				while (true) {
					String msg = input.nextLine();
					System.out.println("receive msg: "+msg);
					server.sendToAll(msg);
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
