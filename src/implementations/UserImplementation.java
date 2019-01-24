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
import static implementations.TownHallImplementation.LOGGER;
import interfaces.iUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import javabeans.UserBean;
import javax.ws.rs.core.GenericType;
import restfuls.UserRestFul;

/**
 *
 * @author Jon Gonzalez
 */
public class UserImplementation implements iUser{
    
    private UserRestFul webClient;
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");

    @Override
    public void createUser(UserBean user) throws CreateException {
         try{
           LOGGER.info("UserImplementation: Creating an user from REST service.");
           webClient.create(user);
       }catch(Exception ex){
           LOGGER.log(Level.SEVERE, "UserImplementation: Exception creating a townhall",
                    ex.getMessage());
           throw new CreateException("Error creating an user");
       }
    }

    @Override
    public void editUser(UserBean user) throws UpdateException {
        try{
            LOGGER.info("UserImplementation: Editing a townhall from REST service.");
            webClient.edit(user);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception editing a townhall",
                    ex.getMessage());
            throw new UpdateException("Error editing an user");
        }
    }

    @Override
    public void removeUser(UserBean user) throws DeleteException {
        try{
            LOGGER.info("UserImplementation: Deleting an user from REST service.");
            webClient.remove(user.getId());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception removing an user",
                    ex.getMessage());
            throw new DeleteException("Error deleting an user");
        }
    }

    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        UserBean usr = null;
        try{
            LOGGER.info("UserImplementation: Finding an user by id from REST service.");
            usr = webClient.find(UserBean.class, user.getId().toString());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "TownhallImplementation: Exception finding a townhall by id",
                    ex.getMessage());
        }
        return usr;
    }

    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        List<UserBean> users = null;
        try{
            LOGGER.info("UserImplementation: Finding all townhalls from REST service.");
            users = webClient.find(new GenericType<List<UserBean>>() {});
        }catch(Exception ex){
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "UserImplementation: Exception finding all users",
                    ex.getMessage());
            throw new ReadException("Error finding all users");
        }
        return users;
    }

    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findUserToChangePassword(UserBean user) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
