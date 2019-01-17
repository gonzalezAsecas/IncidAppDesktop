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
import interfaces.iUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.UserBean;
import javax.ws.rs.core.GenericType;
import restfuls.UserRestFul;

/**
 *
 * @author Jon Gonzalez
 */
public class UserImplementation implements iUser{
    
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    /**
     * The restful for the user 
     */
    UserRestFul userRest = new UserRestFul();
    
    /**
     * Create the user if there isn't any with this login
     * @param user
     * @throws CreateException 
     */
    @Override
    public void createUser(UserBean user) throws CreateException {
        try{
            userRest.create(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new CreateException();
        }
    }

    @Override
    public void editUser(UserBean user) throws UpdateException {
        try{
            userRest.edit(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new UpdateException();
        }
    }

    @Override
    public void removeUser(UserBean user) throws DeleteException {
        try{
            userRest.remove(user.getId().toString());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new DeleteException();
        }
    }

    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        try{
            //user = userRest.find(UserBean.class, user.getId());
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new ReadException();
        }
    }

    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        List<UserBean> us;
        try{
            us = userRest.findAll(new GenericType<List<UserBean>>() {});
            return us;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new ReadException();
        }
    }

    @Override
    public List<UserBean> findAllTHUsers() throws ReadException {
        List<UserBean> us;
        try{
            us = userRest.findAllTHU(new GenericType<List<UserBean>>() {});
            return us;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new ReadException();
        }
    }
    
    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException {
        try{
            user = userRest.findUserbyLogin(UserBean.class, user.getLogin());
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new ReadException();
        }
    }

    @Override
    public void findUserToChangePassword(UserBean user) throws ReadException {
        try{
            userRest.findUserToChangePassword(UserBean.class, user.getLogin());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "", ex);
            throw new ReadException();
        }
    }
    
}
