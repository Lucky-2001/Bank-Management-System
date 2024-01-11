package org.jsp.bank.DAO;

import org.jsp.bank.model.Bank;

public interface BANKDAO {

	void userRegistration(Bank bank);
	void credit(String accountnumber,String password);
	void debit(String accountnumber,String password);
	void changingThePassword(String accountnumber);
	void mobileToMobileTransaction(String  mobilenumber);
	void checkBalance(String accountnumber,String password);
	
	
}
