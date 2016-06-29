
public class ClassC {
	public static void main(String[] args) {
		String s = "Hello";
		char[] s2 = {'W', '3', 'c'};

		f(s, s2);

		System.out.println("s: "+s);
		System.out.println("s2: "+s2.toString());
		System.out.println("s2: "+String.valueOf(s2));
	}

	public static void f(String s, char[] s2) {
		s = new String("world");
		s2[0] = 'a';
	}
}

