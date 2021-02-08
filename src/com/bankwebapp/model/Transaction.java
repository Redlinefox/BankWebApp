package com.bankwebapp.model;

import java.sql.Timestamp;

public class Transaction {

	private Integer trans_id;
	private String type;
	private Integer account1;
	private Integer account2;
	private Double amount;
	private Timestamp time;
	
	public Transaction() {}
	
	public Transaction(Integer trans_id, String type, Integer account1, Integer account2, Double amount, Timestamp time) {
		this.trans_id = trans_id;
		this.type = type;
		this.account1 = account1;
		this.account2 = account2;
		this.amount = amount;
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAccount1() {
		return account1;
	}

	public void setAccount1(Integer account1) {
		this.account1 = account1;
	}

	public int getAccount2() {
		return account2;
	}

	public void setAccount2(Integer account2) {
		this.account2 = account2;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}
}
