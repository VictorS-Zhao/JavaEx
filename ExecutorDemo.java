import java.util.concurrent.*;

public class ExecutorDemo {
	private static class PrintChar implements Runnable {
		private char charToPrint;
		private int times;
		
		public PrintChar(char c, int t) {
			charToPrint = c;
			times = t;
		}

		@Override
			public void run() {
				try {
					for (int i = 1; i <= times; i++) {
						System.out.print("\nRun PrintChar"+Character.toUpperCase(charToPrint)+" with sleep "+i+" times: ");
						System.out.println("\t"+charToPrint);
						if (i >= 50) {
							Thread.sleep((int)(Math.random()*10000));

						}
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
	}

	private static class PrintCharCapital implements Runnable {
		private char charToPrint;
		private int times;
		
		public PrintCharCapital(char c, int t) {
			charToPrint = c;
			times = t;
		}

		@Override
			public void run() {
				Thread t4 = new Thread(new PrintCharString('z', 800));
				t4.start();
				try {
					for (int i = 1; i <= times; i++) {
						System.out.print("\nRun PrintChar"+Character.toUpperCase(charToPrint)+" "+i+" times: ");
						System.out.println("\t"+Character.toUpperCase(charToPrint));
						if (i >= 3) {
							t4.join();
						}
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
	}

	private static class PrintCharString implements Runnable {
		private char charToPrint;
		private int times;
		
		public PrintCharString(char c, int t) {
			charToPrint = c;
			times = t;
		}

		@Override
		public void run() {
			for (int i = 1; i <= times; i++) {
				System.out.print(Character.toUpperCase(charToPrint));
			}
		}
	}

	private static class PrintNum implements Runnable {
		private int lastNum;

		public PrintNum(int n) {
			lastNum = n;
		}

		@Override
		public void run() {
			for (int i = 1; i <= lastNum; i++) {
				System.out.print("\nRun PrintNum with yield "+i+" times");
				System.out.println("\t"+i);
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) {
//		Runnable printA = new PrintChar('a', 100);
//		Runnable printB = new PrintCharCapital('b', 100);
//		Runnable print100 = new PrintNum(100);
//
//		Thread thread1 = new Thread(printA);
//		Thread thread2 = new Thread(printB);
//		Thread thread3 = new Thread(print100);
//		thread3.setPriority(Thread.MAX_PRIORITY);
//
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		ExecutorService executor = Executors.newFixedThreadPool(2);
		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(new PrintCharString('a', 100));
		executor.execute(new PrintCharString('b', 100));
		executor.execute(new PrintCharString('z', 100));

		executor.shutdown();
	}
}

