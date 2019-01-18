/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import static implementations.FTPCliente.LOGGER;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import interfaces.iIncident;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javabeans.IncidentBean;
import javabeans.LocationBean;
import javax.ws.rs.core.GenericType;
import restfuls.IncidentRestFulClient;

/**
 *
 * @author Gorka Redondo
 */
public class IncidentImplementation implements iIncident {
    
    //REST incident web client
    private IncidentRestFulClient webClient;

    public IncidentImplementation() {
        webClient = new IncidentRestFulClient();
    }
    
    
    /**
     * 
     * @param incident
     * @throws CreateException
     */
    @Override
    public void createIncident(IncidentBean incident) throws CreateException {
        try{
            LOGGER.info("Creating incident");
            //Send incident data to web client for creation. 
            webClient.create(incident);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "Exception creating incident",ex.getMessage());
            throw new CreateException("Error creating incident:\n"+ex.getMessage());
        }
    }
    
    /**
     * 
     * @param incident
     * @throws UpdateException 
     */
    @Override
    public void editIncident(IncidentBean incident) throws UpdateException {
        try{
            LOGGER.info("Updating user");
            webClient.edit(incident);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "UsersManager: Exception updating user, {0}",ex.getMessage());
            throw new UpdateException("Error updating incident:\n"+ex.getMessage());
        }
    }
    
    
    @Override
    public void removeIncident(IncidentBean incident) throws DeleteException {
        try{
            LOGGER.info("Deleting  incident");
            webClient.remove(incident.getIdIncident().toString());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "Exception deleting incident",ex.getMessage());
            throw new DeleteException("Error deleting incident:\n"+ex.getMessage());
        }
    }

    @Override
    public Collection<IncidentBean> findAllIncidents() throws ReadException {
        List<IncidentBean> incidents = null;
        try{
            incidents = webClient.findAll(new GenericType<List<IncidentBean>>() {});
        }catch(Exception ex){
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE,
                "Exception finding all incidents",ex.getMessage());
            throw new ReadException("Error finding all incidents:\n"+ex.getMessage());
        }
        return incidents;
    }

    @Override
    public Collection<IncidentBean> findIncidentsbyFilter(LocationBean location) throws ReadException {
        List<IncidentBean> incidentsAll = null;
        List<IncidentBean> incidents = null;
        try{
            incidentsAll = webClient.findAll(new GenericType<List<IncidentBean>>() {});
            for(IncidentBean in : incidentsAll){
                if(in.getLocation().equals(location)){
                    incidents.add(in);
                }
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding all incidents",ex.getMessage());
            throw new ReadException("Error finding all incidents:\n"+ex.getMessage());
        }
        return incidents;
    }
}
