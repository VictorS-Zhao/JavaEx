import java.io.*;

public class Ex17dot10 {
	public static void splitFile(String srcFileName, int numOfPieces) throws IOException {
		File f = new File(srcFileName);
		if (!f.exists()) {
			System.out.println("The file "+srcFileName+" does not exist!");
			return;
		}

		FileInputStream fis = new FileInputStream(f);
		int srcFileSize = fis.available();
		int pieceFileSize = (int)Math.ceil(1.0*srcFileSize / numOfPieces);
		int lastpieceFileSize = srcFileSize - pieceFileSize * (numOfPieces-1);
		System.out.println("the file size: "+srcFileSize);
		System.out.println("the piece file size: "+pieceFileSize);
		System.out.println("the last piece file size: "+lastpieceFileSize);
		
		int pos = 0;
		byte[] buffer = new byte[1024];
		DataInputStream in = new DataInputStream(fis);
		for (int i = 1; i < numOfPieces; i++) {
			DataOutputStream out = new DataOutputStream(new FileOutputStream("SourceFile."+i));
			int bytesToRead = pieceFileSize;
			int bytesRead = 0;
			while (bytesToRead > 0) {
				bytesRead = in.read(buffer, 0, bytesToRead > buffer.length ? buffer.length : bytesToRead);
				bytesToRead -= bytesRead;
				out.write(buffer, 0, bytesRead);
			}
/*
			int count = pieceFileSize / 1024;
			int lastBytes = pieceFileSize - count * 1024;
			System.out.println("the buffer count: "+count);
			System.out.println("the last bytes: "+lastBytes);
			for (int j = 0; j < count; j++) {
				pos += in.read(buffer, 0, 1024);
				out.write(buffer);
				System.out.println("Current pos: "+pos);
			}
			int len = in.read(buffer, 0, lastBytes);
			out.write(buffer, 0, len);
			pos += len;
			System.out.println("Current pos: "+pos);
*/
			out.flush();
			out.close();
		}
	
		DataOutputStream out = new DataOutputStream(new FileOutputStream("SourceFile."+numOfPieces));
		int bytesToRead = lastpieceFileSize;
		int bytesRead = 0;
		while (bytesToRead > 0) {
			bytesRead = in.read(buffer, 0, bytesToRead > buffer.length ? buffer.length : bytesToRead);
			bytesToRead -= bytesRead;
			out.write(buffer, 0, bytesRead);
		}
/*
		if (lastpieceFileSize < 1024) {
			int len = in.read(buffer, 0, lastpieceFileSize);
			out.write(buffer, 0, len);
			pos += len;
			System.out.println("Current pos: "+pos);

		} else {
			int count = lastpieceFileSize / 1024;
			int lastBytes = lastpieceFileSize - count * 1024;
			System.out.println("the buffer count: "+count);
			System.out.println("the last bytes: "+lastBytes);
			for (int j = 0; j < count; j++) {
				pos += in.read(buffer, 0, 1024);
				out.write(buffer);
				System.out.println("Current pos: "+pos);
			}
			int len = in.read(buffer, 0, lastBytes);
			out.write(buffer, 0, len);
			pos += len;
			System.out.println("Current pos: "+pos);
		}
*/
		out.flush();
		out.close();

		in.close();
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Ex17dot10 sourceFile numberOfPieces");
			System.exit(0);
		}

		try {
		splitFile(args[0], Integer.valueOf(args[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
			
