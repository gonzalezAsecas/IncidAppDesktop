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
import java.util.List;
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
import javafx.scene.control.SelectionMode;
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
        try {
            treeFTP.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            loadRoot(FTP.login());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
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
        //treeFTP.getSelectionModel().selectedItemProperty()
        //        .addListener(this::handleLoadFiles);
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
            for(FTPFileTV file: files){
                item = new TreeItem<FTPFileTV>(file);
                if(file.isDirectory()){
                    item.getChildren().addAll(loadFiles(file.getPath() + "/" + file.getName()));
                }
                root.getChildren().add(item);
                
            }
            treeFTP = new TreeView<FTPFileTV>(root);
        } catch (Exception ex) {
            Logger.getLogger(GUI005Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        TreeItem<FTPFileTV> leaff;
        try {
            leafs = FTP.showFiles(path);
            arrayFiles = new ArrayList<TreeItem<FTPFileTV>>();
            for(FTPFileTV leaf: leafs){
                leaff = new TreeItem<FTPFileTV>(leaf);
                if(leaf.isDirectory()){
                    leaff.getChildren().addAll(loadFiles(file.getPath() + "/" + file.getName()));
                }
                arrayFiles.add(leaff);
            }
        } catch (Exception ex) {
            Logger.getLogger(GUI005Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayFiles;
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLoad(ActionEvent event){
        /*
        try{
            FTP.loadFile(file);
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "Error loading file", ex);
            super.getAlert("An error had ocurred loading the file.");
        }
        */
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDownload(ActionEvent event){
        //FTP.downloadFile(file); //el fichero que se hya seleccionado
        
    }
    
    /**
     * 
     * @param event 
     */
    public void handleMakeDir(ActionEvent event){
        //FTP.makeDirectory();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        //FTP.delete();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleIncidentsFTP(ActionEvent event){
        //FTP.logout();
        super.handleIncidents(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleInfoFTP(ActionEvent event){
        //FTP.logout();
        super.handleInfo(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLogOutFTP(ActionEvent event){
        //FTP.logout();
        super.handleLogOut(event);
    }
}