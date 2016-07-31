import java.io.*;

public class Ex17dot8 {
	public static void updateCount() {
		File f = new File("Ex17_08.dat");
		try {
		if (!f.exists()) {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			out.writeInt(1);
			out.close();
			System.out.println("this is the 1st time to open me!");
			return;
		}
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			int count = in.readInt();
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			out.writeInt(count+1);
			System.out.println("this is the "+(count+1)+" times to open me!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		updateCount();
	}
}
