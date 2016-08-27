import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.LinkedList;

public class ConsumerProducer2 {
	private static class Buffer {
		private static final int CAPACITY = 2;
		private java.util.LinkedList<Integer> queue = new LinkedList<Integer>();

		private static Lock lock = new ReentrantLock();

		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();

		private static int flag = 0;

		public void write(int value) {
			lock.lock();
			try {
				while (flag % 2 != 0) {
					notFull.await();
				}
				System.out.println("Now write value "+value);
				flag++;
				notEmpty.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public int read() {
			int value = 0;
			lock.lock();
			try {
				while (flag % 2 == 0) {
					notEmpty.await();
				}
				System.out.println("\t\t\tHas read value "+value);
				flag++;
				notFull.signal();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				
				return value;
			}
		}
	}


	private static Buffer buffer = new Buffer();

	private static class ProducerTask implements Runnable {
		private int times = 0;
		public void run() {
			System.out.println("Current thread id: "+Thread.currentThread().getName());
			try {
				int i = 1;
				while (true) {
					times++;
					System.out.println("ProducerTask run "+times+ " times");
//					System.out.println("Producer writes "+i);
					buffer.write(i++);
//					Thread.sleep((int)(Math.random() * 10000));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class ConsumerTask implements Runnable {
		private int times = 0;
		public void run() {
			System.out.println("\t\t\tCurrent thread id: "+Thread.currentThread().getName());
			try {
				while (true) {
					times++;
					System.out.println("\t\t\tConsumerTask run "+times+" times");
//					System.out.println("\t\t\tConsumer reads "+buffer.read());
					buffer.read();
//					Thread.sleep((int)(Math.random()*10000));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}
}

