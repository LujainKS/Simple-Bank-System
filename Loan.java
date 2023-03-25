package banksystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Loan {

    //attributes
    private int loanNum;
    private double amount;
    private String type;
    private double emi;
    Scanner sc = new Scanner(System.in);

    //constructors
    public Loan(int loanNum, double amount, String type) {
        this.loanNum = loanNum;
        this.amount = amount;
        this.type = type;
    }

    public Loan() {
    }

    //setters and getters
    public int getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(int loanNum) {
        this.loanNum = loanNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount() {

        boolean flag = true;
        System.out.print(" Enter the loan amount in SAR: ");
        do {
            try {
                this.amount = sc.nextDouble();
                if (Validations.validateMoneyAmount(this.amount)) {
                    flag = false;
                }
            } catch (Exception err) {
                System.out.print(" Only floating point and integer numbers are acceptable, try again. "
                        + "\n The loan amount in SAR: ");
                sc.next();
            }
        } while (flag);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void loansMenuPrinter() {
        System.out.print(" Which loan you want?\n"
                + " 1. Mortgage loan\n 2. Car loan\n 3. exit\n"
                + " Your choice: ");
    }//prints menu to choose from loan types

    //LoanCalculater calculates the monthly amount for paying back any taken loans
    public void LoanCalcuater() {
        int period = 0;
        double Rate = 0.0;//interest rate

        while (true) {
            try {
                System.out.print(" Enter number of years to calculate monthly loan payback (min time 1y, max time 5yrs): ");
                period = sc.nextInt();

                //checking the number of years chosen by the user
                if (period == 1) {
                    Rate = .05;
                    break;
                } else if (period == 2) {
                    Rate = .10;
                    break;
                } else if (period == 3) {
                    Rate = .15;
                    break;
                } else if (period == 4) {
                    Rate = .20;
                    break;
                } else if (period == 5) {
                    Rate = .25;
                    break;
                } else {
                    System.out.println(" Error! min time 1y, max time 5yrs. Try again.");//if the number of years is not within specified range
                    continue;
                }
            } catch (Exception err) {
                System.out.println(" Wrong Entry! try again.");//if it was characters, send this message. we only accept numbers in this field
                sc.next();
                continue;
            }

        }

        Rate = Rate / (12 * 100);
        period = period * 12;
        //calculating the monthly payment
        emi = (amount * Rate * Math.pow(1 + Rate, period)) / (Math.pow(1 + Rate, period) - 1);

        System.out.print(" The Monthly Payment = " + String.format("%,.2f", emi) + " SAR\n\n");
    }//end of method

    //this method need to be fixed and coded easier
    public void assignLoan(ArrayList<Customer> cstmrArray, ArrayList<Loan> loanArray, Loan loan, ArrayList<String> LoansType) {
        boolean flag = true;
        AtomicReference<String> f = new AtomicReference<>("");
        AtomicReference<String> idFlagValue = new AtomicReference<>("true");

        System.out.print(" Do you want to assign this loan to a customer? ");
        do {
            System.out.print("\n Enter ( y ) if yes and ( n ) if no: ");
            String Answer = sc.next();
            if ("y".equalsIgnoreCase(Answer)) {
                if (!cstmrArray.isEmpty()) {
                    do {
                        System.out.print(" Enter customer's bank ID: ");
                        int id = sc.nextInt();

                        cstmrArray.forEach((customer) -> {
                            if (customer.getID() == id) {
                                loan.setLoanNum(id);
                                loanArray.add(loan);

                                if (loan.getType().equals("car loan")) {
                                    CarLoan carLoan = (CarLoan) loan;
                                    LoansType.add(carLoan.getCarID());
                                } else if (loan.getType().equals("mortgage loan")) {
                                    MortgageLoan mortgageLoan = (MortgageLoan) loan;
                                    LoansType.add(mortgageLoan.getRealEstateAddress());
                                }

                                System.out.println("\n *The Loan Is Assigned Successfully*");
                                f.set("n");
                                idFlagValue.set("false");
                            } else {
                                idFlagValue.set("true");
                            }
                        });
                        if (idFlagValue.equals("true")) {
                            System.out.print(" Error! Can't find the entered ID's record, do you want to try again?"
                                    + "\n Enter ( y ) if yes and ( n ) if no or any other character: ");
                            f.set(sc.next());
                        }
                    } while ("y".equalsIgnoreCase(f.get()));
                    flag = false;
                }//end if
                else {
                    //if customer's array is empty
                    System.out.println("\n There is no customer's record!");
                    flag = false;
                }
            }//end of yes choice statements
            else if ("n".equalsIgnoreCase(Answer)) {
                flag = false;
            } //end of no choice statements
            else {
                System.out.print("\n Wrong Entry! try again.");
            } //validate user input

        } while (flag);
    }//this method assigns a loan to a specific customer using their ID

    public void recordCustomerId() {

    }

    //this method checks if a customer has a loan
    public static void Loancheck(ArrayList<Loan> arr) {

        Scanner input = new Scanner(System.in);
        boolean flag = true;
        do {
            try {

                System.out.print(" Enter customer ID: ");
                int num = input.nextInt();

                for (Loan A : arr) {
                    if (A.getLoanNum() == num) {
                        System.out.println(" This ID is found.");
                        System.out.println(A.toString() + "\n");
                        flag = false;
                        break;
                    }
                }//end for
                if (flag) {
                    System.out.println(" This ID is not found or has no loans assigned to it.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n An error has occurred!\n Exception: " + e + "\n");
            }

        } while (flag);
    }

    @Override
    public String toString() {
        return "\n Loan Details:"
                + "\n Assigned to: " + loanNum
                + "\n Loan amount: " + amount
                + "\n Loan type: " + type
                + "\n Monthly payback amount: " + String.format("%,.2f", emi);
    }

}//end of class