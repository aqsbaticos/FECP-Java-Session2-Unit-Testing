package javabank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	void tearDown() {
		System.setOut(originalOut);
	}

	// --- Constructor Tests ---

	@Test
	void constructor_twoArgs_shouldInitializeWithZeroBalance() {

		String accountNumber = "12345";
		String accountName = "John Doe";

		BankAccount account = new BankAccount(accountNumber, accountName);

		assertNotNull(account, "BankAccount object should not be null");
		assertEquals(accountNumber, account.getAccountNumber(), "Account number should be set correctly");
		assertEquals(accountName, account.getAccountName(), "Account name should be set correctly");
		assertEquals(0.0, account.getAvailableBalance(), 0.001, "Initial balance should be 0.0");
	}

	@Test
	void constructor_threeArgs_shouldInitializeWithDeposit() {

		String accountNumber = "67890";
		String accountName = "Jane Smith";
		double initialDeposit = 500.75;

		BankAccount account = new BankAccount(accountNumber, accountName, initialDeposit);

		assertNotNull(account, "BankAccount object should not be null");
		assertEquals(accountNumber, account.getAccountNumber(), "Account number should be set correctly");
		assertEquals(accountName, account.getAccountName(), "Account name should be set correctly");
		assertEquals(initialDeposit, account.getAvailableBalance(), 0.001, "Initial balance should match deposit");
	}

	@Test
	void constructor_twoArgs_shouldHandleEmptyAccountName() {

		String accountNumber = "XYZ123";
		String accountName = "";

		BankAccount account = new BankAccount(accountNumber, accountName);

		assertEquals(accountName, account.getAccountName(), "Account name should be an empty string");
	}

	@Test
	void constructor_threeArgs_shouldHandleZeroDeposit() {

		String accountNumber = "DEP000";
		String accountName = "Zero Balance";
		double initialDeposit = 0.0;

		BankAccount account = new BankAccount(accountNumber, accountName, initialDeposit);

		assertEquals(initialDeposit, account.getAvailableBalance(), 0.001, "Initial balance should be 0.0");
	}

	// --- Getter Tests ---

	@Test
	void getAccountName_shouldReturnCorrectName() {
		BankAccount account = new BankAccount("001", "Alice");
		assertEquals("Alice", account.getAccountName(), "getAccountName should return 'Alice'");
	}

	@Test
	void getAccountNumber_shouldReturnCorrectNumber() {
		BankAccount account = new BankAccount("002", "Bob");
		assertEquals("002", account.getAccountNumber(), "getAccountNumber should return '002'");
	}

	@Test
	void getAvailableBalance_shouldReturnCorrectBalance() {
		BankAccount account = new BankAccount("003", "Charlie", 750.25);
		assertEquals(750.25, account.getAvailableBalance(), 0.001, "getAvailableBalance should return 750.25");
	}

	// --- Setter Tests ---

	@Test
	void setAccountName_shouldUpdateName() {

		BankAccount account = new BankAccount("004", "Old Name");
		String newName = "New Name";

		account.setAccountName(newName);

		assertEquals(newName, account.getAccountName(), "Account name should be updated to 'New Name'");
	}

	@Test
	void setAccountNumber_shouldUpdateNumber() {

		BankAccount account = new BankAccount("005", "David");
		String newNumber = "54321";

		account.setAccountNumber(newNumber);

		assertEquals(newNumber, account.getAccountNumber(), "Account number should be updated to '54321'");
	}

	@Test
	void setAvailableBalance_shouldUpdateBalance() {

		BankAccount account = new BankAccount("006", "Eve", 100.0);
		double newBalance = 250.0;

		account.setAvailableBalance(newBalance);

		assertEquals(newBalance, account.getAvailableBalance(), 0.001, "Available balance should be updated to 250.0");
	}

	// --- Withdraw Method Tests ---

	@Test
	void withdraw_validAmount_shouldReduceBalanceAndReturnTrue() {

		BankAccount account = new BankAccount("W001", "Frank", 1000.0);
		double amountToWithdraw = 250.0;
		double expectedBalance = 750.0;

		boolean result = account.withdraw(amountToWithdraw);

		assertTrue(result, "Withdrawal should return true for success");
		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should be reduced correctly");
	}

	@Test
	void withdraw_insufficientBalance_shouldNotReduceBalanceAndReturnFalse() {

		BankAccount account = new BankAccount("W002", "Grace", 100.0);
		double amountToWithdraw = 150.0; // More than available
		double expectedBalance = 100.0; // Balance should remain unchanged

		boolean result = account.withdraw(amountToWithdraw);

		assertFalse(result, "Withdrawal should return false for insufficient funds");
		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should remain unchanged");
	}

	@Test
	void withdraw_exactBalance_shouldReduceBalanceToZeroAndReturnTrue() {

		BankAccount account = new BankAccount("W003", "Heidi", 500.0);
		double amountToWithdraw = 500.0;
		double expectedBalance = 0.0;

		boolean result = account.withdraw(amountToWithdraw);

		assertTrue(result, "Withdrawal should return true for exact balance withdrawal");
		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should be zero");
	}

	@Test
	void withdraw_zeroAmount_shouldReturnTrueAndNotChangeBalance() {

		BankAccount account = new BankAccount("W004", "Ivan", 200.0);
		double amountToWithdraw = 0.0;
		double expectedBalance = 200.0;

		boolean result = account.withdraw(amountToWithdraw);

		assertTrue(result, "Withdrawal of zero should return true"); // Current implementation allows this
		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should remain unchanged for zero withdrawal");

	}

	@Test
	void withdraw_negativeAmount_shouldReturnTrueAndIncreaseBalance() {

		BankAccount account = new BankAccount("W005", "Judy", 100.0);
		double amountToWithdraw = -50.0; // This effectively acts as a deposit
		double expectedBalance = 150.0; // 100 - (-50) = 150

		boolean result = account.withdraw(amountToWithdraw);

		assertTrue(result, "Withdrawal of negative amount should return true (current behavior)");
		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should increase when withdrawing negative amount (current behavior)");
	}


	// --- Deposit Method Tests ---

	@Test
	void deposit_positiveAmount_shouldIncreaseBalance() {

		BankAccount account = new BankAccount("D001", "Kyle", 50.0);
		double amountToDeposit = 100.0;
		double expectedBalance = 150.0;

		account.deposit(amountToDeposit);

		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should increase correctly after deposit");
	}

	@Test
	void deposit_zeroAmount_shouldNotChangeBalance() {

		BankAccount account = new BankAccount("D002", "Liam", 200.0);
		double amountToDeposit = 0.0;
		double expectedBalance = 200.0;

		account.deposit(amountToDeposit);

		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should remain unchanged for zero deposit");
	}

	@Test
	void deposit_negativeAmount_shouldReduceBalance() {

		BankAccount account = new BankAccount("D003", "Mia", 100.0);
		double amountToDeposit = -25.0; // This effectively acts as a withdrawal
		double expectedBalance = 75.0; // 100 + (-25) = 75

		account.deposit(amountToDeposit);

		assertEquals(expectedBalance, account.getAvailableBalance(), 0.001, "Balance should decrease when depositing negative amount (current behavior)");
	}

	// --- displayInformation Method Test ---

	@Test
	void displayInformation_shouldPrintCorrectDetails() {

		String accountNumber = "INFO01";
		String accountName = "Nora";
		double balance = 999.99;
		BankAccount account = new BankAccount(accountNumber, accountName, balance);

		account.displayInformation();

		String expectedOutput = String.format("Account Number: %s\nHolder Name: %s\nAvailable Balance: %.6f\n",
				accountNumber, accountName, balance);
		assertEquals(expectedOutput, outputStreamCaptor.toString());
		assertTrue(outputStreamCaptor.toString().contains("Account Number: INFO01"), "Output should contain account number");
		assertTrue(outputStreamCaptor.toString().contains("Holder Name: Nora"), "Output should contain account name");
		assertTrue(outputStreamCaptor.toString().contains("Available Balance: 999.99"), "Output should contain available balance (formatted)");
	}
}