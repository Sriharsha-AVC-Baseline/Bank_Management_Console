package com.itt.bank.accounts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AccountDetails {
	private long accountNumber;
	private String accountHolderName;
	private double balance;
	private List<Transactions> transactions;
	private Map<Long, LoanDetails> loanDetails;
	private String accountType;

	public AccountDetails(String accountHolderName, double amount) {
		this.accountNumber = this.hashCode();
		this.accountHolderName = accountHolderName;
		this.balance = amount;
		transactions = new ArrayList<>();
		loanDetails = new LinkedHashMap<>();
	}

	
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}


	protected String getAccountHolderName() {
		return accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
	}

	protected List<Transactions> getTransactions() {
		return transactions;
	}

	protected void addTransaction(Transactions transaction) {
		transactions.add(transaction);
	}

	protected void setLoans(Map<Long, LoanDetails> loanDetails) {
		this.loanDetails = loanDetails;
	}

	public Map<Long, LoanDetails> getLoansDetails() {
		return loanDetails;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public LoanDetails getSpecificLoan(long loanId) {
		return loanDetails.get(loanId);
	}

	public void addLoans(LoanDetails loanDetails) {
		this.loanDetails.put(loanDetails.getLoanId(), loanDetails);
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
}
