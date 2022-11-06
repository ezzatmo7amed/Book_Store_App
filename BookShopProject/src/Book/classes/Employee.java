package Book.classes;

import java.util.Date;

public class Employee {
    private int employee_Id;
    private String first_Name;
    private String last_Name;
    private String Address;
    private Date hireDate;
    private double salary;
    private String Email;
    private String user_Name;
    private String password;

    public Employee(){ 
        }


    public Employee(int employee_Id, String first_Name, String last_Name, String Address, Date hireDate, double salary,
                    String Email, String user_Name, String password) {
       
        this.employee_Id = employee_Id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.Address = Address;
        this.hireDate = hireDate;
        this.salary = salary;
        this.Email = Email;
        this.user_Name = user_Name;
        this.password = password;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
