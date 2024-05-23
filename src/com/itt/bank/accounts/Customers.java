package com.itt.bank.accounts;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

import com.itt.constantUtilities.DetailsScanner;


public class Customers {
	private String accountHolderName;
	private long phoneNumber;
	private String username;
	private String password;
	private Map<Long, BankAccount> accounts = new LinkedHashMap<>();
	
	private BiPredicate<String,String> validateCustomer = (userName,password) -> 
		(this.username.equalsIgnoreCase(userName) && this.password.equals(password)) ? true : false;
	
	private DetailsScanner detailsScanner = new DetailsScanner();

	public Customers(String accountHolderName, String username, String password, long phoneNumber) {
		super();
		this.accountHolderName = accountHolderName;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public Map<Long, BankAccount> getAccounts() {
		return accounts;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void addAccount(BankAccount account) {
		this.accounts.put(account.getAccountNumber(), account);
	}

	public boolean validateCustomer(String username, String password) {
		
		return validateCustomer.test(password, username);
	}

	public void updateAccount(long accountNumber, BankAccount bankAccount) {
		accounts.put(accountNumber, bankAccount);
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}
	
	public void deleteAccount(Long accountNumber)
	{
		accounts.remove(accountNumber);
	}

	public boolean isAccountClosable(long accountNumber) {
		BankAccount bankAccount = accounts.get(accountNumber);
		return bankAccount.hasPendingLoans();
	}
	
	
	public void updatePhoneName()
	{
		phoneNumber = detailsScanner.scanPhoneNumber();
	}
	
	public void updateAccountHolderName()
	{
		accountHolderName = detailsScanner.scanAccountHolderName();
		Set<Long> accountNumbers = accounts.keySet();
		for(long accNumber:accountNumbers)
		{
			BankAccount temp = accounts.get(accNumber);
			temp.setAccountHolderName(accountHolderName);
			accounts.put(temp.getAccountNumber(), temp);
		}
	}
	
	public void updatePassword()
	{
		password = detailsScanner.scanPassword();
	}
	
}
