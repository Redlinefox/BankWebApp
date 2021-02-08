package com.bankwebapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.bankwebapp.model.Account;
import com.bankwebapp.model.Transaction;

public interface AccountDAO {
	
	void createAccount(int id, String t, double amount) throws SQLException;
	
	List<Account> viewUserAccounts(int id) throws SQLException;

	List<Account> viewAllAccounts();
	void viewUnapproved();
	
	void setApproved(int employee_id, int id) throws SQLException;
	void approveAll(int employee_id) throws SQLException;
	
	void setRejected(int employee_id, int id) throws SQLException;
	
	void deposit(double d, int id) throws SQLException;
	
	void withdraw(double d, int id) throws SQLException;
	
	void transfer(double d, int id1, int id2) throws SQLException;
	
	double checkBalance(int id) throws SQLException;
	
	boolean checkAccountOwnerId(int userID, int ownerID) throws SQLException;
	
	boolean checkAccountExists(int accountID);
	
	List<Transaction> viewTransactionLog();
	
	boolean checkApproved(int id);
}