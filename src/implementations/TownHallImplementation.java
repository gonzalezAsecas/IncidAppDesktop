/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import interfaces.iTownHall;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import restfuls.TownHallRestFul;

/**
 *
 * @author Lander Lluvia
 */
public class TownHallImplementation implements iTownHall{
    
    private TownHallRestFul webClient;
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    public TownHallImplementation(){
        webClient = new TownHallRestFul();
    }

    @Override
    public void createTownHall(TownHallBean townhall) throws CreateException {
       try{
           LOGGER.info("TownhallImplementation: Creating a townhall from REST service.");
           webClient.create(townhall);
       }catch(Exception ex){
           LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception creating a townhall",
                    ex.getMessage());
           throw new CreateException("Error creating a townhall");
       }
    }

    @Override
    public void editTownHall(TownHallBean townhall) throws UpdateException {
        try{
            LOGGER.info("TownhallImplementation: Editing a townhall from REST service.");
            webClient.edit(townhall);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception editing a townhall",
                    ex.getMessage());
            throw new UpdateException("Error editing a townhall");
        }
    }

    @Override
    public void removeTownHall(TownHallBean townhall) throws DeleteException {
        try{
            LOGGER.info("TownhallImplementation: Deleting a townhall from REST service.");
            webClient.remove(townhall.getId());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception removing a townhall",
                    ex.getMessage());
            throw new DeleteException("Error deleting a townhall");
        }
    }

    @Override
    public TownHallBean findTownHallbyId(TownHallBean townhall) throws ReadException {
        try{
            LOGGER.info("TownhallImplementation: Finding a townhall by id from REST service.");
            webClient.find(TownHallBean, townhall.getId());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception finding a townhall by id",
                    ex.getMessage());
        }
    }

    @Override
    public List<TownHallBean> findAllTownHalls() throws ReadException {
        List<TownHallBean> townhalls = null;
        try{
            LOGGER.info("TownhallImplementation: Finding all townhalls from REST service.");
            townhalls = webClient.findAll(new Class<List<TownHallBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception finding all townhalls",
                    ex.getMessage());
            throw new ReadException("Error finding all townhalls");
        }
        return townhalls;
    }

    @Override
    public void townHallAlreadyExists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
