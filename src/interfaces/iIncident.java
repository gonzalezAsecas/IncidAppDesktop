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
import java.util.List;
import javabeans.IncidentBean;

/**
 *
 * @author Jon Gonzalez
 */
public interface iIncident {
    public IncidentBean createIncident(IncidentBean incident) throws CreateException;

    public void editIncident(IncidentBean incident) throws UpdateException;

    public void removeIncident(IncidentBean incident) throws DeleteException;

    public IncidentBean findIncidentbyId(IncidentBean incident) throws ReadException;

    public List<IncidentBean> findAllIncidents() throws ReadException;
    
    public List<IncidentBean> findIncidentsbyFilter(IncidentBean incident) throws ReadException;
}
