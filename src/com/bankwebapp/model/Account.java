package com.bankwebapp.model;

import java.sql.Timestamp;

public class Account {

	private Integer accountID;
	private String accountType;
	private Double balance;
	private Timestamp last_update;
	private Boolean approved;
	private Timestamp approved_date;
	private Integer approved_by;
	private Integer ownerID;
	private String fullName;
	
	public Account() {}
	
	public Account(String accountType, Double balance) {
		this.accountType = accountType;
		this.balance = balance;
	}
	
	public Account(Integer accountID, String accountType, Double balance, Timestamp last_update, Boolean approved, Timestamp approved_date, Integer approved_by, Integer ownerID) {
		this.accountID = accountID;
		this.accountType = accountType;
		this.balance = balance;
		this.last_update = last_update;
		this.approved = approved;
		this.approved_date = approved_date;
		this.approved_by = approved_by;
		this.ownerID = ownerID;	
	}
	
	public Account(Integer accountID, String accountType, Double balance, String name, Boolean approved) {
		this.accountID = accountID;
		this.accountType = accountType;
		this.balance = balance;
		this.fullName = name;
		this.approved = approved;
	}

	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Integer getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
	}

	public Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}

	public Timestamp getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Timestamp approved_date) {
		this.approved_date = approved_date;
	}

	public Integer getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(Integer approved_by) {
		this.approved_by = approved_by;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
