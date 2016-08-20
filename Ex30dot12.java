import java.util.concurrent.*;
import java.util.Random;

public class Ex30dot12 {

	public static void parallelAssignValues(double[] list) {
		RecursiveAction mainTask = new AssignTask(list, 0, list.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}

	private static class AssignTask extends RecursiveAction {
		private final int THRESHOLD = 50000;
		private double[] list;
		private int startIndex;
		private int endIndex;

		public AssignTask(double[] list, int startIndex, int endIndex) {
			this.list = list;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		protected void compute() {
			Random random = new Random();
//			if (list.length < THRESHOLD) {
//				for (int i = 0; i < list.length; i++) {
//					list[i] = random.nextDouble();
//				}
//			} else {
//				double[] firstHalfList = new double[list.length/2];
//				System.arraycopy(list, 0, firstHalfList, firstHalfList.length);
//
//				double[] secondHalfList = new double[list.length - list.length/2];
//				System.arraycopy(list, list.length/2, secondHalfList, secondHalfList.length);

			if (endIndex - startIndex < THRESHOLD) {
				for (int i = startIndex; i < endIndex; i++) {
					list[i] = random.nextDouble();
				}
			} else {
				int mid = (startIndex + endIndex) / 2;
				RecursiveAction left = new AssignTask(list, startIndex, mid);
				RecursiveAction right = new AssignTask(list, mid, endIndex);

				left.fork();
				right.fork();

				left.join();
				right.join();
//				invokeAll(new AssignTask(list, startIndex, mid), new AssignTask(list, mid, endIndex));
			}
		}
	}

	public static void main(String[] args) {
		double[] list = new double[9000000];

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.length; i++) {
//			System.out.print(list[i]+" ");
			list[i] = Math.random();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Sequential time is "+(endTime - startTime));
	
		startTime = System.currentTimeMillis();
		parallelAssignValues(list);
		endTime = System.currentTimeMillis();
		System.out.println("Time with "+Runtime.getRuntime().availableProcessors()+" processors: "+(endTime - startTime));
				
}
}

