
public class RandomShapeGenerator {
  public Shape next() {
    switch ((int)(Math.random()*3)) {
      case 0:
        return new Circle();
      case 1:
        return new Square();
      case 2:
        return new Triangle();
      default:
        return null;
    }
  }
}

