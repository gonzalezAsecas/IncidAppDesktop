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
        mIncidents.setOnAction(this::handleIncidentsFTP);
        mUserInfo.setOnAction(this::handleInfoFTP);
        mLogOut.setOnAction(this::handleLogOutFTP);
        btnSearch.setOnAction(this::handleSearch);
        btnLoad.setOnAction(this::handleLoad);
        btnDownload.setOnAction(this::handleDownload);
        btnMakeDirectory.setOnAction(this::handleMakeDir);
        btnDelete.setOnAction(this::handleDelete);
        //Load the files of the FTP server in the treeItem and make the listener
        //for the click event
        try {
            treeFTP.getSelectionModel().selectedItemProperty()
                    .addListener(this::handleClickFile);
            loadRoot(FTP.login());
            dirPath = "/";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "The login failed.", ex.getCause());
        }
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI005 stage");
    }
    
    /**
     * Set on showing the mnemonics for the buttons
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
     * The method unleashed when the "Search file" button is clicked or the key 
     * combination is pressed. Open a filechooser and show the pdfs, excels 
     * and words
     * @param event 
     */
    public void handleSearch(ActionEvent event){
        LOGGER.info("Beginning handleSearch");
        //Create the filechooser
        FileChooser filechooser = new FileChooser();
        //Set title
        filechooser.setTitle("Searching file for load in the FTP server");
        //set the filters for make more comfortable
        filechooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF", "*.pdf"),
                new ExtensionFilter("Word", "*.docx"),
                new ExtensionFilter("Excel", "*.xls")
        );
        //save the path of the file
        file = filechooser.showOpenDialog(stage);
        //Verify that the file isn't null
        if(file!=null){
            //set the path in the textfield
            txtFSearch.setText(file.getAbsolutePath());
            //set the load button enable
            btnLoad.setDisable(false);
        }
        LOGGER.info("Ending handleSearch");
    }
    
    /**
     * Load the root item of the treeview and it childs
     * @param dir the root directory from the FTP server
     */
    public void loadRoot(FTPFileTV dir){
        LOGGER.info("Beginning loadRoot");
        TreeItem<FTPFileTV> root;
        FTPFileTV[] files;
        TreeItem<FTPFileTV> item;
        try {
            //create the root item
            root = new TreeItem<FTPFileTV>(dir);
            //get the list of files and directories in the root directory
            files = FTP.showFiles(dir.getPath());
            treeFTP.setRoot(root);
            root.setExpanded(true);
            for(FTPFileTV file: files){
                item = new TreeItem<FTPFileTV>(file);
                if(file.isDirectory()){
                    item.getChildren().addAll(
                            loadFiles(file.getPath() + file.getName()));
                }
                root.getChildren().add(item);
                
            }
            treeFTP = new TreeView<FTPFileTV>(root);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred loading of root", ex);
        }
        LOGGER.info("Ending loadRoot");
    }
    
    /**
     * Load the files to her father and, if they are directorys, load her 
     * childrens
     * @param path the path that represents the father
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
            LOGGER.log(Level.SEVERE, "An error have ocurred loading of files", ex);
        }
        return arrayFiles;
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLoad(ActionEvent event){
        TreeItem<FTPFileTV> ti;
        FTPFileTV filetv = new FTPFileTV();
        FTPFileTV root = new FTPFileTV();
        try{
            filetv.setName(file.getName());
            filetv.setPath(dirPath);
            filetv.setDirectory(false);
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
            
            FTP.downloadFile(dirPath + "/" + treeFTP.getSelectionModel()
                    .getSelectedItem().getValue().getName()); 
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
        TreeItem<FTPFileTV> ti = (TreeItem<FTPFileTV>) treeFTP.getSelectionModel().getSelectedItem();
        try {
            FTP.delete(dirPath + ti.getValue().getName());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error deleting the file", ex);
            ex.printStackTrace();
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