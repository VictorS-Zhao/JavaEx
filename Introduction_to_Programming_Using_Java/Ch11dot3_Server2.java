import java.io.*;
import java.util.*;
import java.net.*;

public class Ch11dot3_Server2 {
    private ServerSocket serverSocket;
    private Socket connection;
    private BufferedReader in;
    private PrintWriter out;  

  public Ch11dot3_Server2() {

    try {
      serverSocket = new ServerSocket(32007);

      while (true) {
          connection = serverSocket.accept();
          in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          out = new PrintWriter(connection.getOutputStream());
          String line = in.readLine().trim();
          System.out.println("received request "+line);
          if (line.equals("INDEX")) {
              List<String> filesList = listFiles(".");
              for (int i = 0; i < filesList.size(); i++) {
                  out.println(filesList.get(i));
              }
//              out.println("end-of-line");
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
//              out.println("end-of-line");
              out.flush();
          } else {
              System.out.println("Invalid request!");
              out.println("ERROR");
              out.println("Invalid request");
              out.flush();
          }

          out.close();
        in.close();
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
  
  public List<String> listFiles(String fileName) {
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
    new Ch11dot3_Server2();
  }
}
