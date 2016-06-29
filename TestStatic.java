public class TestStatic {
	B b = new B();
	static C c = new C();

	static {
		System.out.println("A static block");
	}
	
	TestStatic() {
		System.out.println("Object of A");
	}

	public static void main(String[] args) {
		TestStatic a;

		a = new TestStatic();
	
//		a = new TestStatic();
	}
}

class B {
	B() {
		System.out.println("Object of B");
	}
}

class C {
	C() {
		System.out.println("Object of C");
	}
}

