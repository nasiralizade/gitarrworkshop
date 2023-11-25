package admin.create_plt;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.swing.*;

@Named
@RequestScoped
public class CreateImageBean implements java.io.Serializable {
    public CreateImageBean() {
    }

    public void createImage() {
        ReadData_db readData_db = new ReadData_db();
        readData_db.createImage();
    }

    public String getCreateImageAction() {
        createImage();
        return "admin_calendar";
    }

}
