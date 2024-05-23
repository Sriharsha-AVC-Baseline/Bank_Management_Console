package com.itt.sbi_bank.utilities;

import java.util.Map;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.Customers;
import com.itt.constantUtilities.DetailsScanner;

public class AccountCreator {

	@FunctionalInterface
	interface AddCustomer {
		Customers addAccountToCustomer(Customers customer, String userName);
	}

	private AccountFactoryOperations accountFactoryOperations = new AccountFactoryOperations();
	private AccountFactory accountFactory = new AccountFactory();
	private DetailsScanner detailsScanner = new DetailsScanner();

	public Customers createFreshCustomer(Map<String, Customers> PresentCustomers) {
		
		boolean userAlreadyExist = false;
		String userName = "", password = "", holderName = "";
		double deposit = 0;
		char accChoice = 0;
		Customers customers = null;
		long phoneNumber = 0;
		try {

			while (!userAlreadyExist) {
				userName = detailsScanner.scanUserName();
				if (PresentCustomers.containsKey(userName)) {
					System.out.println("Username Already Present, Enter unique username");
				} else {
					userAlreadyExist = true;
				}
			}

			holderName = detailsScanner.scanAccountHolderName();
			phoneNumber = detailsScanner.scanPhoneNumber();
			password = detailsScanner.scanPassword();
			accChoice = accountFactoryOperations.scanAccountTypeChoice();
			deposit = accountFactoryOperations.scanDeposit();

			customers = new Customers(holderName, userName, password, phoneNumber);

			BankAccount bankAccount = accountFactory.openAccount(userName, holderName, deposit, accChoice);
			customers.addAccount(bankAccount);
		}

		catch (Exception exception) {
			System.out.println(exception);
		}
		return customers;
	}

	public Customers addAccountToExistingCustomer(Customers customer, String userName) {

		char accChoice = accountFactoryOperations.scanAccountTypeChoice();
		double deposit = accountFactoryOperations.scanDeposit();

		AddCustomer addCustomer = (customera, userNamea) -> {
			BankAccount bankAccount = accountFactory.openAccount(userName, customer.getAccountHolderName(), deposit,
					accChoice);
			customer.addAccount(bankAccount);
			return customer;
		};

		return addCustomer.addAccountToCustomer(customer, userName);
	}

}
