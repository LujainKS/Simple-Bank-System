package banksystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Employee {
    private String name;
    private String username;
    private String password;


    public Employee(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    public static ArrayList<Employee> employeeArrayCreator() {
        ArrayList<Employee> ArrayOfEmployeesData = new ArrayList<Employee>();

        try {
            RandomAccessFile accessfile = new RandomAccessFile("EmployeeData.txt", "rw");

            for (int i = 0; i < accessfile.length(); i++) {
                
                String str = accessfile.readLine();//reading employee name from file
                int index = str.indexOf(":");
                str = str.substring(index+1);
                String name = str;

                str = accessfile.readLine();//reading employee username from file
                index = str.indexOf(":");
                str = str.substring(index+1);
                String username = str;

                str = accessfile.readLine();//reading employee password from file
                index = str.indexOf(":");
                str = str.substring(index+1);
                String password = str;

                ArrayOfEmployeesData.add(new Employee(name, username, password));//store an employee object inside the array of employess

                if ((accessfile.readLine()==null)){ //to stop reading when the file has no values to read
                    break;
                }

            }//end of for loop block
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
        return ArrayOfEmployeesData;
    }//stores the existing information of employees from a file in an array for validation

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}