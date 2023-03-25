package banksystem;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    //attributes
    private String address;
    private ArrayList<Account> myAccounts = new ArrayList<>();//storing all bank accounts
    private ArrayList<Customer> myCustomer = new ArrayList<>();//storing all bank customers
    private ArrayList<Account> savingArr = new ArrayList<>();//storing all saving accounts
    private ArrayList<Account> checkingArr = new ArrayList<>();///storing all checking accounts
    private ArrayList<Loan> CustomerLoans = new ArrayList<>();///storing loan information
    private ArrayList<String> carLoans = new ArrayList<>();///to validate the uniqueness
    private ArrayList<String> mortgageLoans = new ArrayList<>();///to validate the uniqueness
    private Customer newCus;
    private Account newAcc;
    private static int indexOfEmp;///to access employee name

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    private static ArrayList<Employee> ArrayOfEmployeesData = Employee.employeeArrayCreator();
    //Constructor
    public Bank() {}


    public static Bank createBankObject(String username, String password)
    {
        char state = validateEnteredData(username, password);
        switch (state)
        {
            case '1':
                System.out.println("\n WELCOME"+ ArrayOfEmployeesData.get(indexOfEmp).getName().toUpperCase(Locale.ROOT));
                return new Bank();
            case '2':
                System.out.println(" PASSWORD IS NOT CORRECT!\n");
                return null;
            default:
                System.out.println(" The Entered Data Does Not Exist! \n");
                return null;
        }

    }//creates a bank object 


    private static char validateEnteredData(String username, String password)
    {
        for (Employee emp : ArrayOfEmployeesData)
        {
            if (Validations.validateUsername(emp,username) && Validations.validatePassword(emp, password))
            {
                indexOfEmp = ArrayOfEmployeesData.indexOf(emp);
                return '1';
            }
            else if (Validations.validateUsername(emp,username))
            {
                return '2';
            }
        }
        return 3;
    }//validates the employees entered data



///////BANK SYSTEM SERVICES THAT WILL BE AVAILABLE TO VALIDATED USERS///////

    public void createSavingAccount()
    {
        int ser =  ((rand.nextInt(200)+100000));
        newAcc = new SavingAccount("Saving",null,0,0, ser+"");
        //set object values
        newAcc.setName("");
        newAcc.setAmount(0);

        newCus = new Customer();
        //set object values
        newCus.setName(newAcc.getName());
        newCus.setID(newAcc.getID());
        newCus.setAddress(null);
        newCus.setPhone("");

         //add the objects to the array
        addAccountObjectToArrays(savingArr);

        successfulAccountCreationMessagePrinter("Saving");
    }//method to create a saving account (case 1 in main)


    public void createCheckingAccount()
    {
        newAcc = new CheckingAccount("Checking",null,0,0,null);
        //set object values
        newAcc.setName("");
        newAcc.setAmount(0);

        newCus = new Customer();
        //set object values
        newCus.setName(newAcc.getName());
        newCus.setID(newAcc.getID());
        newCus.setAddress(null);
        newCus.setPhone("");

        //add the objects to the array
        addAccountObjectToArrays(checkingArr);

        successfulAccountCreationMessagePrinter("Checking");
    }//method to create a checking account (case 2 in main)

    public void createLoan()
    {
        boolean userChoiceFlag = true; //terminate validation loop
        while (userChoiceFlag) {
            try {
                boolean termination = true;//for terminating do-while loop
                Loan.loansMenuPrinter();
                int loanOption = scanner.nextInt();
                //to keep asking the user for appropriate input
                do
                {
                    if (loanOption == 1) //mortgage loan
                    {
                        createMortgageLoan();
                        termination = false;
                    }
                    else if (loanOption == 2) //car loan
                    {
                        createCarLoan();
                        termination = false;
                    }
                    else if (loanOption == 3) //exit
                    {
                        termination = false;
                    }
                    else //Validate user input
                    {
                        System.out.print("\n Invalid Choice! Please choose a"
                                + " number from 1 or 2 to choose your loan type."
                                + "\n Or enter 3 to exit: ");
                        loanOption = scanner.nextInt();
                    }
                } while (termination);
                userChoiceFlag = false;
            } catch (Exception err) {
                System.out.println(" Wrong Entry! Try again. " + err.toString());
                scanner.next();
                //keep looping if wrong entry
                userChoiceFlag = true;
            }//end of catch clause
        }//end of while loop
    }// creates a loan for the user (case 3 in main)

    public void searchForCustomerLoan()
    {
        System.out.println(" \n ----------------------------------------");
        Loan.Loancheck(CustomerLoans);
        System.out.println(" ----------------------------------------");
    }//method to check availability of a loan using customer ID (case 4 in main)

    public void savingAccountsPrinter()
    {
        //Print all objects in the ArrayList
        System.out.println(" \n ----------------------------------------");
        arraysPrinter(savingArr);
        System.out.println(" ----------------------------------------");
    }//prints list of all saving accounts (case 5 in main)

    public void checkingAccountsPrinter()
    {
        //Print all objects in the ArrayList
        System.out.println(" \n ----------------------------------------");
        arraysPrinter(checkingArr);
        System.out.println(" ----------------------------------------");
    }//prints list of all checking accounts (case 6 in main)
    
        public void getAccounts()
    {
        System.out.println(" \n ----------------------------------------");

        if (!myAccounts.isEmpty())
        {
        System.out.println(" The Existing Bank Accounts Are:\n");
        arraysPrinter(myAccounts);
        } else
            System.out.println(" \tThere Is No Bank Accounts Records");

        System.out.println(" ----------------------------------------");
    }//prints all accounts in the bank (case 7 in main)
//////////////END OF MAIN BANK SERVICES METHODS/////////////////       
        
///DETAILED METHODS THAT HELP ACHIEVING THE SERVICES///
    private void createMortgageLoan()
    {
        String houseAddress;
        MortgageLoan mortgage = new MortgageLoan();
        System.out.print(" Enter the real estate address (Building number-District): ");
        houseAddress=scanner.next();
        while (!Validations.validateRealEstateAddress(houseAddress) |
                Validations.validateFromTheUniquenessOfRealEstateAddress(mortgageLoans,houseAddress).get())
        {
            System.out.print(" Enter the real estate address (Building number-District): ");
            houseAddress=scanner.next();
        }
        mortgage.setRealEstateAddress(houseAddress);
        mortgage.setAmount();
        mortgage.setType("mortgage loan");
        mortgage.LoanCalcuater();

        //assigning the loan to a customer
        mortgage.assignLoan(myCustomer, CustomerLoans, mortgage, mortgageLoans);
    }//creating a mortgage loan object, calculating loan amount and assigning it to customer 
    
    private void createCarLoan()
    {
        CarLoan car =new CarLoan();
        car.setCarID(carLoans);
        car.setAmount();
        car.setType("car loan");
        car.LoanCalcuater();
        car.assignLoan(myCustomer, CustomerLoans, car, carLoans);
    }//creating a car loan object, calculating loan amount and assigning it to customer 

    private void addAccountObjectToArrays(ArrayList<Account> accountType)
    {
        //add the objects to the array
        myAccounts.add(newAcc);
        accountType.add(newAcc);
        myCustomer.add(newCus);
    }
    
    /////PRINTER METHODS//////
        private void successfulAccountCreationMessagePrinter(String accountType)
    {
        System.out.printf("\n\n ****The %s Account Has Been Created**** "
                + "\n **********The Customer ID = "+newCus.getID()+"*********** \n\n ", accountType);
    }

    private void arraysPrinter(ArrayList<Account> array)
    {
        if (!array.isEmpty())
        {
            for (Account A : array)
                System.out.println(A);
        }
        else
        {
            System.out.println(" ----------THERE IS NO ACCOUNTS----------");
        }
    }

    //Setters & Getters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}//end of class