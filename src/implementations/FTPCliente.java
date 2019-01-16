/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import interfaces.iFTP;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Jon Gonzalez
 */
public class FTPCliente implements iFTP{
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    //private FTPClient ftpclient = new FTPClient();
    private ResourceBundle properties;

    @Override
    public void login() {
        LOGGER.info("Beginning login");
        properties = ResourceBundle.getBundle("properties.ftpClientProperties");
        //ftpclient.connect(properties.getString("ftpserver"));
        //ftpclient.login(properties.getString("ftpuser"), properties.getString("ftppassword"));
        //ftpclient.changeWorkingDirectory(properties.getString("ftpdirectory"));
        //ftpclient.changeWorkingDirectory(pathname); //posicionarse en el directorio a usar
        LOGGER.info("Ending login");
    }
    
    public void logout() {
        //ftpclient.logout();
        //ftpclient.disconnect();
    }
    
    /*public FTPFile[] showFiles() {
        //return ftpclient.listFiles(""); cargar los archivos que estan en raiz
        return null;
    }*/
    
    public void loadFile(File file) throws IOException{
        BufferedInputStream buffIn;
        //ftpclient.changeWorkingDirectory(pathname); cambiar al directorio
        buffIn = new BufferedInputStream(new FileInputStream(file.getPath()));
        //ftpclient.enterLocalPassiveMode();
        //ftpclient.storeFile(file.getName(), buffIn);
    }
    
    public void makeDirectory() {
        
    }

    /*public void downloadFile(FTPFile file) {
        
    }*/
    
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
