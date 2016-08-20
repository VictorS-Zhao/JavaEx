import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Ex30dot8 {
	private static Account account = new Account();

	private static class Account {
//		private static Lock lock = new ReentrantLock();

//		private static Condition newDeposit = lock.newCondition();

		private int balance = 0;
		private int withdrawTimes = 0;
		private int depositTimes = 0;

		public int getBalance() {
			return balance;
		}

		public synchronized void withdraw(int amount) {
//			lock.lock();
			try {
				while (balance < amount) {
//					newDeposit.await();
					System.out.println("\t\t\tNow the balance is "+balance+", but need "+amount+" so wait!");
					wait();
				}

				withdrawTimes++;
				System.out.println(withdrawTimes+" times withdraw");
				balance -= amount;
				System.out.println("\t\t\tWithdraw "+amount+"\t\t"+getBalance());
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			finally {
//				lock.unlock();
			}
		}

		public synchronized void deposit(int amount) {
//			lock.lock();
			try {
				depositTimes++;
				System.out.println(depositTimes+" times deposit");
				balance += amount;
				System.out.println("Deposit "+amount+"\t\t\t\t\t"+getBalance());

//				newDeposit.signalAll();
				notifyAll();
			}
			finally {
//				lock.unlock();
			}
		}
	}

	public static class DepositTask implements Runnable {
		public void run() {
			try {
				while (true) {
					account.deposit((int)(Math.random() * 10) + 1);
					Thread.sleep(1000);
				}
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static class WithdrawTask implements Runnable {
		public void run() {
			while (true) {
				account.withdraw((int)(Math.random() * 10) + 1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Thread 1\t\tThread 2\t\tBalance");

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
	}
}
