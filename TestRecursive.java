
public class TestRecursive {
	public static void main(String[] args) {
		String s = "cantv";

		System.out.println("F: "+f(s));
	}

	public static String f(String s) {
		return s.isEmpty() ? "" : f(s.substring(1))+s.charAt(0);
	}
}
