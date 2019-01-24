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
import javax.xml.bind.DatatypeConverter;
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
        LOGGER.info("UserImplementation: Beginning the creation of the user.");
        try{
            userRest.create(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception creating the user.", ex);
            throw new CreateException("UserImplememtatio: Error creating an user");
        }
        LOGGER.info("UserImplementation: Ending the creation of the user.");
    }
    
    /**
     * 
     * @param user
     * @throws UpdateException 
     */
    @Override
    public void editUser(UserBean user) throws UpdateException {
        LOGGER.info("UserImplementation: Beginning the modification of the user.");
        try{
            userRest.edit(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception modifing the user.", ex);
            throw new UpdateException("UserImplementation: Error updating an user");
        }
        LOGGER.info("UserImplementation: ");
    }
    
    /**
     * 
     * @param user
     * @throws DeleteException 
     */
    @Override
    public void removeUser(UserBean user) throws DeleteException {
        LOGGER.info("UserImplementation: Beginning the erasing of the user.");
        try{
            userRest.remove(user.getId());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception deleting the user.", ex);
            throw new DeleteException("UserImplementation: Error deleting an user");
        }
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws ReadException 
     */
    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: Beginning the creation of the user.");
        try{
            user = userRest.find(UserBean.class, user.getId().toString());
            LOGGER.info("UserImplementation: ");
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user by id.", ex);
            throw new ReadException("UserImplementation: Error creating an user");
        }
        
    }
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        LOGGER.info("UserImplementation: ");
        List<UserBean> us;
        try{
            us = userRest.findAll(new GenericType<List<UserBean>>() {});
            LOGGER.info("UserImplementation: ");
            return us;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding all the users.", ex);
            throw new ReadException("UserImplementation: Error finding all users");
        }
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws ReadException 
     */
    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: ");
        try{
            user = userRest.findUserbyLogin(UserBean.class, user.getLogin(), 
                    DatatypeConverter.printHexBinary(user.getPassword()));
            LOGGER.info("UserImplementation: ");
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user by login.", ex);
            throw new ReadException("UserImplementation: Error finding an user");
        }
    }
    
    /**
     * 
     * @param user
     * @throws ReadException 
     */
    @Override
    public void findUserToChangePassword(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: Beginning the search of the user for change the password");
        try{
            userRest.findUserToChangePassword(UserBean.class, user.getLogin());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user to change the password.", ex);
            throw new ReadException("UserImplementation: Error finding an user");
        }
        LOGGER.info("UserImplementation: Ending the search of the user for change the password");
    }
}
