package com.itt.bank;

import com.itt.bank.accounts.BankAccount;

public interface Deposit {
	BankAccount depositAmount(BankAccount account);
}
