/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.Collection;
import javabeans.IncidentBean;
import javabeans.UserBean;

/**
 *
 * @author Gorka Redondo
 */
public interface iIncident {
    public void createIncident(IncidentBean incident) throws CreateException;
    
    public void editIncident(IncidentBean incident) throws UpdateException;
    
    public void removeIncident(IncidentBean incident) throws DeleteException;
    
    public Collection<IncidentBean> findAllIncidents() throws ReadException;
    
    public Collection<IncidentBean> findIncidentsByUser(UserBean user) throws ReadException;
}
