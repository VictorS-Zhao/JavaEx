import java.util.Arrays;

//class Processor {
//  public String name() {
//    return getClass().getSimpleName();
//  }
//
//  Object process(Object input) {
//    return input;
//  }
//}

interface Processor {
  String name();
  String process(Object input);
}

class Upcase implements Processor {
  @Override
  public String name() {
    return getClass().getSimpleName();
  }

  @Override
  public String process(Object input) {
    return ((String)input).toUpperCase();
  }
}

class Downcase implements Processor {
  @Override
  public String name() {
    return getClass().getSimpleName();
  }

  @Override
  public String process(Object input) {
    return ((String)input).toLowerCase();
  }
}

class Splitter implements Processor {
  @Override
  public String name() {
    return getClass().getSimpleName();
  }

  @Override
  public String process(Object input) {
    return Arrays.toString(((String)input).split(" "));
  }
}

public class Apply {
  public static void process(Processor p, Object s) {
    System.out.println("Using processor "+p.name());
    System.out.println(p.process(s));
  }

  public static void main(String[] args) {
    String s = "Disagreement with beliefs is by definition incorrect";
    process(new Upcase(), s);
    process(new Downcase(), s);
    process(new Splitter(), s);
  }

}

