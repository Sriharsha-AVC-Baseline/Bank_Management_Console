package com.itt.sbi_bank;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.Customers;

public class SbiOperations {

	public Optional<Customers> getCustomer(Map<String, Customers> customers, String userName, String password) {
		Customers currentCustomer = null;

		if (customers.containsKey(userName)) {

			if (customers.get(userName).validateCustomer(userName, password)) {
				currentCustomer = customers.get(userName);
			} else {
				System.out.println("Invalid Credentials");
			}
		}

		return Optional.ofNullable(currentCustomer);

	}


	public Optional<Customers> findRecipient(long accountNumber, Map<String, Customers> customers) {
	
		Optional<Customers> recipient = Optional.empty();
		for (Customers ids : customers.values()) {
			if (ids.getAccounts().containsKey(accountNumber)) {
				recipient = Optional.ofNullable(ids);
				break;
			}
		}

		return recipient;
	}

	public Optional<BankAccount> findRecipientBankAccount(long accountNumber, Map<String, Customers> customers) {
		Optional<BankAccount> recipientAccount = Optional
				.ofNullable(findRecipient(accountNumber, customers).get().getAccounts().get(accountNumber));
		return recipientAccount;
	}

}
