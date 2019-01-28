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
import java.util.List;
import javabeans.UserBean;

/**
 * The interface for the user bean
 * @author Jon Gonzalez
 * @version 1.0
 */
public interface iUser {
    
    /**
     * The method for create and user
     * @param user the user is going to be created
     * @throws CreateException if there are any error creating the user
     */
    public void createUser(UserBean user) throws CreateException;
    
    /**
     * The method for modify an user
     * @param user the user is going to be modified
     * @throws UpdateException if there is any problem modifying the user
     */
    public void editUser(UserBean user) throws UpdateException;
    
    /**
     * The method for delete an user
     * @param user the user is going to be deleted
     * @throws DeleteException if there is any problem deleting the user
     */
    public void removeUser(UserBean user) throws DeleteException;
    
    /**
     * The method for search an user by it id
     * @param user the user is going to be found with her id
     * @return the user if is found
     * @throws ReadException if there is any problem finding by id the user
     */
    public UserBean findUserbyId(UserBean user) throws ReadException;
    
    /**
     * The method for find all users
     * @return all the users in the database
     * @throws ReadException if there is any problem finding the users
     */
    public List<UserBean> findAllUsers() throws ReadException;
    
    /**
     * The method for search an user by it login
     * @param user the user is going to be finded with her login
     * @return the user if is finded
     * @throws ReadException if there is any problem finding by login the user
     */
    public UserBean findUserbyLogin(UserBean user) throws ReadException;
    
    /**
     * The method for change the password of a user
     * @param user the user is going to change the password
     * @throws ReadException if there is any problem finding the user to change the password
     */
    public void findUserToChangePassword(UserBean user) throws ReadException;
}
