import java.nio.*;
import java.util.*;

public class AdaptedCharSwapper extends CharSwapper implements Readable {
  private String[] input;
  private int count;
  private int index = 0;

  public AdaptedCharSwapper(String[] input) {
    this.input = input;
    this.count = input.length;
  }

  @Override
  public int read(CharBuffer cb) {
    if (count-- == 0)
      return -1;

    String result = swap(input[index++])+"\n";
    cb.append(result);
    return result.length();
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(new AdaptedCharSwapper(new String[]{"new input output stream", "If she weighs the same as a duck, she’s made of wood", "fuck damn shit!"}));
    while(s.hasNextLine()) {
      System.out.println(s.nextLine());
    }
  }
}

