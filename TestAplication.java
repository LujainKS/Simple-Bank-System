package banksystem;

import java.util.Scanner;

public class TestAplication {
     public static void main(String[] args) {

            Bank bank = null;
            Scanner in = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);

            boolean quit = false;
            headerPrinter();
            int menuItem = 0;

            do
            {
                //Create bank object to be able to access the system
                System.out.print(" Enter your username: ");
                String username = scanner.next();

                System.out.print(" Enter your password: ");
                String password = scanner.next();
                bank = Bank.createBankObject(username, password);
            } while(bank == null);


            //This (do while) loop runs a menu of choices that a user can choose from
            do {
                  //(Do-While) to validate user choice
                  boolean flag = true;
                  do
                  {
                      try
                      {
                          mainMenuPrinter();
                          //read user choice
                          menuItem = in.nextInt();
                          //validate user choice
                          if ((Validations.validateMenuEnteredValue(menuItem))){
                              flag = false;
                          }
                      }catch (Exception e){
                          System.out.println(" \n ****************************");
                          System.out.println("   Wrong Entry! Try again");
                          System.out.println(" ****************************");
                          in.next();
                          //continue;
                      }
                  }while(flag);

                  //output format
                  System.out.print("\n");
                  
                  
                  switch (menuItem) {

                  
                  case 1:
                      bank.createSavingAccount();
                      break;

                 
                  case 2:
                        bank.createCheckingAccount();
                        break;
                  
                  case 3: 
                        bank.createLoan();
                        break;
                  case 4://checking if a customer has a loan
                        bank.searchForCustomerLoan();
                        break;  

                  case 5:
                        bank.savingAccountsPrinter();
                        break;
                  
                 case 6:
                        bank.checkingAccountsPrinter();
                        break;
                  
                 case 7:
                     bank.getAccounts();
                     break; 
                                             
                 case 0: 
                     quit = true;
                     break;

                 default:
                        System.out.println(" Invalid choice.");

                  }//end of switch

            } while (!quit);

             System.out.println(" ************************************************");
             System.out.println(" Thank you for using the system! ");
             System.out.println(" ************************************************");
     }//end of main


    public static void headerPrinter()
    {
        System.out.println(" ************************************************");
        System.out.println(" Welcome to the Bank Customers Management system!");
        System.out.println(" ************************************************\n");
    }


    public static void mainMenuPrinter()
    {
        System.out.print("\n Choose an item from the following menu:"
                + " \n 1- Create saving account \n 2- Create checking account "
                + " \n 3- Make a loan \n 4- Check a customer's loan details"
                + "\n 5- Print all saving accounts"
                + "\n 6- Print all checking accounts"
                + " \n 7- Print all accounts in the Bank"
                + "\n  - Press (0) to exit the system.. \n Enter your choice : ");
    }    
}//end of class