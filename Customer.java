package banksystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
//attributes
private String name;
private String phone;
private long ID ;
private String address;
Scanner sc=new Scanner(System.in); 


    public Customer() {
    }

    public Customer(String name, String phone, long ID, String address) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        System.out.print(" Customer phone number: ");
                this.phone = sc.nextLine();
                while(!Validations.validatePhoneNumber(this.phone)){
                    System.out.print(" Incorrect phone number! try again. (10 numbers starts with 05)\n Phone number: ");
                    this.phone = sc.nextLine();
                }

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getAddress() {
        
        
        return address;
    }

    public void setAddress(String address)
    {
        System.out.print(" Customer address: ");
        this.address  = sc.nextLine();
        while (! Validations.validateAddress(this.address))
        {
            System.out.print(" Customer address: ");
            this.address  = sc.nextLine();
        }
    }

    public Customer getInfo(String name, ArrayList<Customer> customers){
        
        for( Customer c: customers ){
            if(c.getName() == name)
                return c;
        }
        return null;
    }
    
    @Override
        public String toString() {
        return " Name: " + name + "\n Phone: " + phone + "\n Address: " + address + "\n ID:"+ ID +"\n";
    }

}