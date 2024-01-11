package org.jsp.bank;

import java.util.Scanner;

import org.jsp.bank.DAO.BANKDAO;
import org.jsp.bank.DAO.BankCustomerDesk;
import org.jsp.bank.model.Bank;
public class App 
{
    public static void main( String[] args )
    {
    	BANKDAO bankDAO =  BankCustomerDesk.customerHelpDesk();
         Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter \n 1. For Registration \n 2.For Credit \n 3.For Debit \n 4.For Changing The Password \n 5. For Mobile To Mobile Transaction \n 6.For Check Balance");
         Scanner sc = new Scanner(System.in);
         int response = sc.nextInt();
         
         switch(response)
         {
         case 1:   
        	      System.out.println("Enter Your First Name:");
        	      String fname = scanner.next();
        	      System.out.println("Enter Your Last Name:");
        	      String lname = scanner.next();
        	      System.out.println("Enter the Mobile Number:");
        	      String mb = scanner.next();
        	      System.out.println("Enter the EmailID:");
        	      String email = scanner.next();
        	      System.out.println("Enter the Password:");
        	      String password = scanner.next();
        	      System.out.println("Enter Your Amount:");
        	      double amount = scanner.nextDouble();
        	      System.out.println("Enter Your Address:");
        	      String address = scanner.next();
        	      System.out.println("Enter Your Account Number:");
        	      String account = scanner.next();
        	      
        	      
//        	       Bank bank = new Bank(0,"Dingi","Kumari","5614957926","dingi@gmal.com","65348",1166,"tirupathi","axs23697458");
        	      Bank bank = new Bank(0,fname,lname,mb,email,password,amount,address,account);
        	       bankDAO.userRegistration(bank);
        	       break;
        	       
         case 2:
        	 System.out.println("Enter your account number");
     	    boolean accountstatus = true;
     	    while(accountstatus) {
     	    String accountnumber = scanner.next();
     	    if(accountnumber.length()==11) {
     	    	accountstatus = false;
     	    	System.out.println("Enter the password");
     	    	boolean passwordstatus = true;
     	    	while(passwordstatus) {
         	    String pass = scanner.next();
         	    if(pass.length()==5) {
         	    	passwordstatus = false;
         	    	bankDAO.credit(accountnumber,pass);
         	    	
         	    	
         	    }
         	    else {
         	    	System.out.println("Invalid Password");
         	    	System.out.println("Enter 5 digit password");
         	    	passwordstatus = true;
         	    }
     	    	}
     	    }
     	    else
     	    {  
     	    	System.out.println("Invalid Account Number");
     	    	System.out.println("Enter 11 digit account number");
     	    }
              }
     	    break;
        	 
         case 3:
        	    System.out.println("Enter your account number");
        	    boolean accountstatus1 = true;
        	    while(accountstatus1) {
        	    String accountnumber = scanner.next();
        	    if(accountnumber.length()==11) {
        	    	accountstatus1 = false;
        	    	System.out.println("Enter the password");
        	    	boolean passwordstatus = true;
        	    	while(passwordstatus) {
            	    String pass = scanner.next();
            	    if(pass.length()==5) {
            	    	passwordstatus = false;
            	    	bankDAO.debit(accountnumber,pass);
            	    	
            	    	
            	    }
            	    else {
            	    	System.out.println("Invalid Password");
            	    	System.out.println("Enter 5 digit password");
            	    	passwordstatus = true;
            	    }
        	    	}
        	    }
        	    else
        	    {  
        	    	System.out.println("Invalid Account Number");
        	    	System.out.println("Enter 11 digit account number");
        	    }
                 }
        	    break;
        	    
         case 4:
        	 System.out.println("Enter your account number");
     	    boolean accountstatus3 = true;
     	    while(accountstatus3) {
     	    String accountnumber = scanner.next();
     	    if(accountnumber.length()==11) {
     	    	 accountstatus3 = false;
     	    	bankDAO.changingThePassword(accountnumber);
     	    	}
     	    
     	    else
     	    {  
     	    	System.out.println("Invalid Account Number");
     	    	System.out.println("Enter 11 digit account number");
     	    }
           }
     	    break;
         case 5:
        	 
     	    boolean mobilestatus = true;
     	    while(mobilestatus) {
     	    System.out.println("Enter your mobile number");
     	    String mobilenumber = scanner.next();
     	    if(mobilenumber.length()==10) {
     	    	mobilestatus = false;
     	    	bankDAO.mobileToMobileTransaction(mobilenumber);
         	    }         	       	   
     	    else
     	    {  
     	    	System.out.println("Invalid Mobile Number");
     	    	System.out.println("Enter 10 digit account number");
     	    }
          }
     	    break;
        	 
     	    
         case 6:
        	 System.out.println("Enter your account number");
     	    boolean accountstatus2 = true;
     	    while(accountstatus2) {
     	    String accountnumber = scanner.next();
     	    if(accountnumber.length()==11) {
     	    	accountstatus2 = false;
     	    	System.out.println("Enter the password");
     	    	boolean passwordstatus = true;
     	    	while(passwordstatus) {
         	    String pass = scanner.next();
         	    if(pass.length()==5) {
         	    	passwordstatus = false;
         	    	bankDAO.checkBalance(accountnumber,pass);
         	    	
         	    	
         	    }
         	    else {
         	    	System.out.println("Invalid Password");
         	    	System.out.println("Enter 5 digit password");
         	    	passwordstatus = true;
         	    }
     	    	}
     	    }
     	    else
     	    {  
     	    	System.out.println("Invalid Account Number");
     	    	System.out.println("Enter 11 digit account number");
     	    }
              }
     	    break;
        	    
        default:
        		    break;
         }
       
    }
}
