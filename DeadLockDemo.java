import java.util.*;

public class DeadLockDemo {
	public static Object l1 = new Object();
	public static Object l2 = new Object();

	private static class Thread1 extends Thread {
		@Override
		public void run() {
			synchronized (l1) {
				System.out.println("Thread 1: Holding lock 1...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("Thread 1 waits for lock 2...");
				synchronized (l2) {
					System.out.println("Thread 1: Holding lock 1 and 2...");
				}
			}
		}
	}

	private static class Thread2 extends Thread {
		@Override
		public void run() {
			synchronized (l2) {
				System.out.println("Thread 2: holding lock 2...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("Thread 2 waits for lock 1...");
				synchronized (l1) {
					System.out.println("Thread 2: holding lock 2 and 1...");
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread1();
		Thread t2 = new Thread2();
		t1.start();
		t2.start();
	}
}

