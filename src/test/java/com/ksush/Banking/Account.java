package com.ksush.Banking;

public class Account {
	int account_number;
	String name;
	float balance;

	public Account(int account_number, String name, float balance) {
		this.account_number = account_number;
		this.name = name;
		this.balance = balance;
	}

	public Account() {

	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
