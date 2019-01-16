/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import interfaces.iFTP;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * The ftp client for connect and load and download files, make directories and 
 * delete it on the server. The parameters for the connect are in the 
 * ftpClientProperties file 
 * @author Jon Gonzalez
 */
public class FTPCliente implements iFTP{
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    private final FTPClient ftpclient = new FTPClient();
    private ResourceBundle properties;
    
    /**
     * login in the FTP server decrypting the user and the password
     * @return the list of the files and directories in the root element
     * @throws Exception 
     */
    @Override
    public FTPFile[] login() throws Exception{
        try {
            LOGGER.info("Beginning login");
            //Set properties file for the ftpclient
            properties = ResourceBundle
                    .getBundle("properties.ftpClientProperties.properties");
            //get the server name
            ftpclient.connect(properties.getString("ftpserver"));
            //get the user and the password decrypting it before
            ftpclient.login(properties.getString("ftpuser"),
                    properties.getString("ftppassword"));//Cambiar esto para que desencripte la contrseña y el usuario primero
            //get the 
            ftpclient.changeWorkingDirectory(properties
                    .getString("ftpdirectory"));
            LOGGER.info("Ending login");
            //return showFiles();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "An error have ocurred in the login of the ftp client", ex);
            throw new Exception(ex);
        }
        return null;//quitar
    }
    
    /**
     * logout and disconnect of the ftp server
     * @throws Exception
     */
    @Override
    public void logout() throws Exception{
        try {
            //logout the ftp server
            ftpclient.logout();
            //disconnect of the server
            ftpclient.disconnect();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error disconnecting the ftpclient", ex);
            throw new Exception(ex);
        }
    }
    
    /**
     * list the files inside the directory sent
     * @param dir the directory that is going to be listed
     * @return the list of files inside of the directory
     * @throws Exception 
     */
    @Override
    public FTPFile[] showFiles(FTPFile dir) throws Exception{
        try{
            return ftpclient.listFiles(dir.getLink());
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "Error getting list of files", ex);
            throw new Exception(ex);
        }
    }
    
    /**
     * load the file in the directory sent
     * @param ftpdirectory the directory in
     * @param file
     * @throws Exception 
     */
    @Override
    public void loadFile(FTPFile ftpdirectory, File file) throws Exception{
        try {
            BufferedInputStream buffIn;
            ftpclient.changeWorkingDirectory(ftpdirectory.getLink());
            buffIn = new BufferedInputStream(new FileInputStream(file.getPath()));
            ftpclient.enterLocalPassiveMode();
            ftpclient.storeFile(file.getName(), buffIn);
        } catch (IOException ex) {
            Logger.getLogger(FTPCliente.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }
    
    /**
     * 
     * @param dir
     * @param dirName
     * @throws Exception 
     */
    @Override
    public void makeDirectory(FTPFile dir, String dirName) throws Exception {
        try {
            ftpclient.changeWorkingDirectory(dir.getLink());
            ftpclient.makeDirectory(dirName);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "FTPClient: An error have ocurred making the directory", ex);
            throw new Exception(ex);
        }
    }
    
    /**
     * 
     * @param file 
     */
    @Override
    public void downloadFile(FTPFile file) throws Exception {
        BufferedOutputStream out;
        try{
           out = new BufferedOutputStream(new FileOutputStream(file.getLink()));
           ftpclient.retrieveFile(file.getName(), out);
           out.close();
        }catch(IOException ex){
           LOGGER.log(Level.SEVERE, "", ex);
           throw new Exception(ex);
        }
    }
    
    /**
     * 
     * @param file
     * @throws Exception 
     */
    @Override
    public void delete(FTPFile file) throws Exception {
        try{
            ftpclient.deleteFile(file.getLink());
        }catch(IOException ex){
           LOGGER.log(Level.SEVERE, "", ex);
           throw new Exception(ex);
        }
    }
}
