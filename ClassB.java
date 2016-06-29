
public class ClassB {
	public int numB;
	public ClassA a;
	public ClassB(int num) {
		numB = num;
		a = new ClassA(0);
	}

	public static void main(String[] args) {
		ClassB b = new ClassB(9);

		System.out.println("b.numB: "+b.numB+"; b.a.numA: "+b.a.numA);
	}
}
