
class FilterAdapter implements Processor {
  Filter filter;
  public FilterAdapter(Filter filter) {
    this.filter = filter;
  }

  public String name() {
    return filter.name();
  }

  public String process(Object input) {
    return filter.process((Waveform)input).toString();
  }

}

public class FilterProcessor {
  public static void process(Processor p, Object s) {
    System.out.println("Using processor "+p.name());
    System.out.println(p.process(s));
  }

  public static void main(String[] args) {
    Waveform w = new Waveform();
    process(new FilterAdapter(new LowPass(1.0)), w);
    process(new FilterAdapter(new HighPass(2.0)), w);
    process(new FilterAdapter(new BandPass(3.0, 4.0)), w);
  }
}


