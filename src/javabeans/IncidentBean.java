/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gorka Redondo
 */
@XmlRootElement(name="incident")
public class IncidentBean implements Serializable{
    private Integer idIncident;
    private final SimpleStringProperty title;
    private byte[] photo;
    private final SimpleStringProperty description;
    private String comment;
    private Date createDate;
    private Date endDate;
    private final SimpleObjectProperty<Estate> estate;
    private UserBean user;
    private final SimpleObjectProperty<LocationBean> location;
    private final SimpleObjectProperty<TypeBean> type;
    private List<UserBean> users;

    public IncidentBean() {
        //this.idIncident = idIncident;
        this.title = new SimpleStringProperty();
        //this.photo = photo;
        this.description = new SimpleStringProperty();
        //this.comment = comment;
        //this.createDate = createDate;
        //this.endDate = endDate;
        this.estate = new SimpleObjectProperty();
        //this.user = user;
        this.location = new SimpleObjectProperty();
        this.type = new SimpleObjectProperty();
        //this.users = users;
    }
    
    public IncidentBean(Integer idIncident, String title, byte[] photo, 
        String description, String comment, Date createDate, Date endDate, 
        Estate estate, UserBean user, LocationBean location, TypeBean type, 
        List<UserBean> users, Integer votes) {
        this.idIncident = idIncident;
        this.title = new SimpleStringProperty(title);
        this.photo = photo;
        this.description = new SimpleStringProperty(description);
        this.comment = comment;
        this.createDate = createDate;
        this.endDate = endDate;
        this.estate = new SimpleObjectProperty(estate);
        this.user = user;
        this.location = new SimpleObjectProperty(location);
        this.type = new SimpleObjectProperty(type);
        this.users = users;
    }

    public Integer getIdIncident() {
        return idIncident;
    }

    public void setIdIncident(Integer idIncident) {
        this.idIncident = idIncident;
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
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idIncident);
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Arrays.hashCode(this.photo);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.comment);
        hash = 37 * hash + Objects.hashCode(this.createDate);
        hash = 37 * hash + Objects.hashCode(this.endDate);
        hash = 37 * hash + Objects.hashCode(this.estate);
        hash = 37 * hash + Objects.hashCode(this.user);
        hash = 37 * hash + Objects.hashCode(this.location);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.users);
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
        final IncidentBean other = (IncidentBean) obj;
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.idIncident, other.idIncident)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Arrays.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.createDate, other.createDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.estate, other.estate)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IncidentBean{" + "idIncident=" + idIncident + ", title=" + title + ", description=" + description + ", comment=" + comment + ", createDate=" + createDate + ", endDate=" + endDate + ", estate=" + estate + ", user=" + user + ", location=" + location + ", type=" + type + ", users=" + users + '}';
    }
}
