package Book.beans;

import Book.classes.JDBCCLASS;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class LoginBean implements Serializable {
    
    private String userName;
    private String password;
    private boolean showItem;
    public LoginBean() {
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setShowItem(boolean showItem) {
        this.showItem = showItem;
    }

    public boolean isShowItem() {
        return showItem;
    }

    public Object checkLogin(){
          boolean loginOk = JDBCCLASS.checkLogin();
          
          if(loginOk){
              if(userName.equals("ezzatadmin")&&password.equals("ezzat90")){
                 setShowItem(true);
                  return "AdminPage";
              }
              setShowItem(true);
            return "MyAccount";
          }else
          return null;
    }

    public Object logout() {
        // Add event code here...
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index2";
    }
}
