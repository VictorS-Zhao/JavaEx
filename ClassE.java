
public class ClassE {
	public static void main(String[] args) {
		System.out.println("Random integer between 0 and 65535: "+(int)(Math.random()*(65535+1)));

		System.out.println("Random lowercase letter: "+(char)('a'+Math.random()*('z'-'a'+1)));
	}

}
