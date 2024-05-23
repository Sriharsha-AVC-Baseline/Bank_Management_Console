package com.itt.sbi_bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.Customers;
import com.itt.constantUtilities.Utilities;
import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.RecipientNotFoundException;

public class SbiBank extends AbstractSbiBank {

	private SbiOperations sbiOperations = new SbiOperations();
	private BufferedReader bufferedReader = Utilities.bufferedReader;
	private Map<String, Customers> customers = getCustomers();

	@Override
	public void login() {
		String userName = "", password = "";
		try {
			System.out.print("Enter the username : ");
			userName = Utilities.bufferedReader.readLine();
			System.out.print("Enter the password : ");
			password = Utilities.bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Optional<Customers> customer = sbiOperations.getCustomer(customers, userName, password);
		BankAccount account = null;
		if (!customer.isPresent()) {
			System.out.println("Invalid Credentials!\n");
		} else {
			if(customer.get().getAccounts().isEmpty())
			{
				System.out.println("You don't Have any accounts created here!!!");
				return;
			}
			System.out.println("Enter the Account to log in");
			int accountChoice = 0;
			List<Long> accountNumbers = new ArrayList<>(customer.get().getAccounts().keySet());
			
			for (int i = 0; i < accountNumbers.size(); i++) {
				System.out.println((i + 1) + " " + accountNumbers.get(i));
			}
			try {
				accountChoice = Integer.parseInt(bufferedReader.readLine()) - 1;
				if(accountChoice >= accountNumbers.size())
				{
					throw new ArrayIndexOutOfBoundsException();
				}
			}
			catch(ArrayIndexOutOfBoundsException arrayException)
			{
				System.out.println("Invalid choice,"+" Default set to " + accountNumbers.get(0));
				accountChoice = 0;
			}
			catch (Exception exception) {
				System.out.println("Default set to " + accountNumbers.get(0));
			}

			boolean logout = false,closeAccount = false;
			byte choice = 0;
			while (!logout) {
				account = customer.get().getAccounts().get(accountNumbers.get(accountChoice));
				System.out.println(
						"1.Deposit\n2.Withdraw\n3.Get Loan\n4.pay Dept\n5.show Transactions\n6.Show balance\n7.Show Loans\n8.Transfer Money\n9.Close Account\nAny other number to logout");
				try {
					choice = Byte.parseByte(bufferedReader.readLine());
					switch (choice) {
					case 1:
						account = depositAmount(account);
						break;
					case 2:
						account = withdrawMoney(account);
						break;
					case 3:
						account = lendLoan(account);
						break;
					case 4:
						account = payDebt(account);
						break;
					case 5:
						account.showTransactions();
						break;
					case 6:
						account.showBalance();
						break;
					case 7:
						account.printLoanDetails();
						break;
					case 8:
						account = transferAmount(account);
						break;
					case 9:
						closeAccount = closeAccount(account,userName);
						logout = true;
						break;
					default:
						logout = true;
						break;
					}

				} catch (InsufficientFundsException | RecipientNotFoundException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception e2) {
					System.out.println(e2);
					System.out.println("Invalid Choice");
				} 
				if(!closeAccount)
				{
					setCustomers(customers);
					customer.get().updateAccount(account.getAccountNumber(), account);
					customers.put(customer.get().getUsername(), customer.get());
				}
			}

		}

	}
}
