import java.io.*;
import java.util.*;

public class Ch11dot2 {
  public static void countLines(String[] fileNames) {
    for (String s : fileNames) {
      int count = 0;
      File f = new File(s);
      if (!f.exists()) {
        System.out.println("No such file "+s);
        continue;
      }

      try {
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
          input.nextLine();
          count++;
        }
        System.out.println("The file "+s+" has "+count+" lines.");
      } catch (IOException ex) {
        ex.printStackTrace();
      }

    }
  }

  public static void main(String[] args) {
  
    if (args.length < 1) {
      System.out.println("The usage: \n Java Ch11dot2 <file1> <file2> <file3> ...");
      System.exit(0);
    }

    countLines(args);
  }
}
