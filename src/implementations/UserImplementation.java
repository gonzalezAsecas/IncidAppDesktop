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
import javabeans.UserBean;

/**
 *
 * @author Jon Gonzalez
 */
public class UserImplementation implements iUser{
    
    //REST incident web client
    /*private UserRestFulClient webClient;

    public UserImplementation() {
        webClient = new UserRestFulClient();
    }*/

    @Override
    public UserBean createUser(UserBean user) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editUser(UserBean user) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(UserBean user) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserBean findUserbyId(UserBean user) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserBean> findAllUsers() throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
