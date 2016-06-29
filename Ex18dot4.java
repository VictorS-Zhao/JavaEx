
public class Ex18dot4 {
	public static double m(int i) {
		if (1== i) {
			return 1.0;
		}

		return 1.0/i + m(i-1);
	}

	public static double m2(int i) {
		if (1 == i) {
			return 1.0/2;
		}

		return i*1.0 / (i+1) + m2(i-1);
	}

	public static void main(String[] args) {
	
		for (int i=1; i<=10; i++) {
		System.out.println("m("+i+"): "+m(i));
		System.out.println("m2("+i+"): "+m2(i));
		}
	}
}

