/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jon Gonzalez
 */
@XmlRootElement
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private final SimpleStringProperty login;
    private final SimpleStringProperty email;
    private byte[] password;
    private final SimpleStringProperty fullName;
    private Status status;
    private Privilege privilege;
    private Date lastAccess;
    private Date lastPasswordChange;
    private String dni;
    private String street;
    private TownHallBean th;
    private List<IncidentBean> incidents;
    private List<IncidentBean> signatureIncidents;
    
    public UserBean() {
        this.login = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
    }
    
    public UserBean(String login, String email, String fullName, TownHallBean th ) {
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.fullName = new SimpleStringProperty(fullName);
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public TownHallBean getTH() {
        return th;
    }

    public void setTH(TownHallBean th) {
        this.th = th;
    }

    @XmlTransient
    public List<IncidentBean> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents = incidents;
    }

    @XmlTransient
    public List<IncidentBean> getSignatureIncidents() {
        return signatureIncidents;
    }

    public void setSignatureIncidents(List<IncidentBean> signatureIncidents) {
        this.signatureIncidents = signatureIncidents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.login);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.fullName);
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + Objects.hashCode(this.privilege);
        hash = 37 * hash + Objects.hashCode(this.lastAccess);
        hash = 37 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 37 * hash + Objects.hashCode(this.dni);
        hash = 37 * hash + Objects.hashCode(this.street);
        hash = 37 * hash + Objects.hashCode(this.th);
        hash = 37 * hash + Objects.hashCode(this.incidents);
        hash = 37 * hash + Objects.hashCode(this.signatureIncidents);
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
        final UserBean other = (UserBean) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.privilege != other.privilege) {
            return false;
        }
        if (!Objects.equals(this.lastAccess, other.lastAccess)) {
            return false;
        }
        if (!Objects.equals(this.lastPasswordChange, other.lastPasswordChange)) {
            return false;
        }
        if (!Objects.equals(this.th, other.th)) {
            return false;
        }
        if (!Objects.equals(this.incidents, other.incidents)) {
            return false;
        }
        if (!Objects.equals(this.signatureIncidents, other.signatureIncidents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonBean{" + "idPerson=" + id + ", login=" + login + ", email=" + email + ", password=" + password + ", fullName=" + fullName + ", status=" + status + ", privilege=" + privilege + ", lastAccess=" + lastAccess + ", lastPasswordChange=" + lastPasswordChange + ", dni=" + dni + ", street=" + street + ", townHall=" + th + ", incidents=" + incidents + ", signatureIncidents=" + signatureIncidents + '}';
    }
}
