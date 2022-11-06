package Book.classes;


import Book.beans.EmployeesBean;
import Book.beans.LoginBean;
import Book.beans.RegisterBean;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class JDBCCLASS {
    
    public static boolean registerCustomer() {
        Connection conn = null;
               PreparedStatement pstmt = null;
               try {

                   //STEP 3: Open a connection
                   System.out.println("Connecting to database...");
                   //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                   Context ctx = new InitialContext();
                   DataSource ds = (DataSource) ctx.lookup("BookShopAppDB");
                   conn = ds.getConnection();

           // Reading parameter from Bean in Session Scope
            RegisterBean registerBean =
                       (RegisterBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("RegisterBean");

                   String firstName = registerBean.getFirstName();
                   String lastName = registerBean.getLastName();
                   int phoneNumber =registerBean.getPhoneNumber();
                   String email = registerBean.getEmail();
                   String password = registerBean.getPassword();

                   String sql;
                   sql = "INSERT INTO BOOK_SHOP_USERS" + " (UER_NAME, PASSWORD)" + " VALUES(?,?)";

                   //STEP 4: Execute a query
                   System.out.println("Creating statement...");
                   pstmt = conn.prepareStatement(sql);
                   System.out.println("set params");

                   pstmt.setString(1, email);
                   pstmt.setString(2, password);

                   pstmt.executeUpdate();


                   //// Insert into Library Customers
                   sql =
                       "INSERT INTO BOOK_SHOP_CUSTOMER" + " (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL)" +
                       " VALUES(?,?,?,?)";

                   //STEP 4: Execute a query
                   System.out.println("Creating statement...");
                   pstmt = conn.prepareStatement(sql);
                   System.out.println("set params");

                   pstmt.setString(1, firstName);
                   pstmt.setString(2, lastName);
                   pstmt.setInt(3, phoneNumber);
                   pstmt.setString(4, email);
                  

                   pstmt.executeUpdate();
      System.out.println("end");
               pstmt.close();

               } catch (Exception e) {
                   e.printStackTrace();
                   return false;
               }

               return true;
    }
    public static boolean checkLogin() {
          Connection conn = null;
          PreparedStatement pstmt = null;
              boolean userFound = false;
           try{
          //STEP 2: Register JDBC driver
          //      Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
          //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

          //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                      Context ctx = new InitialContext();             
                        DataSource ds = (DataSource)ctx.lookup("BookShopAppDB");
                        conn = ds.getConnection();

              // Reading parameter from Bean in Session Scope
            LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().get("LoginBean");
                       
                     String userName = loginBean.getUserName();
                     String password = loginBean.getPassword();
                     

               String sql;
               sql = "SELECT COUNT(*) CNT"
                      +" FROM BOOK_SHOP_USERS"
                      +" WHERE UER_NAME=?"
                      +" AND PASSWORD=?";
             
                    //STEP 4: Execute a query
                    System.out.println("Creating statement...");
                    pstmt = conn.prepareStatement(sql);
                      System.out.println("set params");

                    pstmt.setString(1, userName);
                    pstmt.setString(2, password);
                       
                     ResultSet rs =  pstmt.executeQuery();
                          rs.next();
                          int cnt = rs.getInt("CNT");
               if(cnt > 0)
               {
                   userFound = true;
               System.out.println("User Found");
               }
               else {
                   System.out.println("User Not Found");
               }
               rs.close();
               pstmt.close();
               
          } catch (Exception e) {
               e.printStackTrace();
               return userFound;
          }
                      
         return userFound;
          
      }
    
    public static ArrayList<Products> getAllProducts() {
           Connection conn = null;
           PreparedStatement pstmt = null;
           ArrayList<Products> productsList = new ArrayList<Products>();
           
           
            try{
           //STEP 2: Register JDBC driver
           //      Class.forName(JDBC_DRIVER);

             //STEP 3: Open a connection
             System.out.println("Connecting to database...");
           //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

           //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                       Context ctx = new InitialContext();             
                         DataSource ds = (DataSource)ctx.lookup("BookShopAppDB");
                         conn = ds.getConnection();

                String sql;
                sql = "SELECT BOOK_ID, BOOK_NAME, BOOK_DESCRIPTION, BOOK_IMG, BOOK_PRICE"
                           +" FROM PRODUCTS";
              
                     //STEP 4: Execute a query
                     System.out.println("Creating statement...");
                     pstmt = conn.prepareStatement(sql);
                      
                        
                      ResultSet rs =  pstmt.executeQuery();
                while( rs.next()){
                    System.out.println("in loop .. ");
                           int productId = rs.getInt("BOOK_ID");
                           String productName = rs.getString("BOOK_NAME");
                           String productDescription = rs.getString("BOOK_DESCRIPTION");
                           String productImage = rs.getString("BOOk_IMG");
                           double productPrice = rs.getDouble("BOOK_PRICE");
                           Products p1 = new Products(productId,productName,productDescription,productImage,productPrice);
                    
                               productsList.add(p1);
                        }
                rs.close();
                pstmt.close();
                conn.close();
           } catch (Exception e) {
                e.printStackTrace();
           }
                       
          return productsList;
           
       }

        public static ArrayList<Employee> showEmployees() {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ArrayList<Employee> empList = new ArrayList<Employee>();
        try {
            
                System.out.println("Connecting to database...");
                //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                          Context ctx = new InitialContext();             
                            DataSource ds = (DataSource)ctx.lookup("BookShopAppDB");
                            conn = ds.getConnection();
            
                System.out.println("after Connecting to database...");
            String sql;
            
            sql= "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, ADDRESS, HIREDATE, SALARY, EMAIL, USER_NAME, PASSWORD"
                +" FROM EMPLOYEES";
      System.out.println("after sql");
                pstmt = conn.prepareStatement(sql);
               ResultSet rs= pstmt.executeQuery();
            System.out.println("after sql excute .. ");
            while(rs.next()) {
                System.out.println("in loop ..");
                int employee_Id = rs.getInt("EMPLOYEE_ID");
                String first_Name = rs.getNString("FIRST_NAME");
                String last_Name = rs.getNString("LAST_NAME");
                String address = rs.getNString("ADDRESS");
                Date hire_Date = rs.getDate("HIREDATE");
                double salary = rs.getDouble("SALARY");
                String email = rs.getNString("EMAIL");
                String user_Name = rs.getNString("USER_NAME");
                String password = rs.getNString("PASSWORD");
                
               empList.add( new Employee(employee_Id,first_Name,last_Name,address,hire_Date,salary,email,user_Name,password));
             System.out.println("after emplist object ..");   
            }
            
            rs.close();
            conn.close();
            pstmt.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            return empList;
        }
        public static ArrayList<Customers> showCustomers() {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ArrayList<Customers> customerList = new ArrayList<Customers>();
        try {
            
                System.out.println("Connecting to database...");
                //     conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                          Context ctx = new InitialContext();             
                            DataSource ds = (DataSource)ctx.lookup("BookShopAppDB");
                            conn = ds.getConnection();
            
                System.out.println("after Connecting to database...");
            String sql;
            
            sql= "SELECT USER_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL "
                +" FROM BOOK_SHOP_CUSTOMER";
        System.out.println("after sql");
                pstmt = conn.prepareStatement(sql);
               ResultSet rs= pstmt.executeQuery();
            System.out.println("after sql excute .. ");
            while(rs.next()) {
                System.out.println("in loop ..");
                int user_Id = rs.getInt("USER_ID");
                String first_Name = rs.getNString("FIRST_NAME");
                String last_Name = rs.getNString("LAST_NAME");
                int phone_Number = rs.getInt("PHONE_NUMBER");
                String email = rs.getNString("EMAIL");
                
                
               customerList.add( new Customers(user_Id,first_Name,last_Name,phone_Number,email));
             System.out.println("after emplist object ..");   
            }
            
            rs.close();
            conn.close();
            pstmt.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            return customerList;
        }
        public static boolean addEmployee() {
            Connection conn = null;
            PreparedStatement pstmt = null;
                   try {

                       //STEP 3: Open a connection
                       System.out.println("Connecting to database...");
                       //// Reading connection on Weblogic Data Source as (  LibraryAppDBDS   )
                       Context ctx = new InitialContext();
                       DataSource ds = (DataSource) ctx.lookup("BookShopAppDB");
                       conn = ds.getConnection();

               // Reading parameter from Bean in Session Scope
                EmployeesBean empBean =
                           (EmployeesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("EmployeesBean");

                      
                        String first_Name = empBean.getFirst_Name();
                        String last_Name = empBean.getLast_Name();
                       String address = empBean.getAddress();
                        Date hireDate = empBean.getHireDate();
                        double salary = empBean.getSalary();
                        String email = empBean.getEmail();
                        String user_Name = empBean.getUser_Name();
                        String password = empBean.getPassword();
                       
                      

                       //STEP 4: Execute a query
                       System.out.println("Creating statement...");
                       
                       System.out.println("set params");

                       pstmt.executeUpdate();


                       //// Insert into Library Customers
                       String sql;
                       sql =
                           "INSERT INTO EMPLOYEES" + " (FIRST_NAME, LAST_NAME, ADDRESS, HIREDATE, SALARY, EMAIL, USER_NAME, PASSWORD)" +
                           " VALUES(?,?,?,?,?,?,?,?)";

                       //STEP 4: Execute a query
                       System.out.println("Creating statement...");
                       pstmt = conn.prepareStatement(sql);
                       System.out.println("set params");

                       pstmt.setString(1, first_Name);
                       pstmt.setString(2, last_Name);
                       pstmt.setString(3, address);
                       pstmt.setDate(4, new java.sql.Date(hireDate.getTime()));
                       pstmt.setDouble(5, salary);
                       pstmt.setString(6, email);
                       pstmt.setString(7, user_Name);
                       pstmt.setString(8, password);
                    
                       pstmt.executeUpdate();
          System.out.println("end");
                   pstmt.close();

                   } catch (Exception e) {
                       e.printStackTrace();
                       return false;
                   }

                   return true;
        }
    }



