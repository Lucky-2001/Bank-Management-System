package org.jsp.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import org.jsp.bank.model.Bank;

public class BankDaoImplementation implements BANKDAO {
	String url = "jdbc:mysql://localhost:3306/teca52?user=root&password=12345";
	Scanner scanner = new Scanner(System.in);
	@Override
	public void userRegistration(Bank bank) {
		String insert = "insert into bank(userFirstName, userLastName, mobileNumber, emailId, password, address,amount,accountNumber)values(?,?,?,?,?,?,?,?)";
		
	    try {
	    Connection connection = DriverManager.getConnection(url);
	    PreparedStatement preparedstatement = connection.prepareStatement(insert);
	    preparedstatement.setString(1, bank.getUserFirstName());
	    preparedstatement.setString(2, bank.getUserLastName());
	    preparedstatement.setString(3, bank.getMobileNumber());
	    
	    String tempname= bank.getUserFirstName().toLowerCase();
	    Random random = new Random();
	    int tempnum = random.nextInt(1000);
	    String bankemailid = tempname+tempnum+"@teca52.com";
	    
	    preparedstatement.setString(4, bankemailid);
	    preparedstatement.setString(5, bank.getPassword());
	    preparedstatement.setString(6, bank.getAddress());
	    preparedstatement.setDouble(7, bank.getAmount());
	    
	    long ac = random.nextLong(1000000000l);
	    if(ac<1000000000l) {
	    	ac+=1000000000l;   // 1000000l  -> last digit is l
	    }
	    
	    preparedstatement.setString(8,""+ac);
       int result = preparedstatement.executeUpdate();
       if(result!=0) {
	      System.out.println("Account created successfully");
	      try {
			Thread.sleep(2000);
			System.out.println("Email and Account Number Generated");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       else {
	         System.out.println("Account not created");
        }
	    
	    }
	    catch(SQLException e){
	    	e.getStackTrace();
	    }
	    
	}

	@Override
	public void credit(String accountnumber,String password) {
		// TODO Auto-generated method stub
String select= "select * from bank where accountNumber=?  and password=?";
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, accountnumber);
			ps.setString(2, password);
			ResultSet resultset = ps.executeQuery();
			if(resultset.next()) {
				System.out.println("Enter your amount");
				double userAmount =scanner.nextDouble();
				boolean amountStatus = true;
				if(userAmount>0) {
					amountStatus = false;
					double databaseAmount = resultset.getDouble("amount");
						double add = databaseAmount + userAmount;
						System.out.println(add);
						
						String update = "update bank set amount=? where accountNumber = ? and password=?";
						
					    try {
					    Connection connection1 = DriverManager.getConnection(url);
					    PreparedStatement preparedstatement1 = connection1.prepareStatement(update);
					    preparedstatement1.setDouble(1, add);
					    preparedstatement1.setString(2, accountnumber);
					    preparedstatement1.setString(3, password);
					    
					    int result1 = preparedstatement1.executeUpdate();
					       if(result1!=0) {
						      System.out.println("Amount Creditted successfully");
						     
					       }
					       else {
						         System.out.println("Amount Credit unsuccessful");
					        }
					    }
					    catch(SQLException e){
					    	e.getStackTrace();
					    }
					    
														
				}
				else {
					System.out.println("Invalid Amount");
					System.out.println("Enter amount greater than 0");
					amountStatus = true;
					
				}
				
			}
			else {
				System.out.println("NOT OK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void debit(String accountnumber,String password) {
		String select= "select * from bank where accountNumber=?  and password=?";
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, accountnumber);
			ps.setString(2, password);
			ResultSet resultset = ps.executeQuery();
			if(resultset.next()) {
				System.out.println("Enter your amount");
				double userAmount =scanner.nextDouble();
				boolean amountStatus = true;
				if(userAmount>0) {
					amountStatus = false;
					double databaseAmount = resultset.getDouble("amount");
					if(databaseAmount>=userAmount) {
						double sub = databaseAmount - userAmount;
						System.out.println(sub);
						
						String update = "update bank set amount=? where accountNumber = ? and password=?";
						
					    try {
					    Connection connection1 = DriverManager.getConnection(url);
					    PreparedStatement preparedstatement1 = connection1.prepareStatement(update);
					    preparedstatement1.setDouble(1, sub);
					    preparedstatement1.setString(2, accountnumber);
					    preparedstatement1.setString(3, password);
					    
					    int result1 = preparedstatement1.executeUpdate();
					       if(result1!=0) {
						      System.out.println("Amount Debitted successfully");
						     
					       }
					       else {
						         System.out.println("Amount debit unsuccessful");
					        }
					    }
					    catch(SQLException e){
					    	e.getStackTrace();
					    }
					    
					    
					}
					else {
						System.out.println("Insufficient Balance");
						amountStatus = true;
					}
					
					
					
				}
				else {
					System.out.println("Invalid Amount");
					System.out.println("Enter amount greater than 0");
					amountStatus = true;
					
				}
				
			}
			else {
				System.out.println("NOT OK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void changingThePassword(String accountnumber) {
		// TODO Auto-generated method stub
	String select= "select * from bank where accountNumber=? ";
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, accountnumber);
			
			ResultSet resultset = ps.executeQuery();
			if(resultset.next()) {
				
						
						String update = "update bank set password=? where accountNumber = ?";
						
					    try {
					    Connection connection1 = DriverManager.getConnection(url);
					    PreparedStatement preparedstatement1 = connection1.prepareStatement(update);
					    boolean checkpassword = true;
					    while(checkpassword) {
					    System.out.println("Enter the new password");
					    String newPassword = scanner.next();
					    System.out.println("Re-enter the  new password");
					    String rePassword = scanner.next();
					    if(newPassword.equals(rePassword)) {
					    	preparedstatement1.setString(1, newPassword);
						    preparedstatement1.setString(2, accountnumber);
						    
						    int result1 = preparedstatement1.executeUpdate();
						       if(result1!=0) {
						    	   checkpassword = false;
							      System.out.println("Password changed successfully");
							     
						       }
						       else {
							         System.out.println("password change unsuccessful");
						        }
						    }

					    else {
					    	System.out.println("Enter the new password correctly");
					        }
					    }
					   }
					    catch(SQLException e){
					    	e.getStackTrace();
					    }
					    
			}
					
			else {
				System.out.println("NOT OK");
			}
		}
		
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public void mobileToMobileTransaction(String  sendernumber) {
		// TODO Auto-generated method stub
       String select= "select * from bank where  mobileNumber=?";
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, sendernumber);
			
			ResultSet resultset = ps.executeQuery();
			if(resultset.next()) {
				boolean remobilenumberstatus = true;
				while(remobilenumberstatus) {
				System.out.println("Enter Receiver Mobile Number");
				String receivernumber =  scanner.next();
				if(receivernumber.length()==10) {
					remobilenumberstatus = false;
					PreparedStatement psre = connection.prepareStatement(select);
					psre.setString(1, receivernumber);
					ResultSet resultsetre = psre.executeQuery();
					if(resultsetre.next()) {
						System.out.println("Enter Amount:");
						boolean amountstatus = true;
						while(amountstatus) {
								double amount = scanner.nextDouble();
								if(amount>0) {
									amountstatus = false;
									double senderbalance = resultset.getDouble("amount");
									double recieverbalance = resultsetre.getDouble("amount");
									if(amount<=senderbalance) {
										
										double sub = senderbalance - amount;
										System.out.println("Remaining Balance for "+sendernumber+" is: "+sub);
										double add = recieverbalance + amount;
										System.out.println("Balance for "+receivernumber+" is: "+add);
										
										
										String senderupdate = "update bank set amount=? where mobileNumber=?";
										String recieverupdate = "update bank set amount=? where mobileNumber=?";
										
									    try {

									    PreparedStatement preparedstatement1 = connection.prepareStatement(senderupdate);
									    preparedstatement1.setDouble(1, sub);
									    preparedstatement1.setString(2, sendernumber);
									    
									    PreparedStatement preparedstatement2 = connection.prepareStatement(recieverupdate);
									    preparedstatement2.setDouble(1, add);
									    preparedstatement2.setString(2, receivernumber);
									    
									    

									    
									    int result1 = preparedstatement1.executeUpdate();
									    int result2 = preparedstatement2.executeUpdate();
									    
									       if(result1!=0 && result2!=0 ) {
										      System.out.println("Transaction successfully");
										     
									       }
									       else {
										         System.out.println("Transaction unsuccessful");
									        }
									    }
									    catch(SQLException e){
									    	e.getStackTrace();
									    }
										
									}
									else {
										System.out.println("Insufficient Balance");
										System.out.println("Your Account Balance is:"+sendernumber+"/-Rs");
										amountstatus= true;
									}
								}
								else {
									System.out.println("Enter Valid Amount");
								}
						}
					}
					else {
						System.out.println("Enter Receiver Mobile Number");
						System.out.println("Refer your friend to TECA52 bank to get offers and benefits");
					}
					
				}
			   }
			}else {
				System.out.println("Invalid Details");
			}
			}
		 catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}

	@Override
	public void checkBalance(String accountnumber, String password) {
	    String check = "select * from  bank where accountNumber = ? and password=?";
	    
	    try {
	        Connection connection2 = DriverManager.getConnection(url);
	        PreparedStatement preparedstatement2 = connection2.prepareStatement(check);
	        preparedstatement2.setString(1, accountnumber);
	        preparedstatement2.setString(2, password);
	        ResultSet result2 = preparedstatement2.executeQuery();
	        
	        if (result2.next()) {
	            double balance = result2.getDouble("amount");
	            System.out.println("Account Balance: " + balance);
	        } else {
	            System.out.println("Invalid account number or password");
	        }
	        
	        // Close resources
	        result2.close();
	        preparedstatement2.close();
	        connection2.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
