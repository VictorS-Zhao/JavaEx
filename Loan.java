
public class Loan implements java.io.Serializable {
	private double rate;
	private int numOfYears;
	private int loanAmount;
	private double totalPayment;
	private double monthlyPayment;

	public Loan(double rate, int numOfYears, int loanAmount) {
		this.rate = rate;
		this.numOfYears = numOfYears;
		this.loanAmount = loanAmount;
	}

	public double getRate() {
		return rate;
	}

	public int getNumOfYears() {
		return numOfYears;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setTotalPayment(double payment) {
		this.totalPayment = payment;
	}

	public void setMonthlyPayment(double payment) {
		this.monthlyPayment = payment;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}
}
