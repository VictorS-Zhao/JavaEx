import java.io.*;
import java.util.*;

public class Ch11dot1 {
  public static void listDirectory(String directoryName) {
    File directory = new File(directoryName);
    if (!directory.isDirectory()) {
      if (directory.exists()) {
        System.out.println("That file is not a directory!");
      } else {
        System.out.println("There is no such directory!");
      }
    } else {
      String[] files = directory.list();
      for (String s : files) {
        File file = new File(directory, s);
        if (file.isDirectory()) {
          listDirectory(s);
        } else {
          System.out.println("File in directory "+directory+" :"+s);
        }
      }
    }
  }

  public static void listFiles(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      System.out.println("No such file or directory!");
      return;
    }
    if (file.isDirectory()) {
      System.out.println("\n"+fileName+":");
      String[] files = file.list();
      for (String s : files) {
        File f = new File(file, s);
        if (f.isDirectory()) {
          listFiles(f.getPath());
        } else {
          System.out.print(s+"\t");
        }
      }
      System.out.println();
    } else {
      System.out.println(fileName);
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a directory: ");
    String s = input.nextLine().trim();
    listFiles(s);
  }
}
