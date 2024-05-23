package com.itt.bank.accounts;

import java.util.Date;

import com.itt.bank.constants.TransactionType;

public class Transactions {
	@FunctionalInterface
	interface TransactionPrinter{
		String printTransaction(int id,double money,Date date,double balance, TransactionType type);
	}

	private int transactionId;
	private double transactMoney;
	private Date transactionDate;
	private double balance;
	private TransactionType type;

	public Transactions(double money, double balance, Date transactionDate, TransactionType type) {
		this.transactionId = this.hashCode();
		this.transactMoney = money;
		this.transactionDate = transactionDate;
		this.balance = balance;
		this.type = type;
	}

	@Override
	public String toString() {
		
		TransactionPrinter printer = (id, money, date, balance, type) ->
		"Transactions [transactionId=" + transactionId + ", transactMoney=" + transactMoney
		+ ", transactionDate=" + transactionDate + ", balance=" + balance + ", type=" + type + "]";
		
		return printer.printTransaction(transactionId, transactMoney, transactionDate, balance, type);
	}

}
