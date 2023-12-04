package admin.cases;

import java.io.Serializable;

public class Cases implements Serializable {

    // The serialVersionUID is a universal version identifier for a Serializable class.
    //private static final long serialVersionUID = 1L;

    private int case_id;
    private String case_desc;
    private String case_status;
    private String case_type;

    // Constructors, getters, and setters

    public Cases() {
        // Default constructor
    }

    public Cases(int case_id, String case_desc, String case_status, String case_type) {
        this.case_id = case_id;
        this.case_desc = case_desc;
        this.case_status= case_status;
        this.case_type = case_type;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getCase_desc() {
        return case_desc;
    }

    public void setCase_desc(String case_desc) {
        this.case_desc = case_desc;
    }

    public String getCase_status() {
        return case_status;
    }

    public void setCase_status(String case_status) {
        this.case_status = case_status;
    }

    public String getCase_type() {
        return case_type;
    }

    public void setCase_type(String case_type) {
        this.case_type = case_type;
    }
}


