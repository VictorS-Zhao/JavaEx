import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;

public class Ch12dot4_Server2 {

    private static class ConnectionHandler extends Thread {
        private Socket connection;
        private BufferedReader in;
        private PrintWriter out;  

        public ConnectionHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                out = new PrintWriter(connection.getOutputStream());

                while (true) {
                    String line = in.readLine().trim();
                    System.out.println("Current thread "+Thread.currentThread().getName()+" received request "+line);
                    if (line.equals("INDEX")) {
                        List<String> filesList = listFiles(".");
                        for (int i = 0; i < filesList.size(); i++) {
                            out.println(filesList.get(i));
                        }
                        out.println("end-of-line");
                        out.flush();
                    } else if (line.startsWith("GET")) {
                        String[] s = line.split(" ");
                        String fileName = s[1].trim();

                        File f = new File(fileName);
                        if (!f.exists()) {
                            //            out.writeChars("ERROR");
                            //            out.writeChars("\n");
                            //            out.writeChars("No such File "+fileName);
                            //            out.writeChars("\n");
                            //            out.flush();
                            out.println("ERROR");
                            out.println("No such file "+fileName);
                        } else {
                            //            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                            BufferedReader reader = new BufferedReader(new FileReader(fileName));
                            String l = null;
                            while ((l = reader.readLine()) != null) {
                                //              out.writeChars(l);
                                out.println(l);
                            }

                        }
                        out.println("end-of-line");
                        out.flush();
                    } else {
                        System.out.println("Invalid request!");
                        out.println("ERROR");
                        out.println("Invalid request");
                        out.flush();
                    }

                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 


        }
    }


		private static void  handleConnection(Socket connection) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                PrintWriter out = new PrintWriter(connection.getOutputStream());

                while (true) {
                    String line = in.readLine().trim();
                    System.out.println("Current thread "+Thread.currentThread().getName()+" received request "+line);
                    if (line.equals("INDEX")) {
                        List<String> filesList = listFiles(".");
                        for (int i = 0; i < filesList.size(); i++) {
                            out.println(filesList.get(i));
                        }
                        out.println("end-of-line");
                        out.flush();
                    } else if (line.startsWith("GET")) {
                        String[] s = line.split(" ");
                        String fileName = s[1].trim();

                        File f = new File(fileName);
                        if (!f.exists()) {
                            //            out.writeChars("ERROR");
                            //            out.writeChars("\n");
                            //            out.writeChars("No such File "+fileName);
                            //            out.writeChars("\n");
                            //            out.flush();
                            out.println("ERROR");
                            out.println("No such file "+fileName);
                        } else {
                            //            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                            BufferedReader reader = new BufferedReader(new FileReader(fileName));
                            String l = null;
                            while ((l = reader.readLine()) != null) {
                                //              out.writeChars(l);
                                out.println(l);
                            }

                        }
                        out.println("end-of-line");
                        out.flush();
                    } else {
                        System.out.println("Invalid request!");
                        out.println("ERROR");
                        out.println("Invalid request");
                        out.flush();
                    }

                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } 

		}

		private static class RequestHandler extends Thread {
				public RequestHandler() {
						setDaemon(true);
				}

				@Override
				public void run() {
						while (true) {
								try {
										Socket socket = connectionQueue.take();
										handleConnection(socket);
								} catch (InterruptedException ex) {
										ex.printStackTrace();
								}
						}
				}
		}

/**
     * The length of the ArrayBlockingQueue of connections.
     * This should not be too big, since connections in the
     * queue are waiting for service and hopefully won't 
     * spend too long in the queue.
     */
    private static final int CONNECTION_QUEUE_SIZE = 5;
    /**
     * The number of threads in the thread pool.
     */
    private static final int THREAD_POOL_SIZE = 10;

		private static ArrayBlockingQueue<Socket> connectionQueue;

    public Ch12dot4_Server2() {

				connectionQueue = new ArrayBlockingQueue<Socket>(CONNECTION_QUEUE_SIZE);

				for (int i = 0; i < THREAD_POOL_SIZE; i++) {
						RequestHandler worker = new RequestHandler();
						worker.start();
				}

        try {
            ServerSocket serverSocket = new ServerSocket(32007);
            while (true) {
                Socket connection = serverSocket.accept();
                InetAddress ia = connection.getInetAddress();
                System.out.println("Client "+ia.getHostName()+"/"+ia.getHostAddress()+":"+connection.getPort()+" connected at "+new Date());

    //            new ConnectionHandler(connection).start();
								connectionQueue.add(connection);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> listFiles(String fileName) {
        List<String> filesList = new ArrayList<String>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("No such file or directory!");
            return null;
        }
        if (file.isDirectory()) {
            String[] files = file.list();
            for (String s : files) {
                File f = new File(file, s);
                if (f.isDirectory()) {
                    listFiles(f.getPath());
                } else {
                    filesList.add(s);
                }
            }
        } else {
            filesList.add(fileName);
        }

        return filesList;
    }


    public static void main(String[] args) {
        new Ch12dot4_Server2();
    }
}
