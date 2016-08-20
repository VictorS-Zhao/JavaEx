import java.util.*;
import java.io.*;

public class Ex21dot6 {
	public static void countOccurrenceOfNumbers() {
		System.out.println("Enter an array of integer numbers: ");
		Scanner input = new Scanner(System.in);
		List<Integer> numbers = new ArrayList<Integer>();
		Integer number;
		while (input.hasNextInt() && (number = input.nextInt()) != 0) {
			numbers.add(number);
		}

		Map<Integer, Integer> occurrence = new HashMap<Integer, Integer>();
		ListIterator<Integer> it = numbers.listIterator();
		while (it.hasNext()) {
			Integer num = it.next();
			if (occurrence.get(num) != null) {
				int count = occurrence.get(num).intValue();
				count++;
				occurrence.put(num, count);
			} else {
				occurrence.put(num, 1);
			}
		}
		
		Map<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(occurrence);

		Integer maxCount = Collections.max(sortedMap.values());
		Set<Integer> keys = sortedMap.keySet();
		Iterator<Integer> iterator = keys.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			Integer value = sortedMap.get(key);
			if (value.equals(maxCount)) {
				System.out.println("Number "+key+" occurred most "+value+" times");
			}
		}
	}

	public static void main(String[] args) {
		countOccurrenceOfNumbers();
	}
}

				
			
			
