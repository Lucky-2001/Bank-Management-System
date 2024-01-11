package org.jsp.bank.DAO;

public class BankCustomerDesk {

	public static BANKDAO customerHelpDesk() {
		BANKDAO bankDAO = new BankDaoImplementation();
		return bankDAO;
	}
}
