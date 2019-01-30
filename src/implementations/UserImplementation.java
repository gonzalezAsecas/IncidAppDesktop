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
import static implementations.FTPCliente.LOGGER;
import interfaces.iUser;
import java.util.List;
import java.util.logging.Level;
import javabeans.UserBean;
import javax.ws.rs.core.GenericType;
import restfuls.UserRestFulClient;

/**
 *
 * @author Jon Gonzalez
 */
public class UserImplementation implements iUser{
    
    //REST incident web client
    private UserRestFulClient webClient;

    public UserImplementation() {
        webClient = new UserRestFulClient();
    }

    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        LOGGER.info("Finding all users");
        List<UserBean> users = null;
        try{
            users = webClient.findAll(new GenericType<List<UserBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding all users",ex.getMessage());
            throw new ReadException("Error finding all users:\n"+ex.getMessage());
        }
        return users;
    } 
    
    @Override
    public UserBean findUserbyLogin(UserBean user) throws ReadException {
        LOGGER.info("Finding user by id");
        List<UserBean> users = null;
        UserBean us = null;
        try{
            users = webClient.findAll(new GenericType<List<UserBean>>() {});
            for(UserBean u : users){
                if(u.getIdPerson() == user.getIdPerson()){
                    us = u;
                    break;
                }
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                "Exception finding user by id",ex.getMessage());
            throw new ReadException("Error finding user by id:\n"+ex.getMessage());
        }
        return us;
    }
}
