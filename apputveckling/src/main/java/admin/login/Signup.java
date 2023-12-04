package admin.login;

import admin.DB.DB;
import admin.DB.SetDB;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.io.Serializable;
@Named
@SessionScoped
public class Signup implements Serializable {
    //@Inject
    private DB databaseExample;
    private String name;
    private String password;
    private String email;
    private String phone;


    public Signup(){

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
        System.out.println(name+" "+phone+" "+email+" "+ password);

        //DB databaseExample = new DB();

        //databaseExample.InsertMember(name,phone,email);

        return "loggedinpage";

    }
}
