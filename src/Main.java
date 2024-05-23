import java.io.IOException;
import com.itt.constantUtilities.Utilities;
import com.itt.sbi_bank.SbiBank;

public class Main {

	public static void main(String[] args) {

		SbiBank sbiBank = new SbiBank();
		boolean exitApp = false;
		System.out.println("Welocome to SBI Bank");
		while (!exitApp) {
			System.out.println("1. Create Bank account\n2. login\n3. Update Information\nAny number to Exit");
			try {
				byte choice = Byte.parseByte(Utilities.bufferedReader.readLine());
				switch (choice) {
				case 1:
					sbiBank.createAccount();
					break;
				case 2:
					sbiBank.login();
					break;
				case 3:
					sbiBank.updateUserInfo();
					break;
				default:
					exitApp = true;
					break;
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Enter a Valid choice");
			}
		}
		System.out.println("Thank you!!!");
	}

}
