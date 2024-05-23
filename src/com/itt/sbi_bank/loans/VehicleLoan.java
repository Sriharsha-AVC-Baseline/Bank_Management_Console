package com.itt.sbi_bank.loans;

import com.itt.bank.accounts.LoanDetails;

public class VehicleLoan extends LoanDetails {
	
	public VehicleLoan(double amount, int years) {
		super(amount, years);
		setRateOfInterest(6.8);
		calculateTotalLoan();
		setLoanType("Vehicle Loan");
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
