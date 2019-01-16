/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.File;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Jon Gonzalez
 */
public interface iFTP {
    public FTPFile[] login() throws Exception;
    public void logout() throws Exception;
    public FTPFile[] showFiles(FTPFile dir) throws Exception;
    public void loadFile(FTPFile ftpdirectory,File file) throws Exception;
    public void makeDirectory(FTPFile dir, String dirName) throws Exception;
    public void downloadFile(FTPFile file) throws Exception;
    public void delete(FTPFile file) throws Exception;
}
