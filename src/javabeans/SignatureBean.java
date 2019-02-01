/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Jon Gonzalez
 */
public class SignatureBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idPerson;
    private Integer idIncident;
    private Timestamp signatureDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getIdIncident() {
        return idIncident;
    }

    public void setIdIncident(Integer idIncident) {
        this.idIncident = idIncident;
    }

    public Timestamp getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Timestamp signatureDate) {
        this.signatureDate = signatureDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.idPerson);
        hash = 23 * hash + Objects.hashCode(this.idIncident);
        hash = 23 * hash + Objects.hashCode(this.signatureDate);
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
        final SignatureBean other = (SignatureBean) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idPerson, other.idPerson)) {
            return false;
        }
        if (!Objects.equals(this.idIncident, other.idIncident)) {
            return false;
        }
        if (!Objects.equals(this.signatureDate, other.signatureDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SignatureBean{" + "id=" + id + ", idPerson=" + idPerson + ", idIncident=" + idIncident + ", signatureDate=" + signatureDate + '}';
    }
}
