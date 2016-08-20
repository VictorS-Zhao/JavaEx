import java.util.concurrent.*;

public class Ex30dot4 {
	private static class Integer {
		public int num = 0;

		public Integer(int num) {
			this.num = num;
		}
	}

	private static class AddTask implements Runnable {
		private Integer n;

		public AddTask(Integer n) {
//			System.out.println("Construct AddTask, the number is: "+n.num);
			this.n = n;
		}

		@Override
		public void run() {
//			System.out.println("Before add, the number is: "+n.num);
			synchronized (this.n) {
			this.n.num++;
			}
		}
	}

	public static void addOne() {
		Integer n = new Integer(0);
//		for (int i = 0; i < 1000; i++) {
//			new Thread(new AddTask(n)).start();
//		}
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			executor.execute(new AddTask(n));
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
		}

		System.out.println("After add 1000 times, the number is: "+n.num);
	}

	public static void main(String[] args) {
		addOne();
	}
}

