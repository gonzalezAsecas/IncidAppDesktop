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
import java.util.ResourceBundle;
import java.util.logging.Level;
import javabeans.FTPFileTV;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
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
    private MenuItem mIIncidentList;
    @FXML
    private MenuItem mIFTP;
    @FXML
    private MenuItem mISetting;
    @FXML
    private MenuItem mILogOut;
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
    @FXML
    private TextField txtFServer;
    @FXML
    private TextField txtFDirectory;
    
    public GUI005Controller(){}
    
    /**
     * the file is going to be loaded in the FTP server
     */
    private File file;
    
    public void setFile(File file){
        this.file = file;
    }
    
    /**
     * the path of the directory
     */
    private String dirPath;
    
    public void setDirPath(String dirPath){
        this.dirPath = dirPath;
    }
    
    /**
     * the ftp interface
     */
    private final iFTP FTP = FTPFactory.getiFTP();
    
    private TreeItem<FTPFileTV> tiselected;
    
    ResourceBundle properties = ResourceBundle
            .getBundle("properties/ftpClientProperties");
    
    /**
     * GUI005 FXML Controller class, this is the window for connect and do all
     * with the ftpsserver
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
        mIIncidentList.setOnAction(this::handleIncidentsFTP);
        mISetting.setOnAction(this::handleInfoFTP);
        mILogOut.setOnAction(this::handleLogOutFTP);
        btnSearch.setOnAction(this::handleSearch);
        btnLoad.setOnAction(this::handleLoad);
        btnDownload.setOnAction(this::handleDownload);
        btnMakeDirectory.setOnAction(this::handleMakeDir);
        btnDelete.setOnAction(this::handleDelete);
        txtFNameDirectory.textProperty().addListener(this::handleTextChanged);
        //Load the files of the FTP server in the treeItem and make the listener
        //for the click event
        txtFServer.setText(properties.getString("ftpServer") + ":" + properties.getString("ftpServerPort"));
        txtFDirectory.setText(properties.getString("ftpRootDirectory"));
        try {
            treeFTP.getSelectionModel().selectedItemProperty()
                    .addListener(this::handleClickFile);
            loadRoot(FTP.login());
            //set the first directory path
            dirPath = "/";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "The login failed.", ex.getCause());
        }
        //Show the ftpclient window
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
        btnLoad.setText("_Upload file");
        btnDownload.setMnemonicParsing(true);
        btnDownload.setText("Do_wnload file");
        btnMakeDirectory.setMnemonicParsing(true);
        btnMakeDirectory.setText("_Make directory");
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        btnMakeDirectory.setDisable(true);
        LOGGER.info("Ending OnShowingHandler");
    }
    
    public void handleTextChanged(ObservableValue observable, 
            String oldvalue, String newvalue){
        if(txtFNameDirectory.getText().trim().isEmpty()){
            btnMakeDirectory.setDisable(true);
        }else{
            btnMakeDirectory.setDisable(false);
        }
    }
    
    /**
     * The method unleashed when the "Search file" button is clicked or the key 
     * combination is pressed. Open a filechooser and set a helpful filters
     * @param event 
     */
    public void handleSearch(ActionEvent event){
        LOGGER.info("Beginning handleSearch");
        //Create the filechooser
        FileChooser filechooser = new FileChooser();
        //Set title
        filechooser.setTitle("Searching file for load in the FTP server");
        filechooser.setInitialDirectory(
            new File(System.getProperty("user.home")));
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
            for(FTPFileTV filee: files){
                item = new TreeItem<FTPFileTV>(filee);
                if(filee.isDirectory()){
                    item.getChildren().addAll(
                            loadFiles(filee.getPath() + filee.getName()));
                }
                root.getChildren().add(item);
                
            }
            treeFTP = new TreeView<FTPFileTV>(root);
            file=null;
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
            //get the files of that directory
            leafs = FTP.showFiles(path);
            // create a arrayList for all the files
            arrayFiles = new ArrayList<TreeItem<FTPFileTV>>();
            for(FTPFileTV leaf: leafs){
                //get an item from the array
                treeleaf = new TreeItem<FTPFileTV>(leaf);
                //check if this object is a directory or a file
                if(leaf.isDirectory()){
                    //get it childrens
                    treeleaf.getChildren()
                            .addAll(loadFiles(leaf.getPath() + "/" + leaf.getName()));
                }
                //set the treeitem in the arraylist
                arrayFiles.add(treeleaf);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred loading of files", ex);
        }
        return arrayFiles;
    }
    
    /**
     * Load a file in the ftp server
     * @param event 
     */
    public void handleLoad(ActionEvent event){
        TreeItem<FTPFileTV> ti;
        FTPFileTV filetv = new FTPFileTV();
        try{
            //load the file in the FTPServer
            FTP.loadFile(dirPath, file);
            //set the FTPFileTV for know what is de directory for the file
            filetv.setName(file.getName());
            filetv.setPath(dirPath);
            filetv.setDirectory(false);
            ti = new TreeItem<FTPFileTV>(filetv);
            tiselected.getParent().getChildren().add(ti);
            treeFTP.refresh();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "File uploaded.", ButtonType.OK);
            alert.showAndWait();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "Error loading file", ex);
            super.getAlert("An error had ocurred loading the file.");
        }
    }
    
    //Test It
    /**
     * download the file selected in the treeview
     * @param event 
     */
    public void handleDownload(ActionEvent event){
        try {
            //Create the filechooser
            DirectoryChooser directorychooser = new DirectoryChooser();
            //Set title
            directorychooser.setTitle("Searching file for load in the FTP server");
            directorychooser.setInitialDirectory(
                    new File(System.getProperty("user.home")));
            //get the value of the treeitem selected and download it
            //save the path of the file
            file = directorychooser.showDialog(stage);
            if(file!=null){
                FTP.downloadFile(tiselected.getValue(), file);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "File downloaded.", ButtonType.OK);
                alert.showAndWait();
            }
            file=null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error downloading file", ex);
            super.getAlert("An error had ocurred downloading the file.");
        }
        
    }
    
    /**
     * Make a directory in the ftp server
     * @param event 
     */
    public void handleMakeDir(ActionEvent event){
        FTPFileTV filetv = new FTPFileTV();
        TreeItem<FTPFileTV> ti;
        try {
            //get the name for the directory and the directory path and make 
            //this directory in the ftpserver 
            FTP.makeDirectory(dirPath, txtFNameDirectory.getText());
            filetv.setName(txtFNameDirectory.getText());
            filetv.setPath(dirPath);
            filetv.setDirectory(true);
            ti = new TreeItem<FTPFileTV>(filetv);
            tiselected.getParent().getChildren().add(ti);
            treeFTP.refresh();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error making the directory", ex);
            super.getAlert("An error had ocurred making the directory.");
        }
    }
    
    //Test It
    /**
     * delete the selected item
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        try {
            //send the selected item value for delete it
            if(super.getAlert("You are going to delete this file, it´s ok?").equals(ButtonType.OK)){
                FTP.delete(dirPath + "/" + tiselected.getValue().getName(), tiselected.getValue().isDirectory());
                tiselected.getParent().getChildren().remove(tiselected);
                treeFTP.refresh();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error deleting the file", ex);
            super.getAlert("An error had ocurred deleting the file.");
        }
    }
    
    /**
     * logout from the ftp server and go to the incidents window
     * @param event 
     */
    public void handleIncidentsFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred in the menu item incident event", ex);
        }
        super.handleIncidents(event);
    }
    
    /**
     * logout from the ftp server and go to the modifyData window
     * @param event 
     */
    public void handleInfoFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred in the menu item info event", ex);
        }
        super.handleInfo(event);
    }
    
    /**
     * logout from the ftp server and the application
     * @param event 
     */
    public void handleLogOutFTP(ActionEvent event){
        try {
            FTP.logout();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred in the menu item logout event", ex);
        }
        super.handleLogOut(event);
    }
    
    /**
     * check that the item selected in the treeview is a file or a directory
     * @param observable
     * @param oldvalue
     * @param newvalue 
     */
    public void handleClickFile(ObservableValue observable, 
            Object oldvalue, Object newvalue){
        //check that the new file clicked is diferent that the old file clicked
        if(!newvalue.equals(oldvalue)){
            //get the item selected
            tiselected = (TreeItem<FTPFileTV>) newvalue;
            //check it type
            if(tiselected.getValue().isDirectory()){
                //get her path with her name if it's a directory
                if(tiselected.getValue().getPath().equals("/")){
                    dirPath = tiselected.getValue().getPath() + tiselected.getValue().getName();
                }else{
                    dirPath = tiselected.getValue().getPath() + "/" + tiselected.getValue().getName();
                }
                if(tiselected.getChildren().isEmpty()){
                    btnDelete.setDisable(false);
                }else{
                    btnDelete.setDisable(true);
                }
                btnDownload.setDisable(true);
            }else{
                dirPath = tiselected.getValue().getPath();
                btnDelete.setDisable(false);
                btnDownload.setDisable(false);
            }
        }else if(newvalue==null){
            btnDelete.setDisable(true);
            btnDownload.setDisable(true);
            btnLoad.setDisable(true);
        }
        txtFDirectory.setText(dirPath);
    }
}
