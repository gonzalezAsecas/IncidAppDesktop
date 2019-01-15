/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.FTPFactory;
import interfaces.iFTP;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
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
//import org.apache.commons.net.ftp.FTPFile;

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
    private TreeView<String> treeFTP;
    @FXML
    private Button btnMakeDirectory;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDownload;
    
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
        loadFiles();
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
     */
    private void loadFiles() {
        
        TreeItem root;
        FTPFile[] files;
        FTP.login();
        files = FTP.showFiles();
        root = new TreeItem<String>("FTP client");
        root.setExpanded(true);
        for(int i=0;i<=files.length; i++){
            if(files[i].getType()==1){
                files[i].setName(files[i].getName() + "[DIR]");
            }
            root.getChildren().add(files[i].getName());
        }
        treeFTP = new TreeView<String>(root);*/
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLoad(ActionEvent event){
        
        try{
            FTP.loadFile(file);
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "Error loading file", ex);
            super.getAlert("An error had ocurred loading the file.");
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDownload(ActionEvent event){
        FTP.downloadFile(file); //el fichero que se hya seleccionado
        
    }
    
    /**
     * 
     * @param event 
     */
    public void handleMakeDir(ActionEvent event){
        FTP.makeDirectory();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        FTP.delete();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleIncidentsFTP(ActionEvent event){
        FTP.logout();
        super.handleIncidents(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleInfoFTP(ActionEvent event){
        FTP.logout();
        super.handleInfo(event);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLogOutFTP(ActionEvent event){
        FTP.logout();
        super.handleLogOut(event);
    }
}