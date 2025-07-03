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
					else System.out.println("[ERROR] Please enter a valid number. Try again.");
					break;
				case "2":
					viewAllAccounts();
					break;
				case "3":
					viewBalance();
					break;
				case "4":
					break;
				case "5":
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

	private static void viewAllAccounts() {
		System.out.println("");
		System.out.println("=== View All Accounts ===");
		for (int i=0; i<bankAccounts.size(); i++) {
			bankAccounts.get(i).displayInformation();
			System.out.println("");
		}
		System.out.println("===================== END");
	}

	private static void viewBalance() {
		System.out.println("");
		System.out.println("=== Account Balance ===");
		System.out.print("Enter account number: ");
			String accountNumber = in.next();
		for (BankAccount account: bankAccounts) {
			if (accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
				System.out.printf("Account: %s [%s]\n", account.getAccountName(), account.getAccountNumber());
				System.out.printf("Available Balance: %f\n", account.getAvailableBalance());
			}
		}
	}

	//	--- CHECKERS
	private static boolean isValidAccountNumber(String accountNumber) {
		return accountNumber != null && accountNumber.matches("-?\\d+(\\.\\d+)?([Ee][+-]?\\d+)?");
	}
}
