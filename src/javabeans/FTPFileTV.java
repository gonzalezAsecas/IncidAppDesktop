/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The file for the TreeView with the needed information 
 * @author Jon Gonzalez
 * @version 1.0
 */
public class FTPFileTV {
    
    /**
     * the propertie for the path in the ftp server
     */
    private final SimpleStringProperty path;
    
    /**
     * the propertie for the name of the file
     */
    private final SimpleStringProperty name;
    
    /**
     * The propertie for know it is a directory or a file
     */
    private final SimpleBooleanProperty directory;
    
    public FTPFileTV() {
        path = new SimpleStringProperty();
        name = new SimpleStringProperty();
        directory = new SimpleBooleanProperty();
    }
    
    /**
     * 
     * @return 
     */
    public String getPath() {
        return path.get();
    }
    
    public void setPath(String path) {
        this.path.set(path);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isDirectory() {
        return directory.get();
    }

    public void setDirectory(boolean directory) {
        this.directory.set(directory);
    }

    @Override
    public String toString() {
        return name.get();
    }
    
}
