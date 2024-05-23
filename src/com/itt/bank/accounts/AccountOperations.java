package com.itt.bank.accounts;

import java.util.List;
import java.util.Map;

import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.InvalidAmountException;

public class AccountOperations {


	public void showTransactions(List<Transactions> transactions) {
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println(transactions.get(i));
		}
	}
	
	public Map<Long,LoanDetails> updateLoans(LoanDetails loan,Map<Long, LoanDetails> loanDetails) {
		
		if (loan.getAmount() < 0) {
			loan.setAmount(0);
			loan.setDebtCleared(true);
		}
		loanDetails.replace(loan.getLoanId(), loan);
		return loanDetails;
	}
	
	public double WithdrawMoney(double amount,double balance) throws InsufficientFundsException
	{
		if (balance < amount) {
			throw new InsufficientFundsException();
		}
		 balance = balance - amount;
		return balance;
	}
	
	public double depositMoney(double amount,double balance) throws InvalidAmountException
	{
		if (amount <= 0) {
			throw new InvalidAmountException();
		}
		balance += amount;
		return balance;
	}
	
	public void printLoanDetails(Map<Long,LoanDetails> loanDetails)
	{
		if (loanDetails.isEmpty()) {
			System.out.println("You didnot take any Loans");
		} else {
			loanDetails.forEach((k, e) -> System.out.println(e));
		}
	}
	
}
