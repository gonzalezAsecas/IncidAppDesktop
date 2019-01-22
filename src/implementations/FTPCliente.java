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
import javabeans.FTPFileTV;
import org.apache.commons.net.ftp.FTP;
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
    public FTPFileTV login() throws Exception{
        try {
            LOGGER.info("Beginning login");
            //Set properties file for the ftpclient
            properties = ResourceBundle
                    .getBundle("properties/ftpClientProperties");
            //get the server name
            ftpclient.connect(properties.getString("ftpServer"), 6000);
            //ftpclient.connect(properties.getString("ftpServer"), Integer.getInteger(properties.getString("ftpServerPort")));
            ftpclient.enterRemotePassiveMode();
            ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
            //get the user and the password decrypting it before
            ftpclient.login(properties.getString("ftpUser"),
                    properties.getString("ftpPassword"));//Cambiar esto para que desencripte la contrseña y el usuario primero
            ftpclient.changeWorkingDirectory(properties
                    .getString("ftpRootDirectory"));
            LOGGER.info("Ending login");
            //return showFiles(ftpclient.printWorkingDirectory());
            return fTPFiletoFTPFileTV(ftpclient.printWorkingDirectory());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "An error have ocurred in the login of the ftp client", ex.getCause());
            throw new Exception(ex);
        }
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
            LOGGER.log(Level.SEVERE, 
                    "Error disconnecting the ftpclient", ex.getCause());
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
    public FTPFileTV[] showFiles(String dir) throws Exception{
        FTPFile[] files;
        FTPFileTV[] filestv;
        try{
            files = ftpclient.listFiles(dir);
            filestv = new FTPFileTV[files.length];
            for(int i=0; i<files.length; i++){
                filestv[i] = fTPFiletoFTPFileTV(
                        ftpclient.printWorkingDirectory(), files[i]);
            }
            return filestv;
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, 
                    "Error getting list of files", ex.getCause());
            throw new Exception(ex);
        }
    }
    
    /**
     * load the file in the directory sent
     * @param dir the directory in
     * @param file
     * @throws Exception 
     */
    @Override
    public void loadFile(FTPFileTV dir, File file) throws Exception{
        try {
            BufferedInputStream buffIn;
            ftpclient.changeWorkingDirectory(dir.getPath() + "/" + dir.getName());
            buffIn = new BufferedInputStream(new FileInputStream(file.getPath()));
            ftpclient.storeFile(file.getName(), buffIn);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "FTPClient: An error have ocurred loading the file." , ex.getCause());
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
    public void makeDirectory(FTPFileTV dir, String dirName) throws Exception {
        try {
            ftpclient.changeWorkingDirectory(dir.getPath() + "/" + dir.getName());
            ftpclient.makeDirectory(dirName);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE,
                    "FTPClient: An error have ocurred making the directory", ex.getCause());
            throw new Exception(ex);
        }
    }
    
    /**
     * 
     * @param file 
     */
    @Override
    public void downloadFile(FTPFileTV file) throws Exception {
        BufferedOutputStream out;
        try{
           out = new BufferedOutputStream(new FileOutputStream("filesDownloaded"));
           ftpclient.retrieveFile(file.getPath() + "/" + file.getName(), out);
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
    public void delete(FTPFileTV file) throws Exception {
        try{
            ftpclient.deleteFile(file.getPath() + "/" + file.getName());
        }catch(IOException ex){
           LOGGER.log(Level.SEVERE, "", ex);
           throw new Exception(ex);
        }
    }
    
    public FTPFileTV fTPFiletoFTPFileTV(String dir, FTPFile file){
        FTPFileTV filetv = new FTPFileTV();
        filetv.setName(file.getName());
        if(file.getType()==1){
            filetv.setDirectory(true);
        }else if(file.getType()==0){
            filetv.setDirectory(false);
        }
        filetv.setPath(dir);
        return filetv;
    }

    private FTPFileTV fTPFiletoFTPFileTV(String dir) {
        FTPFileTV filetv = new FTPFileTV();
        filetv.setName("");
        filetv.setDirectory(true);
        filetv.setPath(dir);
        return filetv;
    }
}
