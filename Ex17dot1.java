import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Ex17dot1 {
	public static void writeTextFile3() {
		try {
		File file = new File("./Exercise17_01.dat");
		if (file.exists()) {
			file.delete();
		}

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true));
		Random r = new Random();
		
		int v;
		for (int i = 0; i < 100; i++) {
			v = r.nextInt(Integer.MAX_VALUE);
			System.out.println("random int is "+v);
			output.writeInt(v);
//			output.write(' ');
		}
		output.close();

		DataInputStream input = new DataInputStream(new FileInputStream(file));
		int value;
		while (0 != input.available()) {
			value = input.readInt();
			System.out.print(value + " ");
		}
		input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeTextFile2() {
		try {
		File file = new File("./Exercise17_01.dat");

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream output = new FileOutputStream(file, true);
		Random r = new Random();
		
		int v;
		for (int i = 0; i < 100; i++) {
			v = r.nextInt(Byte.MAX_VALUE);
			System.out.println("random int is "+v);
			output.write(v);
//			output.write(' ');
		}
		output.close();

		FileInputStream input = new FileInputStream(file);
		int value;
		while (-1 != (value = input.read())) {
			System.out.print(value + " ");
		}
		input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeTextFile() {
		try {
		File file = new File("./Exercise17_01.txt");

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		PrintWriter output = new PrintWriter(file);
		Random r = new Random();
		
		for (int i = 0; i < 100; i++) {
			output.print(r.nextInt(Integer.MAX_VALUE)+" ");
		}
		output.close();

		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			System.out.println(input.nextLine());
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		writeTextFile3();
	}
}
