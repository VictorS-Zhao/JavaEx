import java.util.*;
import java.io.*;
import java.net.*;

public class FileClient {
    private Socket socket;
    private BufferedWriter request;
    private static final String REQ_CMD_INDEX = "INDEX";
    private String line;
    private Scanner input;
    private Scanner response; 

    public FileClient() {
        try {
            socket = new Socket("localhost", 3210);
            request = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            response = new Scanner(socket.getInputStream());
            input = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a command \n 'index' \n 'get <fileName>': ");

                String line = input.nextLine().trim();

                if (line.equalsIgnoreCase(REQ_CMD_INDEX)) {
                    request.write("index");
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
                } else if (line.startsWith("get")) {
                    request.write(line);
                    request.write("\n");
                    request.flush();
                }

                String s = null;
                while (response.hasNext()) {
                    s = response.nextLine();
                    if (s == null) 
                        break;

                    System.out.println(s);
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                request.close();
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FileClient();
    }
}
