/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import static implementations.FTPCliente.LOGGER;
import exceptions.ReadException;
import interfaces.iTownHall;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javabeans.TownHallBean;
import javax.ws.rs.core.GenericType;
import restfuls.TownHallRestFulClient;

/**
 *
 * @author Jon Gonzalez
 */
public class TownHallImplementation implements iTownHall {
    //REST incident web client
    private TownHallRestFulClient webClient;

    public TownHallImplementation() {
        webClient = new TownHallRestFulClient();
    }
    
    @Override
    public Collection<TownHallBean> findAllTownHalls() throws ReadException {
        List<TownHallBean> townHalls = null;
        try{
            townHalls = webClient.findAll(new GenericType<List<TownHallBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding all types",ex.getMessage());
            throw new ReadException("Error finding all types:\n"+ex.getMessage());
        }
        return townHalls;
    }
    
    
    
}
