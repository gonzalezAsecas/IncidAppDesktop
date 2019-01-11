/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jon Gonzalez
 */
@Entity
@Table(name="townhall", schema="incidapp")
@XmlRootElement
@NamedQuery(name="findAllTownHalls", 
            query="SELECT s FROM TownHallBean s")
public class TownHallBean implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String locality;
    private String email;
    private String telephoneNumber;
    @OneToMany(mappedBy="townHall")
    private List<LocationBean> locations;
    @OneToMany(mappedBy="th")
    private List<UserBean> users;

    public TownHallBean(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash = 47 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
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
        return "TownHallBean{" + "idTownHall=" + id + ", locality=" + locality + ", email=" + email + ", telephoneNumber=" + telephoneNumber + ", locations=" + locations + ", users=" + users + '}';
    }
}
