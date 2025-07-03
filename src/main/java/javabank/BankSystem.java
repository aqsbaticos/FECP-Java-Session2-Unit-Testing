package javabank;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

	static Scanner in = new Scanner(System.in);
	ArrayList<BankAccount> bankAccounts= new ArrayList<>();

	public static void main(String[] args) {

		String choice = " "; boolean isRunning = true;
		while (isRunning) {
			System.out.println("=== Bank Menu ===");
			System.out.println("1. Create Account");
			System.out.println("2. View All Accounts");
			System.out.println("3. Check Balance");
			System.out.println("4. Deposit");
			System.out.println("5. Withdraw");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");
				choice = in.next();

			switch(choice) {
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				case "6":
					isRunning = false;
					break;
				default:
					break;
			}

			System.out.println();
		}
	}
}
