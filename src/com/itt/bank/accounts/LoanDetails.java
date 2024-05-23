package com.itt.bank.accounts;

public abstract class LoanDetails {

	private long loanId;
	private double amount;
	private String loanType;
	private int years;
	private double rateOfInterest;
	private boolean debtCleared = true;

	public LoanDetails(double amount, int years) {
		super();
		this.loanId = this.hashCode();
		this.amount = amount;
		this.years = years;
		debtCleared = false;
	}

	public abstract void calculateTotalLoan();

	public boolean isDebtCleared() {
		return debtCleared;
	}

	protected void setDebtCleared(boolean debtCleared) {
		this.debtCleared = debtCleared;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getYears() {
		return years;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	@Override
	public String toString() {
		return "Loans [loanId=" + loanId + ", amount=" + amount + ", loanType=" + loanType + ", years=" + years
				+ ", rateOfInterest=" + rateOfInterest + "]";
	}

}
