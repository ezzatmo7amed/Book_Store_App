package Book.beans;

import Book.classes.Customers;
import Book.classes.JDBCCLASS;

import java.io.Serializable;

import java.util.ArrayList;

public class RegisterBean implements Serializable {
    private int user_id;
    private String firstName; 
    private String lastName;
    private int phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private ArrayList<Customers> customerList = new ArrayList<Customers>();
    public RegisterBean() {
    }

    public void setCustomerList(ArrayList<Customers> customerList) {
        this.customerList = customerList;
    }

    public ArrayList<Customers> getCustomerList() {
        
        this.customerList= JDBCCLASS.showCustomers();
        return customerList;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Object registerUser() {
         boolean registerOk = JDBCCLASS.registerCustomer();
         
         if(registerOk)
          return"Registersucces";
         else
          return null;
    }

}
