import java.util.*;
import java.io.*;

public class Ex21dot8 {
	public static void countOccurrenceOfWords(File textFile) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(textFile));

		Map<String, Integer> map = new TreeMap<String, Integer>();

		String line = null;
		while ((line = reader.readLine()) != null) {

//			String[] words = line.split("[ \n\t\r.,;:!?(){}\"/]");
			String[] words = line.split("[ @!~{}\\[\\]$#^&*\n\t\r.,;?'\")(]");
			for (int i = 0; i < words.length; i++) {
				String key = words[i].toLowerCase();

				if (key.trim().length() > 0 && key.trim().matches("[A-Z|a-z]+")) {
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
		}

                // Get all entries into a set
                Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
                // Get key and value from each entry
                for (Map.Entry<String, Integer> entry: entrySet)
                        System.out.println(entry.getValue() + "\t" + entry.getKey());
        }


        public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage:\n"+
				"java Ex21dot8 <textFileName>");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (file.exists()) {
                countOccurrenceOfWords(file);
		} else {
			System.out.println("The file"+args[0]+" does not exist!");
		}
	}

 }

