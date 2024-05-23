package com.itt.bank;

import com.itt.bank.accounts.BankAccount;
import com.itt.exceptions.InsufficientFundsException;

public interface Withdraw {
	
	BankAccount withdrawMoney(BankAccount account,double amount) throws InsufficientFundsException;
}
