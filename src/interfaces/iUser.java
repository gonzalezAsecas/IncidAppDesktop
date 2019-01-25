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
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author Jon Gonzalez
 */
public interface iUser {
    public void createUser(UserBean user) throws CreateException;

    public void editUser(UserBean user) throws UpdateException;

    public void removeUser(UserBean user) throws DeleteException;

    public UserBean findUserbyId(UserBean user) throws ReadException;

    public List<UserBean> findAllUsers() throws ReadException;
    
    public UserBean findUserbyLogin(UserBean user) throws ReadException;

    public void findUserToChangePassword(UserBean user) throws ReadException;
    
    public void findUserToConfirmPassword(UserBean user, byte[] pass) throws ClientErrorException;
}
