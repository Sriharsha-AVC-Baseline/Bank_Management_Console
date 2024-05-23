package com.itt.bank;

import java.util.LinkedHashMap;
import java.util.Map;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.Customers;
import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.InvalidCredentialsException;
import com.itt.exceptions.RecipientNotFoundException;

public abstract class Bank implements ILoan, Deposit, Withdraw {
	
	private String bankName;
	private Map<String, Customers> customers = new LinkedHashMap<>();
	
	public Bank(String bankName)
	{
		this.bankName = bankName;
	}

	public abstract void createAccount();

	public abstract void login() throws InvalidCredentialsException;

	public abstract void closeAccount(BankAccount account,Customers customer) throws InvalidCredentialsException;
	
	public abstract BankAccount transferAmount(double amount,BankAccount from,long accountNumber) throws RecipientNotFoundException,InsufficientFundsException;

	public String getBankName() {
		return bankName;
	}

	public Map<String, Customers> getCustomers() {
		return customers;
	}

	public void setCustomers(Map<String, Customers> customers) {
		this.customers = customers;
	}
	
	

}
