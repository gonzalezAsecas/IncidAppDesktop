/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lander Lluvia
 */
@XmlRootElement(name="townwhall")
public class TownHallBean implements Serializable{
    private final SimpleIntegerProperty idTownHall;
    private final SimpleStringProperty locality;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telephoneNumber;

    public TownHallBean(){
        this.idTownHall = new SimpleIntegerProperty();
        this.locality = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telephoneNumber = new SimpleStringProperty();
    }
    
    public TownHallBean(Integer idTownHall, String locality, String email, String telephoneNumber) {
        this.idTownHall = new SimpleIntegerProperty(idTownHall);
        this.locality = new SimpleStringProperty(locality);
        this.email = new SimpleStringProperty(email);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
    }

    public Integer getIdTownHall() {
        return this.idTownHall.get();
    }

    public void setIdTownHall(Integer idTownHall) {
        this.idTownHall.set(idTownHall);
    }

    public String getLocality() {
        return this.locality.get();
    }

    public void setLocality(String locality) {
        this.locality.set(locality);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber.get();
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
    }
}
