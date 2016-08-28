
public class CharSwapper {
  public String swap(String input) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < input.length()/2; i++) {
      result.append(input.charAt(i*2+1)).append(input.charAt(i*2));
    }

    if (input.length()%2 == 1) {
      result.append(input.charAt(input.length() -1));
    }

    return result.toString();
  }
}
