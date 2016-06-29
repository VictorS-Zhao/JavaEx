
class TestFinal {
public void f1(final int i) {
System.out.println("i: "+i);
}

public void f2(int i) {
f1(i);
}

	public static void main(String[] args) {
		//TestFinal t = new TestFinal();
//t.f2(1);
//t.f2(2);
//t.f2(3);
String s = "v1.2.3";
String[] ss = s.split("v|\\.");
String s2="";
for (String s1 : ss) {
System.out.println("s: "+s1);
//if (!s1.isEmpty()) {

//}
s2+=s1;
}
System.out.println("s2: "+s2.trim());
}
}
