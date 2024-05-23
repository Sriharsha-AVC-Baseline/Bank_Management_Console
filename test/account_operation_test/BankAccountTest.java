package account_operation_test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.itt.bank.accounts.BankAccount;
import com.itt.bank.accounts.types.SavingsAccount;
import com.itt.exceptions.InsufficientFundsException;
import com.itt.exceptions.InvalidAmountException;

class BankAccountTest {
	BankAccount bankAccount;
	@BeforeEach
	void setUp() throws Exception {
		 bankAccount = new SavingsAccount("DummyName", 10000);
		
	}
	
	@Test
	@DisplayName("Check Remaining Balance Test")
	void checkBalance()
	{
		assertEquals(10000, bankAccount.getBalance());
	}

	@Nested
	@DisplayName("Deposit Test")
	class DepositTest{
		
		@Test
		@DisplayName("Deposit legit  Ammount")
		void depositLegitTest()
		{
			try {
			bankAccount.depositMoney(1000);
			} catch (InvalidAmountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			assertEquals(11000,bankAccount.getBalance() );
		}}
		
		@Test
		@DisplayName("Deposit invalid  Ammount")
		void depositInvalidTest()
		{
			assertThrows(InvalidAmountException.class, () -> bankAccount.depositMoney(0));
			assertThrows(InvalidAmountException.class, () -> bankAccount.depositMoney(0));
		}
	}
	
	@Nested
	class WithdrawalTest{
		
		@Test
		@DisplayName("Withdraw Valid amount")
		void withdrawValidAmount()
		{
			try {
				bankAccount.withdrawMoney(1);
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(9999, bankAccount.getBalance());
		}
		
		
		@Test
		@DisplayName("Withdraw Excess amount")
		void withdrawExcessAmount()
		{
			assertThrows(InsufficientFundsException.class,() -> bankAccount.withdrawMoney(11111));
		}
	}

}
