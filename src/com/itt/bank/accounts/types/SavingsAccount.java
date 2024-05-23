package com.itt.bank.accounts.types;

import com.itt.bank.accounts.BankAccount;

public class SavingsAccount extends BankAccount {

	public SavingsAccount(String accountHolderName, double amount) {
		super(accountHolderName, amount);
		setAccountType("Savings Account");
	}

}
