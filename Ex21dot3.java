import java.util.*;
import java.io.*;

public class Ex21dot3 {
	public static void countKeywords(File file) throws Exception {
		String[] keywordString = {"abstract", "assert", "boolean",
"break", "byte", "case", "catch", "char", "class", "const",
"continue", "default", "do", "double", "else", "enum",
"extends", "for", "final", "finally", "float", "goto",
"if", "implements", "import", "instanceof", "int",
"interface", "long", "native", "new", "package", "private",
"protected", "public", "return", "short", "static",
"strictfp", "super", "switch", "synchronized", "this",
"throw", "throws", "transient", "try", "void", "volatile",
"while", "true", "false", "null"};

		//Set<String> keyWordSet = new HashSet<String>(Arrays.asList(keyWordString));

		Map<String, Integer> countMap = new HashMap<String, Integer>();

		for (String keyword : keywordString) {
			countMap.put(keyword, 0);
		}

		Scanner in = new Scanner(file);
		while (in.hasNext()) {
			String word = in.next();
			System.out.println(word);
			if (word.equals("//")) {
				in.nextLine();
			} else if (word.contains("\"")) {
				String nextWord;
				do {
					nextWord = in.next();
				} while (!nextWord.contains("\""));
			} else if (word.contains("/*")) {
				String nextWord;
				do {
					nextWord = in.next();
				} while (!nextWord.contains("*/"));
			} else {

				if (countMap.get(word) != null) {
					int count = countMap.get(word).intValue();
					count++;
					countMap.put(word, count);
				}
			}
		}

		System.out.println("Keywords and their count in ascending order of keyword is: ");
		System.out.println(countMap);

		Set<Map.Entry<String, Integer>> entrySet = countMap.entrySet();
		for (Map.Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Enter a java source file name: ");
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();

		File file = new File(fileName);
		if (file.exists()) {
			System.out.println("The number of keyword in "+fileName+" as below: ");
			countKeywords(file);
		} else {
			System.out.println("File "+fileName+" does not exist");
		}
	}
}

