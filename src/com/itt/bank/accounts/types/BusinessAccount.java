package com.itt.bank.accounts.types;

import com.itt.bank.accounts.BankAccount;
import com.itt.exceptions.InsufficientFundsException;

public class BusinessAccount extends BankAccount {

	public BusinessAccount( String accountHolderName, double amount) {
		super( accountHolderName, amount);
		setAccountType("Business Account");
	}

	@Override
	public void withdrawMoney(double amount) throws InsufficientFundsException {
		if (getBalance() < amount || getBalance() < 100) {
			throw new InsufficientFundsException();
		}

		double balance = getBalance() - amount;
		setBalance(balance);

	}


}
