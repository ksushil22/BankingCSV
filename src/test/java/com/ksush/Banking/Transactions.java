package com.ksush.Banking;


public class Transactions {
	String date;
	String txn_type;
	int account_number;
	float txn_amount;
	float balance;
	String mode;

	public Transactions(String date, String txn_type, int account_number, float txn_amount, float balance, String mode) {
		this.date = date;
		this.txn_type = txn_type;
		this.account_number = account_number;
		this.txn_amount = txn_amount;
		this.balance = balance;
		this.mode = mode;
	}

	public Transactions() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public float getTxn_amount() {
		return txn_amount;
	}

	public void setTxn_amount(float txn_amount) {
		this.txn_amount = txn_amount;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
