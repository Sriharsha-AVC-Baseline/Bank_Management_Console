package com.itt.sbi_bank.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.LoanDetails;
import com.itt.constantUtilities.Utilities;
import com.itt.sbi_bank.loans.HomeLoan;
import com.itt.sbi_bank.loans.VehicleLoan;

public class LoanFactory {
	private BufferedReader bufferedReader = Utilities.bufferedReader;

	public BankAccount addLoanWithAccount(BankAccount bankAccount) {

		Optional<LoanDetails> loanDetails = getLoan();
		if (loanDetails.isPresent()) {
			bankAccount.addLoans(loanDetails.get());
		} else {
			System.out.println("No loans added");
		}
		
		return bankAccount;
	}

	private Optional<LoanDetails> getLoan() {
		Optional<LoanDetails> loan = Optional.empty();
		LoanDetails loanDetails = null;
		System.out.println("You need\n1.Home Loans (3.6%)\n2.Vehicle Loans (6.8)\nAny key to get Back");
		double amount = 0;
		int years = 0;
		try {
			char ch = bufferedReader.readLine().charAt(0);

			System.out.println("Enter the amount");
			amount = Double.parseDouble(Utilities.bufferedReader.readLine());
			System.out.println("Enter the number of years");
			years = Integer.parseInt(Utilities.bufferedReader.readLine());
			switch (ch) {
			case '1':
				loanDetails = new HomeLoan(amount, years);
				break;
			case '2':
				loanDetails = new VehicleLoan(amount, years);
				break;
			default:
				break;
			}
			loan = Optional.ofNullable(loanDetails);

			if (loan.isPresent()) {
				System.out.println("Your total amount would be " + loan.get().getAmount());
				System.out.println("Confirm Loan (Y : yes, Any other key to cancel)");
				String confirmation = bufferedReader.readLine();
				if(!confirmation.equalsIgnoreCase("y"))
				{
					loan = Optional.empty();
				}
			}
		} catch (IOException e) {
		}
		
		return loan;
	}

}
