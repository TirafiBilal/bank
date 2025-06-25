package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class bank {
	int accnum;
	double balance;
	String nom,pass;
	
	public bank(String nom,int accnum,double balance,String pass) {
		this.nom = nom;
		this.accnum = accnum;
		this.balance = balance;
		this.pass = pass;
	}
	
	public bank(int acc, String nom2, double bal) {
		this.accnum = acc;
		this.nom = nom2;
		this.balance = bal;
	}

	public void disp() {
		System.out.print("hello "+nom+","+"\naccount number : "+accnum+","+"\nbalance : "+balance+" DH");
		System.out.print("\n--------------------------------------------------------------------");
	}
	public String tostring() {
		return accnum+","+nom+","+balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void withdraw(Double withdraw) {
		this.balance = this.balance - withdraw;
	}
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		ArrayList<bank> accounts = new ArrayList<>();
		ArrayList<bank> impo = new ArrayList<>();
		boolean bool = true;
		int option;
		int count = 0;
		int accnum = 1;
		double balance = 0;
		while(bool) {
			System.out.print("\n1- create account");
			System.out.print("\n2- deposit money");
			System.out.print("\n3- check balance");
			System.out.print("\n4- withdraw money");
			System.out.print("\n5- exit");
			option = scan.nextInt();
			scan.nextLine();
			switch(option) {
			case 1:
				System.out.print("\nwhat is your name : ");
				String nom = scan.nextLine();
				System.out.print("create a password : ");
				String pass = scan.nextLine();
				accounts.add(count,new bank(nom,accnum,balance,pass));
				System.out.print("\nyour account has been created !");
				System.out.print("\n"+accnum+" is your account number");
				count++;
				accnum++;
				try(BufferedWriter writer = new BufferedWriter(new FileWriter("accounts",true))){
					for(int i=0;i<count;i++) {
						writer.write(accounts.get(i).tostring());
					}
				}
				break;
			case 2:
				System.out.print("\nenter you account number : ");
				int acc = scan.nextInt();
				scan.nextLine();
				try(BufferedReader read = new BufferedReader(new FileReader("accounts.txt"))){
					String line;
					while((line = read.readLine())!= null) {
						String[] parts = line.split(",");
						if(parts.length == 3) {
						acc = Integer.parseInt(parts[0]);
						nom = parts[1];
						double bal = Integer.parseInt(parts[2]);
						impo.add(new bank(acc,nom,bal));
						}
					}
				}
				for(int i=0;i<count;i++) {
					if(impo.get(i).accnum == acc) {
					System.out.print("\nenter your password "+impo.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(impo.get(i).pass.equals(psswd)) {
					impo.get(i).disp();
					System.out.print("\nhow much do you wanna deposit : ");
					Double bal = scan.nextDouble();
					impo.get(i).setBalance(bal);
					System.out.print("\nyour deposit has been succesfully !");
					}
					else {
						System.out.print("\nincorrect password");
					}
					}else {
						System.out.print("there is no account with this number");
					}
				}
				break;
			case 3:
				System.out.print("\nenter you account number : ");
				acc = scan.nextInt();
				scan.nextLine();
				for(int i=0;i<count;i++) {
					if(accounts.get(i).accnum == acc) {
					System.out.print("\nenter your password "+accounts.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(accounts.get(i).pass.equals(psswd)) {
					accounts.get(i).disp();
					}
					else {
						System.out.print("\nincorrect password");
					}
					}else {
						System.out.print("there is no account with this number");
					}
				}
				break;
			case 4:
				System.out.print("\nenter you account number : ");
				acc = scan.nextInt();
				scan.nextLine();
				for(int i=0;i<count;i++) {
					if(accounts.get(i).accnum == acc) {
					System.out.print("\nenter your password "+accounts.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(accounts.get(i).pass.equals(psswd)) {
					accounts.get(i).disp();
					System.out.print("\nhow much do you wanna withdraw : ");
					Double with = scan.nextDouble();
					accounts.get(i).withdraw(with);
					System.out.print("\nyour withdraw has been succesfully !");
					}else {
						System.out.print("\nincorrect password");
					}
					}else {
						System.out.print("there is no account with this number");
					}
				}
				break;
			}
		}
		

	}

}
