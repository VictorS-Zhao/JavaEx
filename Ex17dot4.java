import java.io.*;
import java.util.Scanner;

public class Ex17dot4 {
	public static void convertText2Utf2(String srcFileName, String dstFileName) {
		File srcFile = new File(srcFileName);
		if (!srcFile.exists()) {
			System.out.println("File "+srcFileName+" does not exist!");
			return;
		}

		File dstFile = new File(dstFileName);
		if (dstFile.exists()) {
			System.out.println("File "+dstFileName+" has already exist!");
		}

		try {
		BufferedReader input = new BufferedReader(new FileReader(srcFile));
		String s;
		DataOutputStream output = new DataOutputStream(new FileOutputStream(dstFile));
		while (null != (s = input.readLine())) {
			output.writeUTF(s);
		}
		System.out.println("The file "+dstFileName+" has "+output.size()+" bytes");
		
		output.close();
		input.close();

		FileInputStream fis = new FileInputStream(srcFileName);
		System.out.println("The file "+srcFileName+" has "+fis.available()+" bytes");
		FileInputStream fis2 = new FileInputStream(dstFileName);
		System.out.println("The file "+dstFileName+" has "+fis2.available()+" bytes");

		fis.close();
		fis2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void convertText2Utf(String srcFileName, String dstFileName) {
		File srcFile = new File(srcFileName);
		if (!srcFile.exists()) {
			System.out.println("File "+srcFileName+" does not exist!");
			return;
		}

		File dstFile = new File(dstFileName);
		if (dstFile.exists()) {
			System.out.println("File "+dstFileName+" has already exist!");
		}

		try {
		FileInputStream fis = new FileInputStream(srcFile);
		System.out.println("The file "+srcFileName+" has "+fis.available()+" bytes");
		Scanner input = new Scanner(fis);
		String s;
		DataOutputStream output = new DataOutputStream(new FileOutputStream(dstFile));
		while (input.hasNextLine()) {
			s = input.nextLine();
			output.writeUTF(s);
		}
		System.out.println("The file "+dstFileName+" has "+output.size()+" bytes");
		
		output.close();
		input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Ex17dot4 srcFile dstFile");
			System.exit(0);
		}

		convertText2Utf2(args[0], args[1]);
	}
}

