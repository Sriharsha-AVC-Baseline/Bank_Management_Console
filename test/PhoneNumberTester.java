import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.itt.sbi_bank.utilities.AccountFactoryOperations;

class PhoneNumberTester {

	private AccountFactoryOperations accountFactoryOperations;
	
	@BeforeEach
	public void init()
	{
		accountFactoryOperations = new AccountFactoryOperations();
	}
	
	@Test
	@DisplayName("Test Phone number Checker")
	void test() {	
		assertAll(
		() -> assertEquals(true, accountFactoryOperations.validatePhoneNumber("1234567890")),
		() -> assertNotEquals(true, accountFactoryOperations.validatePhoneNumber("1234532467890")),
		() -> assertNotSame(true, accountFactoryOperations.validatePhoneNumber("123456778")),
		() -> assertNotEquals(true, accountFactoryOperations.validatePhoneNumber("@#fds")));
	}

}
