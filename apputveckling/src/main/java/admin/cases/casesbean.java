package admin.cases;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.Serializable;

@Named
@SessionScoped
public class casesbean implements Serializable {
    private cases my_case;

    public casesbean(){
        my_case = new cases();

        my_case.setCase_id(1);

        my_case.setCase_desc("Khara");
    }

    public cases getMy_case() {
        return my_case;
    }

    public void setMy_case(cases my_case) {
        this.my_case = my_case;
    }


}