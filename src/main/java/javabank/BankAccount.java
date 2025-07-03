package javabank;

public class BankAccount {

	private String accountNumber;
	private String accountName;
	private double availableBalance;

	public BankAccount(String accountNumber, String accountName) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.availableBalance = 0;
	}

	public BankAccount(String accountNumber, String accountName, double deposit) {
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.availableBalance = deposit;
	}

	//	GETTERS
	public String getAccountName() { return accountName; }
	public String getAccountNumber() { return accountNumber; }
	public double getAvailableBalance() { return availableBalance; }

	//	SETTERS
	public void setAccountName(String newAccountName) { this.accountName = newAccountName; }
	public void setAccountNumber(String newAccountNumber) { this.accountNumber = newAccountNumber; }

//	public void withdraw() {
//
//	}

//	public void deposit() {
//
//	}

	public void displayInformation() {
		System.out.printf("Account Number: %s\n", this.accountNumber);
		System.out.printf("Holder Name: %s\n", this.accountName);
		System.out.printf("Available Balance: %f\n", this.availableBalance);
	}

//	public String getAccountNumber() {
//		return this.accountNumber;
//	}

}
