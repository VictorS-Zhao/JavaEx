import java.util.concurrent.*;
import java.util.*;

public class Ex30dot9 {
	private static Set<Integer> hashSet = new HashSet<Integer>();

	private static class WriteTask implements Runnable {
		public WriteTask(Set<Integer> hashSet) {
			this.hashSet = hashSet;
		}

		private Set<Integer> hashSet;

		@Override
		public void run() {
			while (true) {
				hashSet.add((int)(Math.random()*10));
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
			while (true) {
				Iterator it = hashSet.iterator();

				while (it.hasNext()) {
					System.out.println(it.next());
				}
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

