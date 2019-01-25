/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.FTPFactory;
import interfaces.iFTP;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.FTPFileTV;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Jon Gonzalez
 */
public class GUI005Controller extends THUserGenericController{

    @FXML
    private Menu mIncidents;
    @FXML
    private Menu mFiles;
    @FXML
    private Menu mUserInfo;
    @FXML
    private Menu mLogOut;
    @FXML
    private TextField txtFSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnLoad;
    @FXML
    private TreeView<FTPFileTV> treeFTP;
    @FXML
    private Button btnMakeDirectory;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDownload;
    @FXML
    private TextField txtFNameDirectory;
    
    private File file;
    
    private String dirPath;
    
    private final iFTP FTP = FTPFactory.getiFTP();
    
    /**
     * GUI005 FXML Controller class, this is the window for modify the data 
     * of the town hall user
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI005 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Files manager");
        stage.setResizable(true);
        
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        mIncidents.setOnAction((event) -> this.handleIncidentsFTP(event));
        mUserInfo.setOnAction((event) -> this.handleInfoFTP(event));
        mLogOut.setOnAction((event) -> this.handleLogOutFTP(event));
        btnSearch.setOnAction((event) -> this.handleSearch(event));
        btnLoad.setOnAction((event) -> this.handleLoad(event));
        btnDownload.setOnAction((event) -> this.handleDownload(event));
        btnMakeDirectory.setOnAction((event) -> this.handleMakeDir(event));
        btnDelete.setOnAction((event) -> this.handleDelete(event));
        try {
            treeFTP.getSelectionModel().selectedItemProperty().addListener(this::handleClickFile);
            loadRoot(FTP.login());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "The login failed.", ex.getCause());
        }
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI005 stage");
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler");
        mFiles.setDisable(true);
        txtFSearch.setEditable(false);
        btnSearch.setMnemonicParsing(true);
        btnSearch.setText("_Search file");
        btnLoad.setMnemonicParsing(true);
        btnLoad.setText("_Load file");
        btnDownload.setMnemonicParsing(true);
        btnDownload.setText("Do_wnload file");
        btnMakeDirectory.setMnemonicParsing(true);
        btnMakeDirectory.setText("_Make directory");
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleSearch(ActionEvent event){
        LOGGER.info("Beginning handleSearch");
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Searching file");
        filechooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF", "*.pdf"),
                new ExtensionFilter("Word", "*.docx"),
                new ExtensionFilter("Excel", "*.xls")
        );
        file = filechooser.showOpenDialog(stage);
        if(file!=null){
            txtFSearch.setText(file.getAbsolutePath());
            btnLoad.setDisable(false);
        }
        LOGGER.info("Ending handleSearch");
    }
    
    /**
     * 
     * @param dir 
     */
    public void loadRoot(FTPFileTV dir){
        try {
            TreeItem<FTPFileTV> root = new TreeItem<FTPFileTV>(dir);
            FTPFileTV[] files = FTP.showFiles(dir.getPath());
            TreeItem<FTPFileTV> item;
            treeFTP.setRoot(root);
            root.setExpanded(true);
            for(FTPFileTV file: files){
                item = new TreeItem<FTPFileTV>(file);
                if(file.isDirectory()){
                    item.getChildren().addAll(
                            loadFiles(file.getPath() + "/" + file.getName()));
                }
                root.getChildren().add(item);
                
            }
            treeFTP = new TreeView<FTPFileTV>(root);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
    }
    
    /**
     * 
     * @param path
     * @return 
     */
    public ArrayList<TreeItem<FTPFileTV>> loadFiles(String path){
        FTPFileTV[] leafs;
        ArrayList<TreeItem<FTPFileTV>> arrayFiles = null;
        TreeItem<FTPFileTV> treeleaf;
        try {
            leafs = FTP.showFiles(path);
            arrayFiles = new ArrayList<TreeItem<FTPFileTV>>();
            for(FTPFileTV leaf: leafs){
                treeleaf = new TreeItem<FTPFileTV>(leaf);
                if(leaf.isDirectory()){
                    treeleaf.getChildren()
                            .addAll(loadFiles(leaf.getPath() + "/" + leaf.getName()));
                }
                arrayFiles.add(treeleaf);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
        return arrayFiles;
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLoad(ActionEvent event){
        try{
            FTP.loadFile(dirPath, file);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "Error loading file", ex);
            super.getAlert("An error had ocurred loading the file.");
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDownload(ActionEvent event){
        try {
            FTP.downloadFile(dirPath); 
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error downloading file", ex);
            super.getAlert("An error had ocurred downloading the file.");
        }
        
    }
    
    /**
     * 
     * @param event 
     */
    public void handleMakeDir(ActionEvent event){
        try {
            FTP.makeDirectory(dirPath, txtFNameDirectory.getText());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error making the directory", ex);
            super.getAlert("An error had ocurred making the directory.");
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        try {
            FTP.delete(dirPath);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error deleting the file", ex);
            super.getAlert("An error had ocurred deleting the file.");
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void handleIncidentsFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
        super.handleIncidents(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleInfoFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
        super.handleInfo(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLogOutFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
        super.handleLogOut(event);
    }
    
    /**
     * 
     * @param observable
     * @param oldvalue
     * @param newvalue 
     */
    public void handleClickFile(ObservableValue observable, 
            Object oldvalue, Object newvalue){
        if(!newvalue.equals(oldvalue)){
            TreeItem<FTPFileTV> treeItem = (TreeItem<FTPFileTV>) newvalue;
            if(treeItem.getValue().isDirectory()){
                dirPath = treeItem.getValue().getPath() + "/" + treeItem.getValue().getName();
                if(treeItem.getChildren()==null){
                    btnDelete.setDisable(false);
                }else{
                    btnDelete.setDisable(true);
                }
                btnDownload.setDisable(true);
            }else{
                dirPath = treeItem.getValue().getPath();
                btnDelete.setDisable(false);
                btnDownload.setDisable(false);
            }
        }
    }
}