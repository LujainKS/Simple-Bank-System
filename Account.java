package banksystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
    //attributes
    private String accountType;
    private String name;
    private double amount;
    private long ID =3000; //id starts from this number
    //to increase the ID each time a new account is created
    private static long count =0; 
    //using a scanner to store the input taken from user during runtime
    Scanner sc=new Scanner(System.in); 

    //setters and getters
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        System.out.print(" Customer Full Name: ");
        this.name = sc.nextLine().toUpperCase();
        while(!Validations.validateName(this.name)){
            System.out.print(" Incorrect input! try again. (First name, middle name, and Surname)\n Customer full Name: ");
            this.name = sc.nextLine();
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        boolean flag = true;
        System.out.print(" Enter the money amount in SAR: ");
        do {
            try {
                this.amount = sc.nextDouble();
                if (Validations.validateMoneyAmount(this.amount)){
                    flag = false;
                }
            } catch (Exception err)
            {
                System.out.print(" Only floating point and integer numbers are acceptable, try again. " +
                        "\n The money amount in SAR: ");
                sc.next();
            }
        } while (flag);
    }

    public long getID() 
    {
        return ID ;
    }

    public void setID(long ID) {
      this.ID = ID ;
        
    }

    //constructors for creating an Account object
     public Account( String accountType , String name, double amount , long ID) {
         this.accountType = accountType;
         this.name = name;
         this.amount = amount;
         count++;
         this.ID+=count;
        

    }//end const
     
     public Account( String accountType , String name, double amount) {
         this.accountType = accountType;
         this.name = name;
         this.amount = amount;
    }//end const
     
    public Account() {
    }//empty constructor
    
    public Account( String accountType , String name) {
         this.accountType = accountType;
         this.name = name;
    }//end const
    
    public Account( String accountType) {
         this.accountType = accountType;

    }//end const
    
   //functions
      public void deposit(double amount){
        if (amount > 0.0) 
            this.amount += amount ;
                    }//deposit function is used to add money to the account

    @Override
    public String toString() {
        return " Name: " + name + "\n AccountType: " + accountType + "\n Amount: " + amount + "\n ID: "+ ID;
    }//toString is used to print account information
    
}//end of class