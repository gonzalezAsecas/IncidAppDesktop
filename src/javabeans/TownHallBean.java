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
 * @author Lander Lluvia
 */
@XmlRootElement(name="townwhall")
public class TownHallBean implements Serializable{
    //ToDo: no se puede hacer un constructor con solo algunos datos?
    //private final SimpleIntegerProperty id;
    private final SimpleStringProperty locality;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telephoneNumber;
    private final SimpleObjectProperty<List<LocationBean>> locations;
    private final SimpleObjectProperty<List<UserBean>> users;

    public TownHallBean(){
        //this.id = new SimpleIntegerProperty();
        this.locality = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.telephoneNumber = new SimpleStringProperty();
        this.locations = new SimpleObjectProperty<List<LocationBean>>();
        this.users = new SimpleObjectProperty<List<UserBean>>();
    }
    
    public TownHallBean(String locality, String email, String telephoneNumber) {
        this.locality = new SimpleStringProperty(locality);
        this.email = new SimpleStringProperty(email);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
    }

    
    public Integer getId() {
        return this.id.get();
    }
    
    public void setId(Integer id) {
        this.id.set(id);
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
    
    public List<LocationBean> getLocations(List<LocationBean> locations) {
        return this.locations.get();
    }
    
    public void setLocation(List<LocationBean> locations) {
        this.locations.set(locations);
    }
    
    public List<UserBean> getUsers(List<UserBean> users) {
        return this.users.get();
    }
    
    public void setUsers(List<UserBean> users) {
        this.users.set(users);
    }
}
