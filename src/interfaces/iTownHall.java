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
import java.util.List;
import javabeans.TownHallBean;

/**
 *
 * @author Lander Lluvia
 */
public interface iTownHall {
     public void createTownHall(TownHallBean townhall) throws CreateException;

    public void editTownHall(TownHallBean townhall) throws UpdateException;

    public void removeTownHall(TownHallBean townhall) throws DeleteException;

    public TownHallBean findTownHallbyId(TownHallBean townhall) throws ReadException;
    
    public TownHallBean findTownHallByName(TownHallBean townhall) throws ReadException;

    public Collection<TownHallBean> findAllTownHalls() throws ReadException;
    
    public void townHallAlreadyExists(String name) throws ReadException;
}
