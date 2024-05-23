package com.itt.bank.accounts.types;

import com.itt.bank.accounts.BankAccount;
import com.itt.exceptions.InsufficientFundsException;

public class CurrentAccount extends BankAccount {

	public CurrentAccount(String accountHolderName, double amount) {
		super(accountHolderName, amount);
		setAccountType("Current Account");
	}

	@Override
	public void withdrawMoney(double amount) throws InsufficientFundsException {
		if (getBalance() < amount || getBalance() < 10) {
			throw new InsufficientFundsException();
		}

		double balance = getBalance() - amount;
		setBalance(balance);

	}

}
