package javabank;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSystem {

	static Scanner in = new Scanner(System.in);
	static ArrayList<BankAccount> bankAccounts= new ArrayList<>();

	public static void main(String[] args) {

		String choice = " "; boolean isRunning = true;
		while (isRunning) {
			System.out.println("=== Bank Menu ===");
			System.out.println("1. Create Account");
			System.out.println("2. View All Accounts");
			System.out.println("3. Check Balance");
			System.out.println("4. Deposit");
			System.out.println("5. Withdraw");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");
				choice = in.next();

			switch(choice) {
				case "1":
					boolean result = createAccount();
					if (result) System.out.println("Account created successfully!");
					else System.out.println("[ERROR] Please enter a valid input. Try again.");
					break;
				case "2":
					viewAllAccounts();
					break;
				case "3":
					result = viewBalance();
					break;
				case "4":
					result = depositMoneyTo();
					if (result) System.out.println("Deposit transaction successful!");
					else System.out.println("Deposit failed. Try again.");
					break;
				case "5":
					result = withdrawMoneyFrom();
					if (result) System.out.println("Withdraw transaction successful!");
					else System.out.println("Withdrawal failed. Try again.");
					break;
				case "0":
					isRunning = false;
					break;
				default:
					break;
			}

			System.out.println();
		}
	}

	//	---	METHODS
	private static boolean createAccount() {

		String accountName, accountNumber, initialDepositChoice;

		System.out.print("Enter Account Number: ");
			accountNumber = in.next();
		if (!isValidAccountNumber(accountNumber)) return false;
		for (BankAccount account: bankAccounts) {
			if (accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
				System.out.println("Account already exists.");
				return false;
			}
		}

		System.out.print("Enter Holder Name: ");
			accountName = in.next();

		double initialDepositAmount;
		while(true) {
			System.out.print("Initial deposit? (yes/no): "); initialDepositChoice = in.next();
			if(initialDepositChoice.equalsIgnoreCase("yes")) {
				while(true) {
					try {
						System.out.print("Enter initial deposit amount: ");
						initialDepositAmount = Double.parseDouble(in.next());
						if (initialDepositAmount >= 0) {
							BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDepositAmount);
							bankAccounts.add(newAccount);
							return true;
						}
					} catch (NumberFormatException e) {
						return false;
					}
				}
			} else if (initialDepositChoice.equalsIgnoreCase("no")) {
				BankAccount newAccount = new BankAccount(accountNumber, accountName);
				bankAccounts.add(newAccount);
				return true;
			}
		}
	}

	private static boolean withdrawMoneyFrom() {
		System.out.println("");
		System.out.println("=== Withdrawal ===");
		System.out.print("Enter account number: ");
		String accountNumber = in.next();
		for (BankAccount account: bankAccounts) {
			if (accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
				System.out.print("Enter amount to withdraw: ");
				try {
					boolean result = account.withdraw(Double.parseDouble(in.next()));
					if (result) return true;
					else return false;
				} catch (NumberFormatException e) {
					System.out.println("Invalid amount.");
					return false;
				}
			}
		}
		System.out.println("Account doesn't exist.");
		return false;
	}

	private static boolean depositMoneyTo() {
		System.out.println();
		System.out.println("=== Deposit ===");
		System.out.print("Enter account number: ");
		String accountNumber = in.next();
		for (BankAccount account: bankAccounts) {
			if (accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
				System.out.print("Enter amount to deposit: ");
				try {
					account.deposit(Double.parseDouble(in.next()));
					return true;
				} catch (NumberFormatException e) {
					System.out.println("Invalid amount.");
					return false;
				}
			}
		}
		System.out.println("Account doesn't exist.");
		return false;
	}

	private static void viewAllAccounts() {
		if (!bankAccounts.isEmpty()) {
			System.out.println("");
			System.out.println("=== View All Accounts ===");
			for (int i = 0; i < bankAccounts.size(); i++) {
				bankAccounts.get(i).displayInformation();
				System.out.println("");
			}
			System.out.println("===================== END");
		} else System.out.println("No accounts found.");
	}

	private static boolean viewBalance() {
		System.out.println("");
		System.out.println("=== Account Balance ===");
		System.out.print("Enter account number: ");
			String accountNumber = in.next();
		for (BankAccount account: bankAccounts) {
			if (accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
				System.out.printf("Account: %s [%s]\n", account.getAccountName(), account.getAccountNumber());
				System.out.printf("Available Balance: %f\n", account.getAvailableBalance());
				return true;
			}
		}
		System.out.println("Account doesn't exist.");
		return false;
	}

	//	--- CHECKERS
	private static boolean isValidAccountNumber(String accountNumber) {
		return accountNumber != null && accountNumber.matches("-?\\d+(\\.\\d+)?([Ee][+-]?\\d+)?");
	}

}
