package banksystem;


import java.util.ArrayList;
import java.util.Scanner;

public class CarLoan extends Loan {
    //attributes
    private String carID;
   
    Scanner sc=new Scanner(System.in);
    //constructors
    public CarLoan() {
    }
    
    public CarLoan(int loanNum, double amount, String type) {
        super(loanNum, amount, type); 
    
    }

    public CarLoan(int loanNum, double amount, String type, String carID) {
        super(loanNum, amount, type);
        this.carID = carID;

    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(ArrayList<String> CustomerLoans) {
        boolean flag = false;
        do
        {
            System.out.print(" Enter car license plate (example: 1234 ABD): ");
            this.carID = sc.nextLine();
            flag = false;
        } while ( !(Validations.validateCarLicensePlate(this.carID)) |
                Validations.validateFromTheUniquenessOfCarId(CustomerLoans, getCarID()).get());
    }

    @Override
    public String toString() {
        return super.toString()+" Car license: " + carID +"\n";
    }

}//end of class


  
    
   
    
        
        


        

