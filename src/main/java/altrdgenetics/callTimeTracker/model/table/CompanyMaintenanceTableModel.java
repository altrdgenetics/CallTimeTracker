/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.model.table;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class CompanyMaintenanceTableModel {
    
    public ObjectProperty ID;
    public SimpleBooleanProperty active;
    public StringProperty companyName;

    public CompanyMaintenanceTableModel() {
        this(null, false, null);
    }
    
    public CompanyMaintenanceTableModel(Object ID, boolean active, String manufacturer) {
        this.ID = new SimpleObjectProperty(ID);
        this.active = new SimpleBooleanProperty(active);
        this.companyName = new SimpleStringProperty(manufacturer);
    }

    //Checkmark Properties
    public SimpleBooleanProperty checkedProperty() {
        return this.active;
    }

    public java.lang.Boolean getChecked() {
        return this.checkedProperty().get();
    }

    public void setChecked(final java.lang.Boolean active) {
        this.checkedProperty().set(active);
    }

    
    //Getters and Setters
    
    public ObjectProperty getID() {
        return ID;
    }

    public void setID(ObjectProperty ID) {
        this.ID = ID;
    }

    public SimpleBooleanProperty getActive() {
        return active;
    }

    public void setActive(SimpleBooleanProperty active) {
        this.active = active;
    }

    public StringProperty getCompanyName() {
        return companyName;
    }

    public void setCompanyName(StringProperty companyName) {
        this.companyName = companyName;
    }
        
}
