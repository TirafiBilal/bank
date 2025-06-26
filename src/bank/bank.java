package bank;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class bank {
	int accnum;
	double balance;
	String nom, pass;

	public bank(String nom, int accnum, double balance, String pass) {
		this.nom = nom;
		this.accnum = accnum;
		this.balance = balance;
		this.pass = pass;
	}

	public void disp() {
		System.out.print("\nHello " + nom + ","
				+ "\nAccount Number : " + accnum + ","
				+ "\nBalance : " + balance + " DH");
		System.out.print("\n--------------------------------------------------------------------");
	}

	public String tostring() {
		return accnum + "," + nom + "," + balance + "," + pass;
	}

	public void setBalance(double balance) {
		this.balance += balance;
	}

	public void withdraw(Double withdraw) {
		this.balance -= withdraw;
	}

	public static void saveAccounts(ArrayList<bank> accounts) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
			for (bank b : accounts) {
				writer.write(b.tostring());
				writer.newLine();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		ArrayList<bank> impo = new ArrayList<>();

		// Load accounts from file
		try (BufferedReader read = new BufferedReader(new FileReader("accounts.txt"))) {
			String line;
			while ((line = read.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 4) {
					int acc = Integer.parseInt(parts[0]);
					String nom = parts[1];
					double bal = Double.parseDouble(parts[2]);
					String pass = parts[3];
					impo.add(new bank(nom, acc, bal, pass));
				}
			}
		}

		int accnum = impo.size() + 1;
		boolean running = true;

		while (running) {
			System.out.print("\n1- Create Account");
			System.out.print("\n2- Deposit Money");
			System.out.print("\n3- Check Balance");
			System.out.print("\n4- Withdraw Money");
			System.out.print("\n5- Exit");
			System.out.print("\nChoose an option: ");
			int option = scan.nextInt();
			scan.nextLine();

			boolean found = false, match = false;
			int acc;
			String nom, pass;
			double bal, with;

			switch (option) {
				case 1:
					System.out.print("\nWhat is your name: ");
					nom = scan.nextLine();
					System.out.print("Create a password: ");
					pass = scan.nextLine();
					bank newAcc = new bank(nom, accnum, 0.0, pass);
					impo.add(newAcc);
					System.out.print("\nYour account has been created!");
					System.out.print("\n" + accnum + " is your account number");
					accnum++;
					saveAccounts(impo);
					break;

				case 2:
					System.out.print("\nEnter your account number: ");
					acc = scan.nextInt();
					scan.nextLine();
					for (bank b : impo) {
						if (b.accnum == acc) {
							found = true;
							System.out.print("\nEnter your password " + b.nom + ": ");
							String psswd = scan.nextLine();
							if (b.pass.equals(psswd)) {
								match = true;
								b.disp();
								System.out.print("\nHow much do you want to deposit: ");
								bal = scan.nextDouble();
								b.setBalance(bal);
								saveAccounts(impo);
								System.out.print("\nYour deposit has been successful!");
							}
							break;
						}
					}
					if (!found || !match)
						System.out.print("\nYour credentials are incorrect!");
					break;

				case 3:
					System.out.print("\nEnter your account number: ");
					acc = scan.nextInt();
					scan.nextLine();
					for (bank b : impo) {
						if (b.accnum == acc) {
							found = true;
							System.out.print("\nEnter your password " + b.nom + ": ");
							String psswd = scan.nextLine();
							if (b.pass.equals(psswd)) {
								match = true;
								b.disp();
							}
							break;
						}
					}
					if (!found || !match)
						System.out.print("\nYour credentials are incorrect!");
					break;

				case 4:
					System.out.print("\nEnter your account number: ");
					acc = scan.nextInt();
					scan.nextLine();
					for (bank b : impo) {
						if (b.accnum == acc) {
							found = true;
							System.out.print("\nEnter your password " + b.nom + ": ");
							String psswd = scan.nextLine();
							if (b.pass.equals(psswd)) {
								match = true;
								b.disp();
								System.out.print("\nHow much do you want to withdraw: ");
								with = scan.nextDouble();
								if (with <= b.balance) {
									b.withdraw(with);
									saveAccounts(impo);
									System.out.print("\nYour withdrawal has been successful!");
								} else {
									System.out.print("\nYou don't have enough money to withdraw.");
								}
							}
							break;
						}
					}
					if (!found || !match)
						System.out.print("\nYour credentials are incorrect!");
					break;

				case 5:
					running = false;
					break;

				default:
					System.out.print("\nInvalid option. Try again.");
			}
		}
		scan.close();
	}
}
