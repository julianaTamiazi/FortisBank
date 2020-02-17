package client;

import java.sql.SQLException;
import java.util.Scanner;

import data.SavingDB;
import bus.*;


public class BankApp {

	public static void main(String[] args) throws SQLException {

		Scanner scan = new Scanner(System.in);
	    int option;
	    Customer cus = new Customer();
	    Transation tra = new Transation();
	    Saving sav = new Saving();
	    Checking che = new Checking();
	    Credit cre = new Credit();
	 
	    
	    do{	
	    	System.out.println("\nWelcome to J&J Bank");
	    	System.out.println("\nChoose a option.");
	    	System.out.println("\n1.Login");
	    	System.out.println("\n2.Exit application ");
	    	option= scan.nextInt();
	    	
			switch(option)
			{
			case 1:
			{
				System.out.println("\nEnter customer id: ");
			    cus.setId(scan.nextInt());
			    scan.nextLine();
			    System.out.println("\nEnter customer password: ");
			    cus.setPass(scan.next());
			   
			    Customer.Login(cus);
			    if(Customer.Login(cus).getId()==0 || Customer.Login(cus).getPass().equals("")){
			    	System.out.println("Customer not found!");
			    	return;
			    }
			   
			   
			    
			    do{
			    
			    
				System.out.println("\nWelcome");
				System.out.println("\nChoose an option");
				System.out.println("\n1. Display your account informations");
				System.out.println("2. Create a saving account");
				System.out.println("3. Create a credit account");
				System.out.println("4. Delete a saving account");
				System.out.println("5. Delete a credit account");
				System.out.println("6. Transactions");
				System.out.println("7. Exit application");
				option= scan.nextInt();
				
				switch(option)
				{
				case 1:
					//Display
					Checking.DisplayAll(cus.getId());
					Saving.DisplayAll(cus.getId());
					Credit.DisplayAll(cus.getId());
					Account.DisplayAll(cus.getId());
					break;
			case 2:
				//Add Saving
				 	
				 								
					sav.setS_number(Saving.GetIndex());
					sav.setAccNo(Saving.CusXAcc(cus.getId()).getAccNo());
					sav.setS_balance(0);
					sav.setS_fee(10);
					System.out.println(sav.getS_number());
					System.out.println(sav.getAccNo());;
					System.out.println(sav.getS_balance());
					System.out.println(sav.getS_fee());
					Saving.Add(sav);
					//TransationDB.Display();
			break;
				case 3:
					 //Add Credit
//					Credit cre = new Credit();
						
					cre.setCr_number(Credit.GetIndex());
					cre.setAccNo(Credit.CusXAcc(cus.getId()).getAccNo());
					cre.setCr_balance(tra.getAmount());
					cre.setCr_limite(5000);
					System.out.println(cre.getCr_number());
					System.out.println(cre.getAccNo());
					System.out.println(cre.getCr_balance());
					System.out.println(cre.getCr_limite());
					Credit.Add(cre);

					break;
				case 4:
					  //Delete Saving
					Saving sav1 = new Saving();
					System.out.println("Enter saving account number: ");
			        sav1.setS_number(scan.nextInt());
			        if(sav.getS_balance()!=0)
			        {
			        	System.out.println("You have a positive balance, make a withdrawl of your total balance ");
			        }
			        else
			        { Saving.Delete(sav1);}
					break;
				case 5:
//					  //Delete Credit
				    Credit cre1 = new Credit();
				    System.out.println("Enter credit account number: ");
			        cre1.setCr_number(scan.nextInt());
			        
			        if(cre.getCr_balance()!=0)
			        {
			        	System.out.println("You have a positive balance, make a withdrawl of your total balance ");
			        }
			        else
			        {  
			        
				    Credit.Delete(cre1);}
					break;
				case 6:
					//Add a transaction
					Checking.DisplayAll(cus.getId());
					Saving.DisplayAll(cus.getId());
					Credit.DisplayAll(cus.getId());
					Account.DisplayAll(cus.getId());
					
							System.out.println("\nChoose the Type of account do you want to acess");
						System.out.println("Choose [1] to Checking, [2] to Saving or [3] to Credit or [4] to exit");
								int	accoption= scan.nextInt();
									 switch(accoption)
									 {
										case 1:
										{								
											tra.setSa_number(Checking.CheByAcc(Checking.CusXAcc(cus.getId()).getAccNo()));
																		
											break;
										}
										case 2:
										{
											tra.setSa_number(Saving.SavByAcc(Saving.CusXAcc(cus.getId()).getAccNo()));
											break;
										}
										case 3:
											{tra.setSa_number(Credit.CreByAcc(Credit.CusXAcc(cus.getId()).getAccNo()));
											}
											break;
										case 4:
										
										
											break;	
										default:	
								 }
						 
									
					
					do{
						System.out.println("Choose [1] to deposit, [2] to withdrawal or [3] to exit");
						option= scan.nextInt();
						 switch(option)
						 {
							case 1:
							{
								tra.setT_number(Transation.GetIndex());
											
															
								tra.setType(enumTypeTrans.Deposit);
								System.out.println("Enter the amount: ");
								tra.setAmount(scan.nextFloat());	
								Transation.Add(tra);
								Transation.Display();
								
								if(accoption==1){//checking
									
									che.setCh_balance(Transation.TotalDeposit(tra.getSa_number())-Transation.Totalwithdrawal(tra.getSa_number()));
									Checking.Update(che);
								}
								
								else if(accoption==2){
									
									//Saving
									sav.setS_balance(Transation.TotalDeposit(tra.getSa_number())-Transation.Totalwithdrawal(tra.getSa_number()));
									Saving.Update(sav);
									System.out.println(sav.getS_balance());
								}
								else if (accoption==3){
									//credit
									cre.setCr_balance(Transation.TotalDeposit(tra.getSa_number())-Transation.Totalwithdrawal(tra.getSa_number()));
									Credit.Update(cre);
								}
								
								
								break;
							}
							case 2:
							{
								tra.setT_number(Transation.GetIndex());
								
								
								tra.setType(enumTypeTrans.Withdraw);
								System.out.println("Enter the amount: ");
								tra.setAmount(scan.nextFloat());	
								Transation.Add(tra);
								Transation.Display();
								
								if(accoption==1){//checking
									
									che.setCh_balance(Transation.TotalDeposit(tra.getSa_number())-Transation.Totalwithdrawal(tra.getSa_number()));
									Checking.Update(che);
								}
								
								else if(accoption==2){
									
									//Saving
									sav.setS_balance(SavingDB.TotalDeposit(tra.getSa_number())-SavingDB.Totalwithdrawal(tra.getSa_number()));
									Saving.Update(sav);
									
								}
								else if (accoption==3){
									//credit
									cre.setCr_balance(Transation.TotalDeposit(tra.getSa_number())-Transation.Totalwithdrawal(tra.getSa_number()));
									Credit.Update(cre);
								}
								
								
								break;
							}
							case 3:
								break;
							default:	
					 }
			 	}while(option !=3);
					
				}
			    }while(option != 7);
			    System.out.println("System exited!");
			    break;
			}
			case 2:
				break;
			default:
		}
	    }while(option !=2);
	    System.out.println("System exited!");
	    


	    
	    scan.close();
	    System.exit(0);

	}
  }

		

					
