
class CharSwapperAdapter implements Processor {
  private CharSwapper swapper = new CharSwapper();

  @Override
  public String name() {
    return getClass().getSimpleName();
  }

  @Override
  public String process(Object input) {
    return swapper.swap((String)input);
  }
}

public class CharSwapperProcessor {

  public static void main(String[] args) {
    Apply.process(new CharSwapperAdapter(), "If she weighs the same as a duck, she’s made of wood");
  }
}
