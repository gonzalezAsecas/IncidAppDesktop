/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.ReadException;
import java.util.Collection;
import javabeans.TownHallBean;

/**
 *
 * @author Jon Gonzalez
 */
public interface iTownHall {
    public Collection<TownHallBean> findAllTownHalls() throws ReadException;
}
