import java.io.*;

public class CircularLogFile {
	public static final String LOG_FILE_NAME = "circular.log";
	public static final int LOG_FILE_SIZE_MAX = 1024;

	public static long getLastPos() throws IOException {
		File f = new File(".pos");
		if (!f.exists()) {
			return 0L;
		}
		DataInputStream in = new DataInputStream(new FileInputStream(f));
		long pos = in.readLong();
		in.close();
		return pos;
	}

	public static void saveLastPos(long pos) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(".pos"));
		out.writeLong(pos);
		out.close();
	}

	public static void writeBuffer(String[] buf) throws IOException {
		                RandomAccessFile raf = new RandomAccessFile(LOG_FILE_NAME, "rw");
                System.out.println("file size: "+raf.length());
                System.out.println("file pointer: "+raf.getFilePointer());
                long pos = getLastPos();
                if (raf.length() >= LOG_FILE_SIZE_MAX && pos >= LOG_FILE_SIZE_MAX) {
                        raf.seek(0);
                } else {
                        raf.seek(pos);
                }

		for (String s : buf) {
			raf.writeChars(s);
		}

                saveLastPos(raf.getFilePointer());

                raf.close();
	}

	public static void writeLine(String line) throws IOException {
		//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(logFileName, true));
		RandomAccessFile raf = new RandomAccessFile(LOG_FILE_NAME, "rw");
		System.out.println("file size: "+raf.length());
		System.out.println("file pointer: "+raf.getFilePointer());
		long pos = getLastPos();
                if (raf.length() >= LOG_FILE_SIZE_MAX && pos >= LOG_FILE_SIZE_MAX) {
                        raf.seek(0);
                } else {
			raf.seek(pos);
		}
		
/*
		if (raf.length() >= LOG_FILE_SIZE_MAX && raf.getFilePointer() >= LOG_FILE_SIZE_MAX) {
			raf.seek(0);
		} else if (raf.length() >= LOG_FILE_SIZE_MAX && raf.getFilePointer() < LOG_FILE_SIZE_MAX) {
		} else if (raf.length() < LOG_FILE_SIZE_MAX) {
			raf.seek(raf.length());
		}
*/

		raf.writeChars(line+"\n");
		saveLastPos(raf.getFilePointer());
	
		raf.close();
	}

	public static void main(String[] args) throws IOException {
		int bufNum = 0;
		String[] buf = new String[9];

		while (true) {
			bufNum++;
			buf[0] = "==========This is buffer "+bufNum+" ==========" +"\n";
			for (int lineNumber = 1; lineNumber < 9; lineNumber++) {
				buf[lineNumber] = "This is Line "+lineNumber+"\n";
			}
			writeBuffer(buf);
		}
	}
}

