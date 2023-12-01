package admin.members;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Date;

@Named
@SessionScoped
public class MemberInfo implements Serializable {

    private int memberID;
    private String clientName;
    private String email;
    private String phone;
    private String address;
    private String status;
    private Date start_date;
    private Date end_date;


    public MemberInfo(int memberID, String clientName, String email, String phone, String address) {
        this.memberID = memberID;
        this.clientName = clientName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    private String case_typ;

    // Constructors, getters, and setters

    public MemberInfo() {
        // Default constructor
    }

    public String getCase_typ() {
        return case_typ;
    }

    public void setCase_typ(String case_typ) {
        this.case_typ = case_typ;
    }



    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
