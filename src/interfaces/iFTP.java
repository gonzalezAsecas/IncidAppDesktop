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
    public void login();
    public void logout();
    public FTPFile[] showFiles();
    public void loadFile(FTPFile ftpdirectory,File file) throws IOException;
    public void makeDirectory();
    public void downloadFile(FTPFile file);
    public void delete();
}
