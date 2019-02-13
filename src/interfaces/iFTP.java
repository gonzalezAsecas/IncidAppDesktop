/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import javabeans.FTPFileTV;

/**
 * The interface that contains the method for do operations with the ftp server
 * @author Jon Gonzalez
 * @version 1.0
 */
public interface iFTP {
    
    /**
     * The method for login in the ftp server
     * @return the root directory
     * @throws Exception if there is any problem with the connnection
     */
    public FTPFileTV login() throws Exception;
    
    /**
     * The method for logout from the ftp server
     * @throws Exception if there is any problems disconnecting from the ftp server
     */
    public void logout() throws Exception;
    
    /**
     * the method for get the files from the working directory
     * @param dir the working directory for list the children files
     * @return the list of children files
     * @throws Exception if there is any problems loading the list of files
     */
    public FTPFileTV[] showFiles(String dir) throws Exception;
    
    /**
     * load a file in the directory in the ftp server
     * @param ftpdirectory a directory in the ftp server
     * @param file the file is going to be loaded
     * @throws Exception if there is any problem loading the file
     */
    public void loadFile(String ftpdirectory,File file) throws Exception;
    
    /**
     * make directory in the ftp server
     * @param dir the directory in that is going to be made the directory
     * @param dirName the name for the directory
     * @throws Exception if there is any problem making the directory
     */
    public void makeDirectory(String dir, String dirName) throws Exception;
    
    /**
     * download the file from the ftp server
     * @param file the file is going to downloaded
     * @throws Exception if there is any problem downloading the file
     */
    public void downloadFile(FTPFileTV file, File dir) throws Exception;
    
    /**
     * delete the file selected from the ftp server
     * @param file the file is going to be deteled
     * @throws Exception if there is any problem deleting the file
     */
    public void delete(String file, Boolean isDirectory) throws Exception;
}
