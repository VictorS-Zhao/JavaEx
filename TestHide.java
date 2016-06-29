
public class TestHide {
	public static void main(String[] args) {
		A a = new B();
		System.out.println("a.getString(): "+a.getString());
	}
}

class A {
	String getString() {
		return "From A";
	}
}

class B extends A {
	String getString() {
		return "From B";
	}
}

