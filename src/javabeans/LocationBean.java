/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lander Lluvia
 */
@XmlRootElement(name="location")
public class LocationBean implements Serializable{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty street;
    private final SimpleObjectProperty<TownHallBean> townHall;
    private final SimpleObjectProperty<List<IncidentBean>> incidents;

    public LocationBean(){
        this.id = new SimpleIntegerProperty();
        this.street = new SimpleStringProperty();
        this.townHall = new SimpleObjectProperty<TownHallBean>();
        this.incidents = new SimpleObjectProperty<List<IncidentBean>>();
    }

    public Integer getId() {
        return this.id.get();
    }

    public void setIdLocation(Integer idLocation) {
        this.id.set(idLocation);
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
}
