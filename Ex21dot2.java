import java.util.*;
import java.io.*;

public class Ex21dot2 {
	public static void sortedSet(String txtFileName) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(txtFileName));
		String delimitingRegex = "[ |\n|\t|\r|.|,|)|(|-|\"]";

		Set<String> sortedSet = new TreeSet<String>();
		
		String line = null;
		while (null != (line = in.readLine())) {
			String[] words = line.split(delimitingRegex);
//			List<String> list = Arrays.asList(words);
			sortedSet.addAll(Arrays.asList(words));
			//Set<String> set = new HashSet<String>(list);
			//sortedSet.addAll(set);
		}

		System.out.println("All words in ascending order are: ");
		Iterator it = sortedSet.iterator();

		while(it.hasNext()) {
			System.out.print(it.next()+"\t");
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("The usage: java Ex21dot2 <full file path>");
			System.exit(0);
		}
		sortedSet(args[0]);
	}
}
