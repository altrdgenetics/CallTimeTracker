/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altrdgenetics.callTimeTracker.model.table;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class MainWindowTableModel {
    
    public ObjectProperty object = new SimpleObjectProperty(null);
    public StringProperty startTime = new SimpleStringProperty(null);
    public StringProperty company = new SimpleStringProperty(null);
    public StringProperty description = new SimpleStringProperty(null);
    public StringProperty duration = new SimpleStringProperty(null);

    public MainWindowTableModel(Object object, String startTime, String company, String description, String duration) {
        this.object = new SimpleObjectProperty(object);
        this.startTime = new SimpleStringProperty(startTime);
        this.company = new SimpleStringProperty(company);
        this.description = new SimpleStringProperty(description);
        this.duration = new SimpleStringProperty(duration);
    }

    public ObjectProperty getObject() {
        return object;
    }

    public void setObject(ObjectProperty object) {
        this.object = object;
    }

    public StringProperty getStartTime() {
        return startTime;
    }

    public void setStartTime(StringProperty startTime) {
        this.startTime = startTime;
    }

    public StringProperty getCompany() {
        return company;
    }

    public void setCompany(StringProperty company) {
        this.company = company;
    }

    public StringProperty getDescription() {
        return description;
    }

    public void setDescription(StringProperty description) {
        this.description = description;
    }

    public StringProperty getDuration() {
        return duration;
    }

    public void setDuration(StringProperty duration) {
        this.duration = duration;
    }
 
}
