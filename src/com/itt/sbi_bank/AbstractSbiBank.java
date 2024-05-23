package com.itt.sbi_bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.itt.bank.Bank;
import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.Customers;
import com.itt.bank.accounts.LoanDetails;
import com.itt.constantUtilities.Utilities;
import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.InvalidAmountException;
import com.itt.exceptions.RecipientNotFoundException;
import com.itt.sbi_bank.utilities.AccountCreator;
import com.itt.sbi_bank.utilities.LoanFactory;

public abstract class AbstractSbiBank extends Bank {

	private Map<String, Customers> customers;

	public AbstractSbiBank() {
		super("State Bank Of India");
		customers = getCustomers();
	}

	private BufferedReader bufferedReader = Utilities.bufferedReader;
	private SbiOperations sbiOperations = new SbiOperations();
	private AccountCreator accountCreator = new AccountCreator();

	@Override
	public void createAccount() {

		System.out.println("Do you already have an account in this bank ? (Y : yes / N : no (Default))");
		try {
			char res = Utilities.bufferedReader.readLine().toLowerCase().charAt(0);
			String userName = "", password = "";
			if (res == 'y') {
				try {
					System.out.print("Enter the username : ");
					userName = Utilities.bufferedReader.readLine();
					System.out.print("Enter the password : ");
					password = Utilities.bufferedReader.readLine();
				} catch (IOException e) {

					e.printStackTrace();
				}
				Optional<Customers> currentCustomer = sbiOperations.getCustomer(customers, userName, password);
				if (currentCustomer.isPresent()) {
					Customers updatedCustomer;
					updatedCustomer = accountCreator.addAccountToExistingCustomer(currentCustomer.get(),
							currentCustomer.get().getUsername());
					customers.put(currentCustomer.get().getUsername(), updatedCustomer);
				} else {
					System.out.println("Customer not Present, Add new Customer");
				}
			} else if (res == 'n') {
				Customers newCustomer = accountCreator.createFreshCustomer(customers);
				customers.put(newCustomer.getUsername(), newCustomer);
			} else {
				System.out.println("Enter Valid Choice");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void closeAccount(BankAccount account, Customers customer) {
		Customers thisCustomer = customer;
		thisCustomer.deleteAccount(account.getAccountNumber());
		customers.put(customer.getUsername(), thisCustomer);
	}

	public boolean closeAccount(BankAccount account, String userName) {
		boolean willAccountClosed = false;

		if (account.hasPendingLoans()) {
			System.out.println("Clear the Loans before Closing the account");
		} else {
			System.out.println("Are you sure want to Close account Press Y to continue");

			try {
				String choice = bufferedReader.readLine();
				if (choice.equalsIgnoreCase("y")) {
					Customers customer = customers.get(userName);
					closeAccount(account, customer);
					willAccountClosed = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return willAccountClosed;
	}

	@Override
	public BankAccount lendLoan(BankAccount bankAccount) {

		return new LoanFactory().addLoanWithAccount(bankAccount);
	}

	@Override
	public BankAccount payDebt(BankAccount bankAccount) {
		double money = 0;
		List<Long> loanIds = new ArrayList<>(bankAccount.getLoansDetails().keySet());
		int loanIdChoice = 0;
		Map<Long, LoanDetails> loanDetails = bankAccount.getLoansDetails();
		if (loanDetails.isEmpty()) {
			System.out.println("You did not take any loans yet!");

		} else {
			for (int i = 0; i < loanIds.size(); i++) {
				System.out.println((i + 1) + " " + loanDetails.get(loanIds.get(i)));
			}
			System.out.println("Enter your choice");

			try {
				loanIdChoice = Integer.parseInt(bufferedReader.readLine());
			} catch (Exception e) {
				System.out.println("Default set to " + loanDetails.get(loanIds.get(0)));
			}

			if (loanDetails.get(loanIds.get(loanIdChoice - 1)).isDebtCleared()) {
				System.out.println("Already Cleared the debt");
			} else {
				try {
					System.out.println("Enter the money to pay back");
					money = Double.parseDouble(bufferedReader.readLine());
				} catch (Exception e) {
					System.out.println("Default set to 0");
				}
				LoanDetails loan = bankAccount.getSpecificLoan(loanIds.get(loanIdChoice - 1));
				loan.setAmount(loan.getAmount() - money);
				bankAccount.updateLoans(loan);
			}
		}
		return bankAccount;
	}

	@Override
	public void displayLoanDetails(BankAccount bankAccount) {
		bankAccount.printLoanDetails();
	}

	@Override
	public BankAccount depositAmount(BankAccount account) {

		double amount = 0;
		try {
			System.out.println("Enter the amount to deposit");
			amount = Double.parseDouble(bufferedReader.readLine());
			depositAmount(account, amount);
		} catch (InvalidAmountException e) {
			System.out.println("Enter Valid Amount please");
			depositAmount(account);
		} catch (Exception e) {
			System.out.println("Default set to 0");
		}

		return account;
	}

	public BankAccount depositAmount(BankAccount account, double amount) throws InvalidAmountException {
		account.depositMoney(amount);
		System.out.println("Amount deposit Sucessful");
		return account;
	}

	protected BankAccount withdrawMoney(BankAccount account) {
		double amount = 0;
		try {
			System.out.println("Enter the amount to Withdraw");
			amount = Double.parseDouble(bufferedReader.readLine());
		} catch (Exception e) {
			System.out.println("Default set to 0");
		}
		return withdrawMoney(account, amount);
	}

	@Override
	public BankAccount withdrawMoney(BankAccount account, double amount) {
		try {
			System.out.println("Confirm ? Press Y to Continue");
			if (bufferedReader.readLine().equalsIgnoreCase("y")) {
				account.withdrawMoney(amount);
				System.out.println(amount + " Has been successfully Withdraw");
			}

		} catch (InsufficientFundsException | IOException e) {
			System.out.println(e);
		}
		return account;
	}

	@Override
	public BankAccount transferAmount(double amount, BankAccount from, long accountNumber)
			throws RecipientNotFoundException {

		Optional<BankAccount> recipientAccount = sbiOperations.findRecipientBankAccount(accountNumber, customers);
		if (!recipientAccount.isPresent()) {
			throw new RecipientNotFoundException("Transfer Failed: Invalid Account Number");
		} else {
			from = withdrawMoney(from, amount);
			BankAccount recieverAccount = recipientAccount.get();
			try {
				recieverAccount.depositMoney(amount);
			} catch (InvalidAmountException e) {
				System.out.println(e.getMessage());
				System.out.println("Amount did not transfered");
			
			}

			Optional<Customers> reciever = sbiOperations.findRecipient(accountNumber, customers);
			Customers recieverClass = reciever.get();

			customers.put(recieverClass.getUsername(), recieverClass);
			System.out.println("Amount of " + amount + "Rs Transferred Successfully from " + from.getAccountNumber()
					+ " to " + recieverAccount.getAccountNumber());
		}
		return from;

	}

	protected BankAccount transferAmount(BankAccount account)
			throws InsufficientFundsException, RecipientNotFoundException {
		double amount = 0;
		long recieverAccountNumber = 0;
		try {
			System.out.println("Enter the amount you wanted to transfer");
			amount = Double.parseDouble(bufferedReader.readLine());

			if (amount > account.getBalance()) {
				throw new InsufficientFundsException();
			}
			System.out.println("Enter the account Number of the reciever");
			recieverAccountNumber = Long.parseLong(bufferedReader.readLine());

			account = transferAmount(amount, account, recieverAccountNumber);
		} catch (NumberFormatException | IOException e) {
			System.out.println("Invalid amount");
		}
		return account;
	}

	public void updateUserInfo() {
		String userName = "", password = "";
		try {
			System.out.print("Enter the username : ");
			userName = bufferedReader.readLine();
			System.out.print("Enter the password : ");
			password = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Optional<Customers> customer = sbiOperations.getCustomer(customers, userName, password);
		if (customer.isPresent()) {
			Customers currentCustomer = customer.get();
			byte field = 0;
			while (field != 4) {
				try {

					System.out.println(
							"Which field to update\n1.Phone number\n2.Account Holder Name\n3.Password\n4.exit");

					field = Byte.parseByte(bufferedReader.readLine());
					switch (field) {
					case 1:
						currentCustomer.updatePhoneName();
						break;
					case 2:
						currentCustomer.updateAccountHolderName();
						break;
					case 3:
						currentCustomer.updatePassword();
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Enter Valid number");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
