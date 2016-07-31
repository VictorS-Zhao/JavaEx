import java.util.*;

public class Ex21dot1 {
	public static void performSetOperations() {
		String[] strArray1 =  {"George",
"Jim", "John", "Blake", "Kevin", "Michael"};
		String[] strArray2 =  {"George", "Katie",
"Kevin", "Michelle", "Ryan"};

		Set<String> set1 = new HashSet<String>();
		for (String s : strArray1) {
			set1.add(s);
		}
		System.out.println("The set1 is: ");
		printSet(set1);

		Set<String> set2 = new HashSet<String>();
		for (String s : strArray2) {
			set2.add(s);
		}
		System.out.println("The set2 is: ");
		printSet(set2);

		System.out.println("The union of set1 and set2 is: ");
		Set<String> unionSet = new HashSet<String>(set1);
		unionSet.addAll(set2);
		printSet(unionSet);

		System.out.println("The difference of set1 from set2 is: ");
		Set<String> diffSet = new HashSet<String>(set1);
		diffSet.removeAll(set2);
		printSet(diffSet);

		System.out.println("The difference of set2 from set1 is: ");
		Set<String> diffSet2 = new HashSet<String>(set2);
		diffSet2.removeAll(set1);
		printSet(diffSet2);


		System.out.println("The intersection of set1 and set2 is: ");
		Set<String> interSet = new HashSet<String>(set1);
		interSet.retainAll(set2);
		printSet(interSet);

	}

	public static void printSet(Set<String> set) {
		System.out.println(set);
		
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		System.out.println();
	}

	public static void main(String[] args) {
		performSetOperations();
	}
}
	
