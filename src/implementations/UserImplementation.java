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
 * The implementation of the iuser interface
 * @author Jon Gonzalez
 * @version 1.0
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
     * The method for create and user
     * @param user the user is going to be created
     * @throws CreateException if there are any error creating the user
     */
    @Override
    public void createUser(UserBean user) throws CreateException {
        LOGGER.info("UserImplementation: Beginning the creation of the user.");
        try{
            //create the user
            userRest.create(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception creating the user.", ex);
            throw new CreateException();
        }
        LOGGER.info("UserImplementation: Ending the creation of the user.");
    }
    
    /**
     * The method for modify an user
     * @param user the user is going to be modified
     * @throws UpdateException if there is any problem modifying the user
     */
    @Override
    public void editUser(UserBean user) throws UpdateException {
        LOGGER.info("UserImplementation: Beginning the modification of the user.");
        try{
            //modify the user
            userRest.edit(user, "true");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception modifing the user.", ex);
            throw new UpdateException();
        }
        LOGGER.info("UserImplementation: ");
    }
    
    /**
     * The method for delete an user
     * @param user the user is going to be deleted
     * @throws DeleteException if there is any problem deleting the user
     */
    @Override
    public void removeUser(UserBean user) throws DeleteException {
        LOGGER.info("UserImplementation: Beginning the erasing of the user.");
        try{
            //remove the user
            userRest.remove(user.getId().toString());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception deleting the user.", ex);
            throw new DeleteException();
        }
        LOGGER.info("UserImplementation: ");
    }
    
    /**
     * The method for search an user by it id
     * @param user the user is going to be found with her id
     * @return the user if is found
     * @throws ReadException if there is any problem finding by id the user
     */
    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: Beginning the creation of the user.");
        try{
            //find the user by id sending the clas and the id
            user = userRest.find(UserBean.class, user.getId().toString());
            LOGGER.info("UserImplementation: ");
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user by id.", ex);
            throw new ReadException();
        }
        
    }
    
    /**
     * The method for find all users
     * @return all the users in the database
     * @throws ReadException if there is any problem finding the users
     */
    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        LOGGER.info("UserImplementation: ");
        List<UserBean> us;
        try{
            //finding the users
            us = userRest.findAll(new GenericType<List<UserBean>>() {});
            LOGGER.info("UserImplementation: ");
            return us;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding all the users.", ex);
            throw new ReadException();
        }
    }
    
    /**
     * The method for search an user by it login
     * @param user the user is going to be finded with her login
     * @return the user if is finded
     * @throws ReadException if there is any problem finding by login the user
     */
    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: ");
        try{
            //finding the user by loging sending the login and the password 
            //encrypted in hexadecimal
            user = userRest.findUserbyLogin(UserBean.class, user.getLogin(), 
                    DatatypeConverter.printHexBinary(user.getPassword()));
            LOGGER.info("UserImplementation: ");
            return user;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user by login.", ex);
            throw new ReadException();
        }
    }
    
    /**
     * The method for change the password of a user
     * @param user the user is going to change the password
     * @throws ReadException if there is any problem finding the user to change the password
     */
    @Override
    public void findUserToChangePassword(UserBean user) throws ReadException {
        LOGGER.info("UserImplementation: Beginning the search of the user for change the password");
        try{
            //searching the user for change the password
            userRest.findUserToChangePassword(UserBean.class, user.getLogin());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding the user to change the password.", ex);
            throw new ReadException();
        }
        LOGGER.info("UserImplementation: Ending the search of the user for change the password");
    }
}
