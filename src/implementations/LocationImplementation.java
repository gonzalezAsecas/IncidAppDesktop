/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import exceptions.CreateException;
import exceptions.ReadException;
import interfaces.iLocation;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.LocationBean;
import javax.ws.rs.core.GenericType;
import restfuls.LocationRestFul;

/**
 *
 * @author Gorka Redondo
 */
public class LocationImplementation implements iLocation{
    
    //REST incident web client
    private LocationRestFul webClient;
    
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");

    public LocationImplementation() {
        webClient = new LocationRestFul();
    }
    
    /**
     * 
     * @param location
     * @throws CreateException
     */
    @Override
    public void createLocation(LocationBean location) throws CreateException {
        try{
            LOGGER.info("Creating location");
            webClient.create(location);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "Exception creating location",ex.getMessage());
            throw new CreateException("Error creating location:\n"+ex.getMessage());
        }
    }
    
    /**
     * 
     * @return locations
     * @throws ReadException 
     */
    @Override
    public Collection<LocationBean> findAllLocations() throws ReadException {
        List<LocationBean> locations = null;
        try{
            locations = webClient.findAll(new GenericType<List<LocationBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding all types",ex.getMessage());
            throw new ReadException("Error finding all types:\n"+ex.getMessage());
        }
        return locations;
    }
    
    /**
     * 
     * @param location
     * @return loc
     * @throws ReadException 
     */
    @Override
    public LocationBean findLocationByStreet(LocationBean location) throws ReadException {
        List<LocationBean> locations = null;
        LocationBean loc = null;
        try{
            locations = webClient.findAll(new GenericType<List<LocationBean>>() {});
            for(LocationBean l : locations){
                if(l.getTownHall().getLocality().equalsIgnoreCase(location
                    .getTownHall().getLocality()) && l.getStreet().trim()
                    .equalsIgnoreCase(location.getStreet().trim())) {
                    loc = l;
                    break;
                }
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding location",ex.getMessage());
            throw new ReadException("Error finding location:\n"+ex.getMessage());
        }
        return loc;
    }
}
