import java.util.concurrent.*;
import java.util.*;

public class Ex30dot10 {
	private static Set<Integer> hashSet = Collections.synchronizedSet(new HashSet<Integer>());

	private static class WriteTask implements Runnable {
		public WriteTask(Set<Integer> hashSet) {
			this.hashSet = hashSet;
		}

		private Set<Integer> hashSet;

		@Override
		public void run() {
			try {
			while (true) {
				hashSet.add((int)(Math.random()*1000));

				Thread.sleep(1000);
			}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static class ReadTask implements Runnable {
		public ReadTask(Set<Integer> hashSet) {
			this.hashSet = hashSet;
		}

		private Set<Integer> hashSet;

		@Override
		public void run() {
			try {
			while (true) {
				synchronized (this.hashSet) {
				Iterator it = hashSet.iterator();

				System.out.print("[ ");
				while (it.hasNext()) {
					System.out.print(it.next()+",");
				}
				System.out.println(" ]");
				}

				Thread.sleep(1000);
			}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(2);
		exe.execute(new ReadTask(hashSet));
		exe.execute(new WriteTask(hashSet));
		exe.shutdown();
	}
}

