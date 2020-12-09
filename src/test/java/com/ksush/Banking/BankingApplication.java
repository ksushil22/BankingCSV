package com.ksush.Banking;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BankingApplication {
	public static String fileAccount = "files/account.csv";
	public static String fileTransactions = "files/account_statement.csv";
	public static int accNo = 0;


	private static Account getAccount(int accNo) {
		Account account = new Account();
		try{
			String line[];
			CSVReader reader;
			reader = new CSVReader(new FileReader(fileAccount), ',', '"', 1);
			while ((line = reader.readNext()) != null) {
				if (line.length > 0 && Integer.parseInt(line[0]) == accNo) {
					account = new Account(Integer.parseInt(line[0]), line[1], Float.parseFloat(line[2]));
					break;
				}
			}


		}catch ( Exception e ){

		}
		return account;
	}


	public static void writeTransaction(Transactions transaction){
		try {
			String[] line;
			CSVReader reader1 = new CSVReader(new FileReader(fileTransactions), ',','"', 1);
			List<Transactions> lTransactions = new LinkedList<>();
			while((line = reader1.readNext()) != null){
				if(line.length > 0){
					Transactions transac = new Transactions(line[0],line[1],Integer.parseInt(line[2]),Float.parseFloat(line[3]),Float.parseFloat(line[4]),line[5]);
					lTransactions.add(transac);
				}
			}
			lTransactions.add(transaction);
			CSVWriter br1 = new CSVWriter(new FileWriter(fileTransactions));
			String[] gh = "date,txn_type,account_number,txn_amount,balance,mode".split(",");
			br1.writeNext(gh);
			for(int i = 0; i < lTransactions.size(); i++){
				DecimalFormat dfd = new DecimalFormat(".##");
				float bl = Float.valueOf(dfd.format(lTransactions.get(i).getTxn_amount()));
				float bld = Float.valueOf(dfd.format(lTransactions.get(i).getBalance()));
				String[] dat = {lTransactions.get(i).getDate(), lTransactions.get(i).getTxn_type(), String.valueOf(lTransactions.get(i).getAccount_number()), String.valueOf(bl), String.valueOf(bld), lTransactions.get(i).getMode()};
				br1.writeNext(dat);
			}

			br1.close();
		}catch( Exception e ){
		}
	}
	public static void writeAccount(Account account){
		List<Account> lAccount = new ArrayList<>();
		try {
			String line[];
			CSVReader reader;
			reader = new CSVReader(new FileReader(fileAccount), ',', '"', 1);
			while ((line = reader.readNext()) != null) {
				if (line.length > 0) {
					Account acc = new Account(Integer.parseInt(line[0]), line[1], Float.parseFloat(line[2]));
					accNo = Integer.parseInt(line[0]);
					lAccount.add(acc);
				}
			}
			accNo++;
			account.setAccount_number(accNo);
			lAccount.add(account);
			CSVWriter br;
			br = new CSVWriter(new FileWriter(fileAccount));
			String[] d = "account_number,name,balance".split(",");
			br.writeNext(d);
			for (int i = 0; i < lAccount.size(); i++) {
				DecimalFormat dfd = new DecimalFormat(".##");
				float bl = Float.valueOf(dfd.format(lAccount.get(i).getBalance()));
				String[] data = {String.valueOf(lAccount.get(i).getAccount_number()), lAccount.get(i).getName(), String.valueOf(bl)};
				br.writeNext(data);
			}
			br.close();

		}catch (Exception e) {
		}
	}


	private static void updateAccount(int accntNo, float balance) {
		List<Account> lAccount = new ArrayList<>();
		try {
			String line[];
			CSVReader reader;
			reader = new CSVReader(new FileReader(fileAccount), ',', '"', 1);
			while ((line = reader.readNext()) != null) {
				if (line.length > 0) {
					Account acc = new Account(Integer.parseInt(line[0]), line[1], Float.parseFloat(line[2]));
					if(Integer.parseInt(line[0]) == accntNo)
						acc.setBalance(balance);
					accNo = Integer.parseInt(line[0]);
					lAccount.add(acc);
				}
			}
			CSVWriter br;
			br = new CSVWriter(new FileWriter(fileAccount));
			String[] d = "account_number,name,balance".split(",");
			br.writeNext(d);
			for (int i = 0; i < lAccount.size(); i++) {
				DecimalFormat dfd = new DecimalFormat(".##");
				float bl = Float.valueOf(dfd.format(lAccount.get(i).getBalance()));
				String[] data = {String.valueOf(lAccount.get(i).getAccount_number()), lAccount.get(i).getName(), String.valueOf(bl)};
				br.writeNext(data);
			}
			br.close();

		}catch (Exception e) {
		}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner sSc = new Scanner(System.in);
		while(true){
			System.out.println("Enter: \n1. Create new account\n2. Make a transaction\n3. Check balance.\n4. exit");
			int n = sc.nextInt();
			if( n == 4 )
				break;
			else if( n == 1 ){
				System.out.print("Your name: ");
				String name = sSc.nextLine();
				System.out.print("Enter Amount you want to deposit: ");
				float amount = sc.nextInt();
				System.out.println("Enter:3\n1. Cash\n2. Online");
				int mode = sc.nextInt();
				String md;
				if(mode == 1)
					md = "cash";
				else if(mode == 2)
					md = "online";
				else {
					System.out.println("Invalid Input\nTry again...");
					continue;
				}

				Account account = new Account(accNo, name, amount);
				writeAccount(account);


				Date date = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dt = ft.format(date);
				Transactions transaction = new Transactions(dt, "Cr",accNo, amount, amount, md);
				writeTransaction(transaction);
				System.out.println("Account Created Successfully");

			}
			else if( n == 2 ){
				System.out.println("Enter: \n1. Credit\n2. Debit");
				int txType = sc.nextInt();
				String mode;
				int mMode;
				int flag = 0;
				if(txType == 1){
					mode = "Cr";
					System.out.println("Enter: \n1. Cash\n2. Online");
					mMode = sc.nextInt();
					if(mMode >= 4 || mMode < 0)
						flag = 1;

				}
				else if(txType == 2){
					mode = "Dr";
					System.out.println("Enter: \n1. Cash\n2. Online\n3. ATM");
					mMode = sc.nextInt();
					if(mMode > 3 || mMode < 0)
						flag = 1;
				}
				else{
					break;
				}
				if(flag == 1)
					break;

				String finalMode;
				if( mMode == 1 )
					finalMode = "cash";
				else if( mMode == 2 )
					finalMode = "online";
				else
					finalMode = "atm";

				System.out.println("Enter account number: ");
				int accNo = sc.nextInt();
				System.out.print("Enter Amount: ");
				float amount = sc.nextFloat();

				Account acc = getAccount(accNo);
				float balance = acc.getBalance();
				int accntNo = acc.getAccount_number();

				Date date = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dt = ft.format(date);

				Transactions transactions = new Transactions();
				transactions.setAccount_number(accntNo);
				transactions.setDate(dt);
				transactions.setTxn_type(mode);
				transactions.setTxn_amount(amount);
				if(amount > balance && txType == 2)
					System.out.println("Insufficient Balance");
				else{
					if(txType == 1){
						balance += amount;
					}
					else{
						balance -= amount;
					}
				}
				transactions.setBalance(balance);
				transactions.setMode(finalMode);
				writeTransaction(transactions);

				updateAccount(accntNo, balance);

				System.out.println("Transaction Complete");

			}
			else if( n == 3 ){
				System.out.print("Enter your Account Number: ");
				int accNumber = sc.nextInt();

				Account account = getAccount(accNumber);
				System.out.printf("Your current balance is: Rs. %.2f",account.getBalance());
				System.out.println();
			}
			else
				System.out.println(" Invalid Input ");
		}
		sc.close();


	}

}
