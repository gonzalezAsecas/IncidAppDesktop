/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jon Gonzalez
 */
@XmlRootElement(name="type")
public class TypeBean implements Serializable{
    private Integer idType;
    private final SimpleStringProperty name;
    private Integer severity;
    private List<IncidentBean> incidents;

    public TypeBean(){
        this.name = new SimpleStringProperty();
    }

    public TypeBean(Integer idType, String name, Integer severity, List<IncidentBean> incidents) {
        this.idType = idType;
        this.name = new SimpleStringProperty(name);;
        this.severity = severity;
        this.incidents = incidents;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getName() {
        return this.name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }
    
    public List<IncidentBean> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<IncidentBean> incidents) {
        this.incidents = incidents;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idType);
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.severity);
        hash = 61 * hash + Objects.hashCode(this.incidents);
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
        final TypeBean other = (TypeBean) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.idType, other.idType)) {
            return false;
        }
        if (!Objects.equals(this.severity, other.severity)) {
            return false;
        }
        if (!Objects.equals(this.incidents, other.incidents)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeBean{" + "idType=" + idType + ", name=" + name + ", severity=" + severity + ", incidents=" + incidents + '}';
    }
}
