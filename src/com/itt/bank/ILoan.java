package com.itt.bank;

import com.itt.bank.accounts.BankAccount;

public interface ILoan {
	void displayLoanDetails(BankAccount bankAccount);

	BankAccount lendLoan(BankAccount bankAccount);

	BankAccount payDebt(BankAccount bankAccount);

}
