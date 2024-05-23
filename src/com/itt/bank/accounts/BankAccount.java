package com.itt.bank.accounts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itt.bank.constants.TransactionType;
import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.InvalidAmountException;

public abstract class BankAccount extends AccountDetails {

	private AccountOperations accountOperations = new AccountOperations();

	public BankAccount(String accountHolderName, double amount) {
		super(accountHolderName, amount);
		addTransaction(new Transactions(amount, getBalance(), new Date(), TransactionType.CREDIT));
	}

	public void withdrawMoney(double amount) throws InsufficientFundsException {

		setBalance(accountOperations.WithdrawMoney(amount, getBalance()));
		addTransaction(new Transactions(amount, getBalance(), new Date(), TransactionType.DEBIT));
	}

	public void depositMoney(double amount) throws InvalidAmountException {
		setBalance(accountOperations.depositMoney(amount, getBalance()));
		addTransaction(new Transactions(amount, getBalance(), new Date(), TransactionType.CREDIT));
	}

	public void showBalance() {
		System.out.println("Your Bank Account has : " + getBalance());
	}

	public void cancelLoans(LoanDetails loan) {
		Map<Long, LoanDetails> loanDetails = getLoansDetails();
		loanDetails.remove(loan.getLoanId());
		setLoans(loanDetails);
	}

	public void updateLoans(LoanDetails loan) {
		setLoans(accountOperations.updateLoans(loan, getLoansDetails()));
	}

	public void printLoanDetails() {
		accountOperations.printLoanDetails(getLoansDetails());
	}

	public boolean hasPendingLoans() {
		List<Long> loanIds = new ArrayList<>(getLoansDetails().keySet());
		for (int i = 0; i < loanIds.size(); i++) {
			if (!getSpecificLoan(loanIds.get(i)).isDebtCleared()) {
				return true;
			}
		}
		return false;
	}

	public void showTransactions() {
		accountOperations.showTransactions(getTransactions());
	}

}
