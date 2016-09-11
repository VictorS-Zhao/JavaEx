import java.util.*;
import java.io.*;
import java.net.*;

public class Ch11dot4_Client {
  private Socket socket;
  private BufferedWriter request;
  private static final String REQ_CMD_INDEX = "INDEX";
  private static final String REQ_CMD_GET = "GET";
  private String line;
  private Scanner input;
  private Scanner response; 
  private File localFile;

  public Ch11dot4_Client() {
    try {
      socket = new Socket("localhost", 32007);
        request = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
          response = new Scanner(socket.getInputStream());
        input = new Scanner(System.in);
      while (true) {
        System.out.println("Enter a command \n 'INDEX' \n 'GET <remote fileName> <local fileName>': ");

        String line = input.nextLine().trim();

        if (line.equalsIgnoreCase(REQ_CMD_INDEX)) {
          request.write(REQ_CMD_INDEX);
          request.write("\n");
          request.flush();


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

        } else if (line.startsWith("GET") || line.startsWith("get")) {
            String s[] = line.split(" ");
            if (s.length != 3) {
                System.out.println("invalid request parameters!");
            } else {
                String req = REQ_CMD_GET + " " + s[1];
                request.write(req);
                request.write("\n");
                request.flush();
                localFile = new File(s[2].trim());

                if (!localFile.exists()) {

//                    FileWriter fw = new FileWriter(localFile);
                    PrintWriter pw = new PrintWriter(new FileWriter(localFile));
                    boolean done = false;
                    while (!done) {
                        //          System.out.println("printing response...");
                        String ss = response.nextLine();
                        //           System.out.println("s: "+s);
                        if (ss.equals("end-of-line")) {
                            done = true;
                        } else {
                            System.out.println(ss);

                            pw.println(ss);
                        }
                    }
                    pw.flush();
                    pw.close();

                } else {
                    System.out.println("There is already local file "+s[2]);
                }
            }

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
    new Ch11dot4_Client();
  }
}
