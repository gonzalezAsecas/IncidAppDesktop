/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lander Lluvia
 */
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    //private SimpleIntegerProperty idPerson;
    private SimpleStringProperty login;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private SimpleStringProperty fullName;
    private SimpleObjectProperty<Status> status;
    private SimpleObjectProperty<Privilege> privilege;
    private SimpleObjectProperty<Date> lastAccess;
    private SimpleObjectProperty<Date> lastPasswordChange;
    private SimpleStringProperty dni;
    private SimpleStringProperty street;
    private SimpleObjectProperty<TownHallBean> townHall;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public UserBean() {
        this.login = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
        this.status = new SimpleObjectProperty<Status>();
        this.privilege = new SimpleObjectProperty<Privilege>();
        this.lastAccess = new SimpleObjectProperty<Date>();
        this.lastPasswordChange = new SimpleObjectProperty<Date>();
        this.dni = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.townHall = new SimpleObjectProperty<TownHallBean>();
    }
    
    public UserBean(String login, String email, String password, String fullName,
            Date lastPasswordChange, String street, TownHallBean townhall){
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.fullName = new SimpleStringProperty(fullName);
        this.lastPasswordChange = new SimpleObjectProperty<Date>(lastPasswordChange);
        this.street = new SimpleStringProperty(street);
        this.townHall = new SimpleObjectProperty<TownHallBean>(townhall);
        
    }
    
    public String getLogin() {
        return this.login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getFullName() {
        return this.fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public Status getStatus() {
        return this.status.get();
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }

    public Privilege getPrivilege() {
        return this.privilege.get();
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege.set(privilege);
    }

    public Date getLastAccess() {
        return this.lastAccess.get();
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess.set(lastAccess);
    }

    public Date getLastPasswordChange() {
        return this.lastPasswordChange.get();
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange.set(lastPasswordChange);
    }

    public String getDni() {
        return this.dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
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
}
