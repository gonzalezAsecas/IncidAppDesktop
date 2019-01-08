/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javabeans.TownHallBean;

/**
 *
 * @author Jon Gonzalez
 */
public interface iTownHall {
     public TownHallBean createTownHall(TownHallBean townhall) throws CreateException;

    public void editTownHall(TownHallBean townhall) throws UpdateException;

    public void removeTownHall(TownHallBean townhall) throws DeleteException;

    public TownHallBean findTownHallbyId(TownHallBean townhall) throws ReadException;

    public List<TownHallBean> findAllTownHalls() throws ReadException;
}
