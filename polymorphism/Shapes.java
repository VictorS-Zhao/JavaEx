
public class Shapes {
  public static void main(String[] args) {
    RandomShapeGenerator gen = new RandomShapeGenerator();
    Shape[] shapes = new Shape[9];
    for (Shape s : shapes) {
      s = gen.next();
      s.print();
      s.draw();
    }
  }
}

