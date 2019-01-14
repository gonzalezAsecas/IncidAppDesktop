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
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Jon Gonzalez
 */
public class FTPCliente implements iFTP{
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    private final FTPClient ftpclient = new FTPClient();
    private ResourceBundle properties;

    @Override
    public void login() {
        LOGGER.info("Beginning login");
        properties = ResourceBundle
                .getBundle("properties.ftpClientProperties");
        try {
            ftpclient.connect(properties.getString("ftpserver"));
            ftpclient.login(properties.getString("ftpuser"),
                    properties.getString("ftppassword"));
            ftpclient.changeWorkingDirectory(properties
                    .getString("ftpdirectory"));
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.info("Ending login");
    }

    @Override
    public void logout() {
        try {
            ftpclient.logout();
            ftpclient.disconnect();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error disconnecting the ftpclient", ex);
        }
    }
    
    @Override
    public FTPFile[] showFiles(FTPFile dir) throws IOException{
        return ftpclient.listFiles(dir.getLink());
    }
    
    @Override
    public void loadFile(FTPFile ftpdirecotry, File file) throws IOException{
        BufferedInputStream buffIn;
        ftpclient.changeWorkingDirectory("");//TO DO
        buffIn = new BufferedInputStream(new FileInputStream(file.getPath()));
        ftpclient.enterLocalPassiveMode();
        ftpclient.storeFile(file.getName(), buffIn);
    }

    @Override
    public void makeDirectory() {
        try {
            ftpclient.changeWorkingDirectory("");//TO DO
            ftpclient.makeDirectory("");//TO DO
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void downloadFile(FTPFile file) {
        
    }

    @Override
    public void delete() {
        
    }
}
