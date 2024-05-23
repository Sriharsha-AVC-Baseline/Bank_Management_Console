package com.itt.sbi_bank.utilities;

import java.io.IOException;
import java.util.regex.Pattern;

import com.itt.constantUtilities.Utilities;

public class AccountFactoryOperations {

	public boolean validatePhoneNumber(String phoneNumberInString) {
		boolean isRightPhoneNumber = false;
		if (Pattern.matches("\\d{10}", phoneNumberInString)) {
			isRightPhoneNumber = true;
		}
		return isRightPhoneNumber;
	}

	public double scanDeposit() {
		boolean isCorrect = false;
		double deposit = 0;
		while (!isCorrect)
			try {
				deposit = Double.parseDouble(Utilities.bufferedReader.readLine());
				isCorrect = true;
			} catch (NumberFormatException e) {
				System.out.println("Enter Valid amount");
			} catch(IOException e)
		{
				System.out.println(e);
		}
			
		return deposit;
	}

	public char scanAccountTypeChoice() {
		char choice = 0;
		AccountTypes[] accountTypes = AccountTypes.values();
		for (int i = 0; i < accountTypes.length; i++) {
			System.out.println((i + 1) + " " + accountTypes[i]);
		}
		System.out.println(accountTypes[0] + " (Default)");

		try {
			choice = (Utilities.bufferedReader.readLine().charAt(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return choice;
	}
}
