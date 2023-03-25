package banksystem;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations  {

    public static boolean validateName(String name)
    {
        String regex = "^[A-Za-z]+[ ]+[A-Za-z]+[ ]+[A-Za-z]{3,10}+[ ]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }


    public static boolean validateMoneyAmount(double moneyAmount)
    {
        String regex = "[0-9.]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(moneyAmount + "");
        if (!m.matches())
        {
            System.out.print(" Only floating point and integer numbers are acceptable, try again. " +
                    "\n The money amount in SAR: ");
        }
        return m.matches();
    }


    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^(05)+[0-9]{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }


    public static boolean validateMenuEnteredValue(int choice){
        String regex = "^[0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(choice + "");
        if (!m.matches()){
            System.out.println(" \n ****************************");
            System.out.println("   Wrong Entry! Try again");
            System.out.println(" ****************************");
        }
        return m.matches();
    }


    public static boolean validateAddress(String address)
    {
        String regex = "[A-Za-z0-9,\\-_()\\s:]+[^$&$£;{}!\"~@.#]{4,}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(address);
        if (!m.matches())
        {
            System.out.print("\n Wrong entry, try again! \n");
        }
        return m.matches();
    }


    public static boolean validateRealEstateAddress(String realEstateAddress)
    {
        String regex = "^[0-9]{4}+[ ]*(-)+[ ]*[a-zA-Z]{2,9}+[ ]?+([a-zA-Z]{4,8})?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(realEstateAddress);
        if (!m.matches())
        {
            System.out.print("\n Wrong entry, try again! (Building number-District)\n");
        }
        return m.matches();
    }


    public static boolean validateCarLicensePlate(String carLicensePlate)
    {
        String regex = "^[0-9]{4}+[ ]{0,1}+[a-zA-Z]{3}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(carLicensePlate);
        if (!m.matches())
        {
            System.out.print("\n Wrong entry, try again! (four digits followed by 3 letters)\n");
        }
        return m.matches();
    }


    public static AtomicBoolean validateFromTheUniquenessOfCarId(ArrayList<String> CustomerLoans, String carLicensePlate)
    {
        AtomicBoolean flag = new AtomicBoolean(false);

        CustomerLoans.forEach((customerLoan) -> {
            if (customerLoan.replaceAll("\\s+","").equalsIgnoreCase(carLicensePlate.replaceAll("\\s+",""))){
                System.out.println(" This Car License Plate Has Been Used! Try again. ");
                flag.set(true);
            }

        });
        return flag;
    }


    public static AtomicBoolean validateFromTheUniquenessOfRealEstateAddress(ArrayList<String> CustomerMLoans, String houseAddress)
    {
        AtomicBoolean flag = new AtomicBoolean(false);

        CustomerMLoans.forEach((customerLoan) -> {
            if (customerLoan.replaceAll("\\s+","").equalsIgnoreCase(houseAddress.replaceAll("\\s+","")))
            {
                flag.set(true);
                System.out.print("\n This Building Address Has Been Used\n");
            }

        });
        return flag;
    }


    public static boolean validateUsername(Employee emp, String username)
    {
        if (emp.getUsername().replaceAll("\\s+","").equalsIgnoreCase(username.replaceAll("\\s+","")))
        {
            return true;
        }
        return false;
    }

    public static boolean validatePassword(Employee emp, String password)
    {
        if (emp.getPassword().replaceAll("\\s+","").equalsIgnoreCase(password.replaceAll("\\s+","")))
        {
            return true;
        }
        return false;
    }

}