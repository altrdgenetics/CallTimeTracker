/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.model.sql;

import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class PhoneCallModel {
    private int id;
    private int companyid;
    private Timestamp callstarttime;
    private Timestamp callendtime;
    private String calldescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public Timestamp getCallstarttime() {
        return callstarttime;
    }

    public void setCallstarttime(Timestamp callstarttime) {
        this.callstarttime = callstarttime;
    }

    public Timestamp getCallendtime() {
        return callendtime;
    }

    public void setCallendtime(Timestamp callendtime) {
        this.callendtime = callendtime;
    }

    public String getCalldescription() {
        return calldescription;
    }

    public void setCalldescription(String calldescription) {
        this.calldescription = calldescription;
    }
    
    
}
