import java.util.*;
import java.io.*;

public class Ex21dot7 {
	public static class WordOccurrence implements Comparable<WordOccurrence> {
		public String word;
		public int count;

		public WordOccurrence(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(WordOccurrence o) {
//			return this.count - ((WordOccurrence)o).count;
			return this.count - o.count;
		}

		@Override
		public String toString() {
			return "["+word+"="+count+"]";
		}
	}

	public static void countOccurrenceOfWords2() {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		List<WordOccurrence> list = new ArrayList<WordOccurrence>();

		String[] words = text.split("[ \n\t\r.,;:!?]");
		for (String w : words) {
			System.out.println(w);
		}
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();

			int index;
			boolean found = false;
			if (key.length() > 0) {
				for (index = 0; index < list.size(); index++) {
					WordOccurrence wordOccur = list.get(index);
					if (key.equals(wordOccur.word)) {
						wordOccur.count++;
						found = true;
						break;
					}
				}
				if (!found) {
					list.add(new WordOccurrence(key, 1));
				}	
			}
		}

		Collections.sort(list);

		System.out.println("display the words in ascending order of occurrence counts: ");
		System.out.println(list);
	}

	public static class WordOccurrence2 {
		public String word;
		public int count;

		public WordOccurrence2(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return "["+word+"="+count+"]";
		}
	}

	public static class WordOccurrenceComparator implements Comparator<WordOccurrence2> {

		@Override
		public int compare(WordOccurrence2 o1, WordOccurrence2 o2) {
			return o1.count - o2.count;
		}
	}

	public static void countOccurrenceOfWords() {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		// Create a TreeMap to hold words as key and count as value
//		Map<String, Integer> map = new TreeMap<String, Integer>(new WordOccurrenceComparator());
		Set<WordOccurrence2> set = new TreeSet<WordOccurrence2>(new WordOccurrenceComparator());

		String[] words = text.split("[ \n\t\r.,;:!?()]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();

			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}

		// Get all entries into a set
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		// Get key and value from each entry
		for (Map.Entry<String, Integer> entry: entrySet)
			System.out.println(entry.getValue() + "\t" + entry.getKey());
	}


	public static void main(String[] args) {
		//countOccurrenceOfWords2();
		countOccurrenceOfWords();
	}
 }
