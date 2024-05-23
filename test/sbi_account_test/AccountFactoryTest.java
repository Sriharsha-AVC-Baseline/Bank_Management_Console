package sbi_account_test;

import org.junit.jupiter.api.*;

import com.itt.sbi_bank.utilities.AccountFactory;

public class AccountFactoryTest {
	
	private AccountFactory accountFactory;
	
	@BeforeEach
	public void init()
	{
		accountFactory = new AccountFactory();
	}

}
