package admin.cases;

public class cases {
    int case_id;
    String case_desc;
    private boolean editing;
    String case_status;
    String case_start_date;
    String case_end_date;
    float case_hours;
    public String getCase_desc(){
        return case_desc;
    }
    public String getCase_start_date(){
        return case_start_date;
    }
    public String getCase_end_date(){
        return case_end_date;
    }
    public String getCase_status(){
        return case_status;
    }
    public int getCase_id(){
        return case_id;
    }
    public float getCase_hours(){
        return case_hours;
    }
    public void setCase_desc(String case_desc){
        this.case_desc = case_desc;
    }
    public void setCase_status(String stat){
        this.case_status = stat;
    }
    public void setCase_id(int caseId){
        this.case_id = caseId;
    }
    public void setCase_start_date(String startDate){
        this.case_start_date = startDate;
    }
    public void setCase_end_date(String endDate){
        this.case_end_date = endDate;
    }
    public void setCase_hours(float hours){
        this.case_hours = hours;
    }

    public void editCaseDescription(String newDescription) {
        if (editing){
            this.case_desc = newDescription;
        }
    }





    public static void main(String[] arg){


        cases myCase = new cases();
        myCase.setCase_id(1);
        myCase.setCase_desc("Old description");

        System.out.println("Old Description: " + myCase.getCase_desc());
        myCase.editCaseDescription("New description");

        System.out.println("Updated Description: " + myCase.getCase_desc());

    }
}
