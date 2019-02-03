/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.CreateException;
import exceptions.ReadException;
import java.util.Collection;
import javabeans.LocationBean;

/**
 *
 * @author Jon Gonzalez
 */
public interface iLocation {
    public void createLocation(LocationBean location) throws CreateException;
    
    public Collection<LocationBean> findAllLocations() throws ReadException;
    
    public LocationBean findLocationByStreet(LocationBean location) throws ReadException;
}
