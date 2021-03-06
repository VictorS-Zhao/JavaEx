import java.util.*;
import java.io.*;
import java.net.*;

public class Ch11dot3_Client {
    private Socket socket;
    private BufferedWriter request;
    private static final String REQ_CMD_INDEX = "INDEX";
    private String line;
    private Scanner input;
    private Scanner response; 

    public Ch11dot3_Client() {
        try {
            socket = new Socket("localhost", 32007);
            request = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            response = new Scanner(socket.getInputStream());
            input = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a command \n 'INDEX' \n 'GET <fileName>': ");

                String line = input.nextLine().trim();

                if (line.equalsIgnoreCase(REQ_CMD_INDEX)) {
                    request.write(REQ_CMD_INDEX);
                    request.write("\n");
                    request.flush();
                    //          request.close();

                    //            ObjectInputStream response = new ObjectInputStream(socket.getInputStream());
                    //         ArrayList<String> list = null;
                    //        Object obj = null;
                    //       try {
                    //        obj = response.readObject();
                    //       list = (ArrayList<String>)obj;

                    //      if (list != null) {
                    //       for (int i = 0; i < list.size(); i++) {
                    //        System.out.println(list.get(i));
                    //     }
                    //  }

                    //           response.close();

                    //          } catch (ClassNotFoundException e) {
                    //           e.printStackTrace();
                    //        }
                } else if (line.startsWith("GET") || line.startsWith("get")) {
                    request.write(line);
                    request.write("\n");
                    request.flush();

                } else {
                    System.out.println("invalid request command!");
                }

                boolean done = false;
                while (!done) {
                    //          System.out.println("printing response...");
                    String s = response.nextLine();
                    //           System.out.println("s: "+s);
                    if (s.equals("end-of-line")) {
                        done = true;
                    } else {
                        System.out.println(s);
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } finally {
//            try {
//                request.close();
//                input.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        new Ch11dot3_Client();
    }
}
