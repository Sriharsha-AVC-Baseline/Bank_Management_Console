package com.itt.sbi_bank.loans;

import com.itt.bank.accounts.LoanDetails;

public class HomeLoan extends LoanDetails {

	public HomeLoan(double amount, int years) {
		super(amount, years);
		setRateOfInterest(3.6);
		calculateTotalLoan();
		setLoanType("Home Loan");
	}

	@Override
	public void setLoanId(long loanId) {
		setLoanId(this.hashCode());

	}

	@Override
	public void calculateTotalLoan() {
		setAmount(getAmount() + ((getAmount() + getYears() * getRateOfInterest()) / 100));
	}

}
