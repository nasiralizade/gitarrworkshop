package admin.login;

import admin.DB.DB;
import admin.DB.SetDB;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.credential.Password;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class Signup implements Serializable {
    @Inject
    private DB databaseExample;
    private String name;
    private String password;
    private int userId;  // Add a field to store the user ID



    private String password2;
    private String email;
    private String phone;


    public Signup(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String add(){
        //System.out.println(name+" "+phone+" "+email+" "+ password);
        //DB databaseExample = new DB();
        if((password.equals(password2))) {
            if(databaseExample.GetNameByName(name).isEmpty()){
                databaseExample.InsertMember(name, phone, email, password);
            }else return "SignUp";
        }else return "SignUp";

        return "LogIn";
    }

    public String LogIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);

        if (Objects.equals(name, "admin") && Objects.equals(password,databaseExample.GetPasswordByName("admin"))) {
            return "admin_home.xhtml";
        }

        if(Objects.equals(databaseExample.GetNameByName(name), name) && Objects.equals(databaseExample.GetPasswordByName(name), password) ){
            //return "/Login";
            session.setAttribute("username", name);
            return "/includes/loggedinpage";
            //return "client/home";
        }
        return "SignUp";
    }
}
