/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import implementations.IncidentImplementation;
import implementations.LocationImplementation;
import implementations.TownHallImplementation;
import implementations.TypeImplementation;
import implementations.UserImplementation;
import interfaces.iIncident;
import interfaces.iLocation;
import interfaces.iTownHall;
import interfaces.iType;
import interfaces.iUser;

/**
 *
 * @author Jon Gonzalez
 */
public class LogicFactory {
    public static iIncident getiIncident(){
        return new IncidentImplementation();
    }
    public static iTownHall getiTownHall(){
        return new TownHallImplementation();
    }
    public static iType getiType(){
        return new TypeImplementation();
    }
    public static iLocation getiLocation(){
        return new LocationImplementation();
    }
    public static iUser getiUser(){
        return new UserImplementation();
    }
}
