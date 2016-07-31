import java.util.*;
import java.io.*;

public class Ex21dot4 {
	public static void countVowels(File file) throws Exception {
		char[] vowels = {'A', 'E', 'I', 'O', 'U'};

		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		for (char vowel : vowels) {
			countMap.put(vowel, 0);
		}

//		FileInputStream fis = new FileInputStream(file);
		FileReader reader = new FileReader(file);
		int i;
		while (-1 != (i = reader.read())) {
			char b = (char)i;
			System.out.println(b);
			if (b == 'A' || b == 'a') {
				int count = countMap.get('A').intValue();
				count++;
				countMap.put('A', count);
			} else if (b == 'E' || b == 'e') {
				int count = countMap.get('E').intValue();
				count++;
				countMap.put('E', count);
			} else if (b == 'I' || b == 'i') {
				int count = countMap.get('I').intValue();
				count++;
				countMap.put('I', count);
			} else if (b == 'O' || b == 'o') {
				int count = countMap.get('O').intValue();
				count++;
				countMap.put('O', count);
			} else if (b == 'U' || b == 'u') {
				int count = countMap.get('U').intValue();
				count++;
				countMap.put('U', count);
			}
		}

		System.out.println("The count of vowels is: ");
		System.out.println(countMap);

		TreeMap<Character, Integer> sortedMap = new TreeMap<Character, Integer>(countMap);
		Set<Map.Entry<Character, Integer>> entrySet = sortedMap.entrySet();
		for (Map.Entry<Character, Integer> entry : entrySet) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Enter a text file name: ");
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();
		File file = new File(fileName);
		if (file.exists()) {
			countVowels(file);
		} else {
			System.out.println("The file "+fileName+" does not exist!");
		}
	}
}

