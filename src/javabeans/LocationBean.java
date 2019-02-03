/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gorka Redondo
 */
@XmlRootElement(name="locationBean")
public class LocationBean implements Serializable{
    private final SimpleIntegerProperty idLocation;
    private final SimpleStringProperty street;
    private final SimpleObjectProperty<TownHallBean> townHall;
    private final SimpleObjectProperty<List<IncidentBean>> incidents;

    public LocationBean() {
        this.idLocation = new SimpleIntegerProperty();
        this.street = new SimpleStringProperty();
        this.townHall = new SimpleObjectProperty();
        this.incidents = new SimpleObjectProperty<List<IncidentBean>>();
    }

    public LocationBean(String street, TownHallBean townHall) {
        this.idLocation = new SimpleIntegerProperty();
        this.street = new SimpleStringProperty(street);
        this.townHall = new SimpleObjectProperty(townHall);
        this.incidents = new SimpleObjectProperty<List<IncidentBean>>();
    }

    public Integer getIdLocation() {
        return this.idLocation.get();
    }
    
    public void setIdLocation(Integer idLocation) {
        this.idLocation.set(idLocation);
    }

    public String getStreet() {
        return this.street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public TownHallBean getTownHall() {
        return this.townHall.get();
    }

    public void setTownHall(TownHallBean townHall) {
        this.townHall.set(townHall);
    }

    public List<IncidentBean> getIncidents() {
        return this.incidents.get();
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents.set(incidents);
    }

    @Override
    public String toString() {
        return this.getStreet();
    }
}
