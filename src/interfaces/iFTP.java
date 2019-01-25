/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import javabeans.FTPFileTV;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Jon Gonzalez
 */
public interface iFTP {
    public FTPFileTV login() throws Exception;
    public void logout() throws Exception;
    public FTPFileTV[] showFiles(String dir) throws Exception;
    public void loadFile(String ftpdirectory,File file) throws Exception;
    public void makeDirectory(String dir, String dirName) throws Exception;
    public void downloadFile(String file) throws Exception;
    public void delete(String file) throws Exception;
}
