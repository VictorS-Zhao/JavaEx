import java.nio.*;

public class CharBufferTest {
  public static void main(String[] args) {
    CharBuffer cb = CharBuffer.allocate(30);
    String s = "new input output stream";
    cb.put(s);
    cb.flip();
    System.out.println(cb);
  }
}

