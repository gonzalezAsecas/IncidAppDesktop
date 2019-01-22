/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jon Gonzalez
 */
@XmlRootElement(name="townHall")
public class TownHallBean implements Serializable{
    private Integer idTownHall;
    private String locality;
    private String email;
    private String telephoneNumber;
    private List<LocationBean> locations;
    private List<UserBean> users;

    public TownHallBean(){}

    public Integer getIdTownHall() {
        return idTownHall;
    }

    public void setIdTownHall(Integer idTownHall) {
        this.idTownHall = idTownHall;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @XmlTransient
    public List<LocationBean> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationBean> locations) {
        this.locations = locations;
    }

    @XmlTransient
    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idTownHall);
        hash = 47 * hash + Objects.hashCode(this.locality);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.telephoneNumber);
        hash = 47 * hash + Objects.hashCode(this.locations);
        hash = 47 * hash + Objects.hashCode(this.users);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TownHallBean other = (TownHallBean) obj;
        if (!Objects.equals(this.locality, other.locality)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telephoneNumber, other.telephoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.idTownHall, other.idTownHall)) {
            return false;
        }
        if (!Objects.equals(this.locations, other.locations)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return locality;
    }
}
