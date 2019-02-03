/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import implementations.FTPCliente;
import interfaces.iFTP;

/**
 *
 * @author Jon Gonzalez
 */
public class FTPFactory {
    public static iFTP getiFTP(){
        return new FTPCliente();
    }
}
