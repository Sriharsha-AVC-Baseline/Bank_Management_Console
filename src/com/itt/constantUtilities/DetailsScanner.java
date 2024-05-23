package com.itt.constantUtilities;

import java.io.IOException;

import com.itt.sbi_bank.utilities.AccountFactoryOperations;

public class DetailsScanner {

	public long scanPhoneNumber() {
		String phoneNumberInString = "0000000000";
		long phoneNumber = 0;
		boolean rightPhoneNumber = false;
		int numberOfTurns = 3;
		while (!rightPhoneNumber && numberOfTurns > 0) {
			try {
				System.out.println("Enter the Phone Number :");
				phoneNumberInString = Utilities.bufferedReader.readLine();
				rightPhoneNumber = new AccountFactoryOperations().validatePhoneNumber(phoneNumberInString);
				if (!rightPhoneNumber) {
					numberOfTurns--;
					System.out.println("Enter valid phone number : " + numberOfTurns + " try left");
				}
			} catch (NumberFormatException exception) {
				numberOfTurns--;
				System.out.println("Enter valid phone number : " + numberOfTurns + " try left");
			}
			catch (Exception e) {
				System.out.println(e);
			}

		}
		phoneNumber = Long.parseLong(phoneNumberInString);
		return phoneNumber;
	}
	
	public String scanAccountHolderName()
	{
		System.out.println("Enter the Account holder's name");
		String holderName="";
		try {
			 holderName = Utilities.bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return holderName;
	}
	
	public String scanPassword()
	{
		System.out.println("Enter the Password :");
		String password="";
		try {
			password = Utilities.bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public String scanUserName()
	{
		String userName = "";
		System.out.println("Enter the username :");
		try {
			 userName = Utilities.bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}
	
	
}
