package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public void disp() {
		System.out.print("\nhello "+nom+","+"\naccount number : "+accnum+","+"\nbalance : "+balance+" DH");
		System.out.print("\n--------------------------------------------------------------------");
	}
	public String tostring() {
		return accnum+","+nom+","+balance+","+pass;
	}
	public void setBalance(double balance) {
		this.balance = this.balance + balance;
	}
	public void withdraw(Double withdraw) {
		this.balance = this.balance - withdraw;
	}
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		ArrayList<bank> accounts = new ArrayList<>();
		ArrayList<bank> impo = new ArrayList<>();
		int linecount = 0;
		try(BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){
			while(reader.readLine() != null) {
				linecount++;
			}
		}
		boolean found = false;
		boolean match = false;
		boolean bool = true;
		int acc;
		String nom;
		String pass;
		double bal;
		double with = 0;
		int option;
		int count = 0;
		int accnum = linecount+1;
		double balance = 0;
		int j=0;
		try(BufferedReader read = new BufferedReader(new FileReader("accounts.txt"))){
			String line;
			while((line = read.readLine())!= null) {
				String[] parts = line.split(",");
				if(parts.length == 4) {
				acc = Integer.parseInt(parts[0]);
				nom = parts[1];
				pass = parts[3];
				bal = Double.parseDouble(parts[2]);
				impo.add(new bank(nom,acc,bal,pass));
				}
			}
		}
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
				nom = scan.nextLine();
				System.out.print("create a password : ");
				pass =scan.nextLine();
				accounts.add(count,new bank(nom,accnum,balance,pass));
				System.out.print("\nyour account has been created !");
				System.out.print("\n"+accnum+" is your account number");
				count++;
				try(BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt",true))){
					for(int i=j;i<count;i++) {
						writer.write(accounts.get(i).tostring());
						writer.newLine();
					}
				}
				accnum++;
				j++;
				break;
			case 2:
				System.out.print("\nenter you account number : ");
				acc = scan.nextInt();
				scan.nextLine();
				for(int i=0;i<linecount;i++) {
					if(impo.get(i).accnum == acc) {
						found = true;
					System.out.print("\nenter your password "+impo.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(impo.get(i).pass.equals(psswd)) {
						match = true;
					impo.get(i).disp();
					System.out.print("\nhow much do you wanna deposit : ");
					bal = scan.nextDouble();
					impo.get(i).setBalance(bal);
					System.out.print("\nyour deposit has been succesfully !");
					}
					break;
					}
				}
				if(found && match) {
					System.out.print("\nWelcome !");
				}else{
					System.out.print("\nyour identifiants are incorrect !");
				}
				break;
			case 3:
				System.out.print("\nenter you account number : ");
				acc = scan.nextInt();
				scan.nextLine();
				for(int i=0;i<linecount;i++) {
					if(impo.get(i).accnum == acc) {
					found = true;
					System.out.print("\nenter your password "+impo.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(impo.get(i).pass.equals(psswd)) {
						match = true;
					impo.get(i).disp();
					break;
					}
					}
				}
				if(found && match) {
					System.out.print("\nWelcome !");
				}else{
					System.out.print("\nyour identifiants are incorrect !");
				}
				break;
			case 4:
				System.out.print("\nenter you account number : ");
				acc = scan.nextInt();
				scan.nextLine();
				for(int i=0;i<linecount;i++) {
					if(impo.get(i).accnum == acc) {
						found = true;
					System.out.print("\nenter your password "+impo.get(i).nom+" : ");
					String psswd = scan.nextLine();
					if(impo.get(i).pass.equals(psswd)) {
						match = true;
					impo.get(i).disp();
					if(impo.get(i).balance != 0) {
					System.out.print("\nhow much do you wanna withdraw : ");
					with = scan.nextDouble();
					if(with <= impo.get(i).balance ) {
					impo.get(i).withdraw(with);
					System.out.print("\nyour withdraw has been succesfully !");
					}else {
						System.out.print("\nyou don't have enough money to widthraw");
					}
					}else {
						System.out.print("\nyou don't have enough money to widthraw");
					}
					}
					break;
					}
				}
				if(!found || !match) {
					System.out.print("\nWelcome !");
				}else {
					System.out.print("\nyour idenifiants are incorrect !");
				}
				break;
			case 5:
				bool = false;
				break;
			}
		}
		

	}

}
