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
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;

/**
 * The ftp client for connect and load and download files, make directories and 
 * delete it on the server. The parameters for the connect are in the 
 * ftpClientProperties file 
 * @author Jon Gonzalez
 * @version 1.0
 */
//public class FTPCliente implements iFTP{
//    /**
//     * The logger for the desktop app
//     */
//    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
//    private final FTPClient ftpclient = new FTPClient();
//    private ResourceBundle properties;
//    
//    /**
//     * The method for login in the ftp server
//     * @return the root directory
//     * @throws Exception if there is any problem with the connnection
//     */
//    @Override
//    public FTPFileTV login() throws Exception{
//        try {
//            LOGGER.info("FTPCliente: Beginning login");
//            //Set properties file for the ftpclient
//            properties = ResourceBundle
//                    .getBundle("properties/ftpClientProperties");
//            //get the server name
//            ftpclient.connect(properties.getString("ftpServer"), 
//                    Integer.parseInt(properties.getString("ftpServerPort")));
//            ftpclient.enterRemotePassiveMode();
//            ftpclient.setFileType(FTP.BINARY_FILE_TYPE);
//            //get the user and the password decrypting it before
//            ftpclient.login(properties.getString("ftpUser"),
//                    properties.getString("ftpPassword"));
//            ftpclient.changeWorkingDirectory(properties
//                    .getString("ftpRootDirectory"));
//            LOGGER.info("FTPCliente: Ending login");
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE,
//                    "An error have ocurred in the login of the ftp client", ex.getCause());
//            throw new Exception(ex);
//        }
//        return fTPFiletoFTPFileTV(ftpclient.printWorkingDirectory());
//    }
//    
//    /**
//     * The method for logout from the ftp server
//     * @throws Exception if there is any problems disconnecting from the ftp server
//     */
//    @Override
//    public void logout() throws Exception{
//        try {
//            //logout the ftp server
//            ftpclient.logout();
//            //disconnect of the server
//            ftpclient.disconnect();
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, 
//                    "Error disconnecting the ftpclient", ex.getCause());
//            throw new Exception(ex);
//        }
//    }
//    
//    /**
//     * the method for get the files from the working directory
//     * @param dir the working directory for list the children files
//     * @return the list of children files
//     * @throws Exception if there is any problems loading the list of files
//     */
//    @Override
//    public FTPFileTV[] showFiles(String dir) throws Exception{
//        FTPFile[] files;
//        FTPFileTV[] filestv;
//        try{
//            //get the files in the directory
//            files = ftpclient.listFiles(dir);
//            //set the lenght of the files
//            filestv = new FTPFileTV[files.length];
//            for(int i=0; i<files.length; i++){
//                //set the files in the array
//                filestv[i] = fTPFiletoFTPFileTV(
//                        dir, files[i]);
//            }
//            return filestv;
//        }catch(IOException ex){
//            LOGGER.log(Level.SEVERE, 
//                    "Error getting list of files", ex.getCause());
//            throw new Exception(ex);
//        }
//    }
//    
//    /**
//     * load a file in the directory in the ftp server 
//     * @param dir a directory in the ftp server
//     * @param file the file is going to be loaded
//     * @throws Exception if there is any problem loading the file
//     */
//    @Override
//    public void loadFile(String dir, File file) throws Exception{
//        try {
//            BufferedInputStream buffIn;
//            //change the working directory
//            ftpclient.changeWorkingDirectory(dir);
//            //set the stream for load the files
//            buffIn = new BufferedInputStream(new FileInputStream(file.getPath()));
//            //store the file in the ftp server
//            ftpclient.storeFile(file.getName(), buffIn);
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE,
//                    "FTPClient: An error have ocurred loading the file." , ex.getCause());
//            throw new Exception(ex);
//        }
//    }
//    
//    /**
//     * make directory in the ftp server
//     * @param dir the directory in that is going to be made the directory
//     * @param dirName the name for the directory
//     * @throws Exception if there is any problem making the directory
//     */
//    @Override
//    public void makeDirectory(String dir, String dirName) throws Exception {
//        try {
//            //change the worki,ng directory
//            ftpclient.changeWorkingDirectory(dir);
//            //create the directory in the ftp server
//            ftpclient.makeDirectory(dirName);
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE,
//                    "FTPClient: An error have ocurred making the directory", ex.getCause());
//            throw new Exception(ex);
//        }
//    }
//    
//    //Test It
//    /**
//     * download the file from the ftp server
//     * @param file the file is going to downloaded
//     * @throws Exception if there is any problem downloading the file
//     */
//    @Override
//    public void downloadFile(FTPFileTV file) throws Exception {
//        BufferedOutputStream out;
//        try{
//            //set the directory for the file downloaded
//            out = new BufferedOutputStream(new FileOutputStream(new File(file.getName())));
//            //download the file
//            ftpclient.retrieveFile(file.getPath() + "/" + file.getName(), out);
//            //close the stream
//            out.close();
//        }catch(IOException ex){
//           LOGGER.log(Level.SEVERE, "FTPClient: An error have ocurred downloading the file", ex);
//           throw new Exception(ex);
//        }
//    }
//    
//    //Test It
//    /**
//     * delete the file selected from the ftp server
//     * @param file the file is going to be deteled
//     * @throws Exception if there is any problem deleting the file
//     */
//    @Override
//    public void delete(String file) throws Exception {
//        try{
//            //delete the file
//            ftpclient.deleteFile(file);
//        }catch(IOException ex){
//           LOGGER.log(Level.SEVERE, "", ex);
//           ex.printStackTrace();
//           throw new Exception(ex);
//        }
//    }
//    
//    /**
//     * Change the type from the FTPFile to FTPFileTV
//     * @param dir the path of the parent directory
//     * @param file the FTPFile file
//     * @return the file in FTPFileTV type
//     */
//    public FTPFileTV fTPFiletoFTPFileTV(String dir, FTPFile file){
//        //create a FTPFileTV
//        FTPFileTV filetv = new FTPFileTV();
//        //set the name of the file
//        filetv.setName(file.getName());
//        //set if it is directory or not
//        if(file.getType()==1){
//            filetv.setDirectory(true);
//        }else if(file.getType()==0){
//            filetv.setDirectory(false);
//        }
//        //set the path of the parent directory
//        filetv.setPath(dir);
//        return filetv;
//    }
//    
//    /**
//     * Change the type from the FTPFile to FTPFileTV for teh root file
//     * @param dir the directory that is the root file
//     * @return the root file
//     */
//    private FTPFileTV fTPFiletoFTPFileTV(String dir) {
//        //create a FTPFileTV
//        FTPFileTV filetv = new FTPFileTV();
//        //set the name of the file
//        filetv.setName("");
//        //set that it is directory
//        filetv.setDirectory(true);
//        //set the path of the parent directory
//        filetv.setPath(dir);
//        return filetv;
//    }
//}
