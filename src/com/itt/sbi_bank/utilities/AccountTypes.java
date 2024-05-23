package com.itt.sbi_bank.utilities;

public enum AccountTypes {
	
	SAVINGS("Savings Account"),CURRENT("Current Accounr"),BUSINESS("Business Account"),EDUCATION("Education Account");
	private String name;
	
	AccountTypes(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		
		return this.name;
	}

}
