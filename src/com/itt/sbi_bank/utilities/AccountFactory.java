package com.itt.sbi_bank.utilities;


import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.types.BusinessAccount;
import com.itt.bank.accounts.types.CurrentAccount;
import com.itt.bank.accounts.types.SavingsAccount;
import com.itt.sbi_bank.accounts.EducationAccount;

public class AccountFactory {

	 BankAccount openAccount(String userName, String holderName,double deposit,char choice) {
		BankAccount bankAccount = null;
		AccountTypes[] accountTypes = AccountTypes.values();
		for (int i = 0; i < accountTypes.length; i++) {
			System.out.println((i + 1) + " " + accountTypes[i]);
		}
		System.out.println(accountTypes[0] + " (Default)");

			bankAccount = new SavingsAccount(holderName, deposit);
			switch (choice) {
			case '1':
				break;
			case '2':
				bankAccount = new CurrentAccount(holderName, deposit);
				break;
			case '3':
				bankAccount = new BusinessAccount(holderName, deposit);
				break;
			case '4':
				bankAccount = new EducationAccount(holderName, deposit);
				break;
			default:
				System.out.println("Default Set to Savings account");
				break;
			}
			System.out.println("Your Bank account has been created successfully!");
			System.out.println("Acc No : "+bankAccount.getAccountNumber());
			System.out.println("Acc Holder Name : "+holderName);
			System.out.println("Acc Type : "+bankAccount.getAccountType()+"\n");
		
		return bankAccount;
	}

}
