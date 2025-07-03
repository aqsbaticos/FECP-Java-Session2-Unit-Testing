package javabank;

import java.util.ArrayList;
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
//					int result = createAccount();
					break;
				case "2":
					viewAllAccounts();
					break;
				case "3":
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

//	private static int createAccount() {
//		String accountName, accountNumber, initialDepositChoice;
//		System.out.print("Enter Account Number: "); accountNumber = in.next();
//		System.out.print("Enter Holder Name: "); accountName = in.next();
//
//		double initialDepositAmount; boolean checkInitialDepositInput = true;
//		while(checkInitialDepositInput) {
//			System.out.print("Initial deposit? (yes/no): "); initialDepositChoice = in.next();
//			if(initialDepositChoice.toLowerCase().equals("yes")) {
//				System.out.print("Enter initial deposit amount: "); initialDepositAmount = in.nextDouble();
//				if (initialDepositAmount >= 0) {
//					BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDepositAmount);
//					bankAccounts.add(newAccount);
//					System.out.println("Account created successfully!");
//				}
//			} else if (initialDepositChoice.toLowerCase().equals("no")) {
//				checkInitialDepositInput = false;
//			}
//		}
//	}

	private static void viewAllAccounts() {
		System.out.println("=== View All Accounts ===");
		for (int i=0; i<bankAccounts.size(); i++) {
			bankAccounts.get(i).displayInformation();
			System.out.println("");
		}
		System.out.println("===================== END");
	}
}
