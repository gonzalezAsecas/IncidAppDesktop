/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gorka Redondo
 */
@XmlRootElement(name="incidentBean")
public class IncidentBean implements Serializable{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty title;
    private byte[] photo;
    private final SimpleStringProperty description;
    private final SimpleStringProperty comment;
    private final SimpleObjectProperty<Date> createDate;
    private Date endDate;
    private final SimpleObjectProperty<Estate> estate;
    private final SimpleObjectProperty<UserBean> user;
    private final SimpleObjectProperty<LocationBean> location;
    private final SimpleObjectProperty<TypeBean> type;
    private final SimpleObjectProperty<List<UserBean>> users;

    public IncidentBean() {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.comment = new SimpleStringProperty();
        this.createDate = new SimpleObjectProperty();
        this.estate = new SimpleObjectProperty();
        this.user = new SimpleObjectProperty();
        this.location = new SimpleObjectProperty();
        this.type = new SimpleObjectProperty();
        this.users = new SimpleObjectProperty<List<UserBean>>();
    }
    
    public IncidentBean(String title, String description, String comment, Date createDate, Estate estate, UserBean user, LocationBean location, TypeBean type) {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty(title);
        //this.photo = photo;
        this.description = new SimpleStringProperty(description);
        this.comment = new SimpleStringProperty(comment);
        this.createDate = new SimpleObjectProperty(createDate);
        //this.endDate = endDate;
        this.estate = new SimpleObjectProperty(estate);
        this.user = new SimpleObjectProperty(user);
        this.location = new SimpleObjectProperty(location);
        this.type = new SimpleObjectProperty(type);
        this.users = new SimpleObjectProperty<List<UserBean>>();
    }
    
    public Integer getId() {
        return this.id.get();
    }
    
    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public String getTitle() {
        return this.title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    public String getDescription() {
        return this.description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getComment() {
        return this.comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public Date getCreateDate() {
        return this.createDate.get();
    }

    public void setCreateDate(Date createDate) {
        this.createDate.set(createDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Estate getEstate() {
        return this.estate.get();
    }
    
    public void setEstate(Estate estate) {
        this.estate.set(estate);
    }
    
    public UserBean getUser() {
        return this.user.get();
    }

    public void setUser(UserBean user) {
        this.user.set(user);
    }
    
    public LocationBean getLocation() {
        return this.location.get();
    }
    
    public void setLocation(LocationBean location) {
        this.location.set(location);
    }
    
    public TypeBean getType() {
        return this.type.get();
    }
    
    public void setType(TypeBean type) {
        this.type.set(type);
    }

    public List<UserBean> getUsers() {
        return this.users.get();
    }

    public void setUsers(List<UserBean> users) {
        this.users.set(users);
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
