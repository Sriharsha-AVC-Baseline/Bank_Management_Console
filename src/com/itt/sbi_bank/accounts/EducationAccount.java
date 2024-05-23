package com.itt.sbi_bank.accounts;

import com.itt.bank.accounts.BankAccount;
import com.itt.exceptions.InsufficientFundsException;

public class EducationAccount extends BankAccount{


	public EducationAccount( String accountHolderName, double amount) {
		super( accountHolderName, amount);
		setAccountType("Education Account");
	}

	@Override
	public void withdrawMoney(double amount) throws InsufficientFundsException {
		
		if(getBalance() < 100 || getBalance() < amount)
		{
			throw new InsufficientFundsException();
		}
		
		double balance = getBalance() - amount;
		setBalance(balance);
	}

}
