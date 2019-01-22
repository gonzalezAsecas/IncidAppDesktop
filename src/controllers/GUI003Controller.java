/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.ReadException;
import java.util.logging.Level;
import javabeans.IncidentBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * GUI003 FXML Controller class, in this window you can see the list of 
 * incidents, add one incident and see or modify the incident that is selected
 * @author Gorka Redondo
 */
public class GUI003Controller extends THUserGenericController {

    @FXML
    private TextField txtFTitle;
    @FXML
    private TableView<IncidentBean> tbIncidents;
    @FXML
    private TableColumn tCTitle;
    @FXML
    private TableColumn tCDescription;
    @FXML
    private TableColumn tCLocation;
    @FXML
    private TableColumn tCType;
    @FXML
    private TableColumn tCEstate;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnAddIncident;
    @FXML
    private Button btnModifyIncident;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCollectSignatures;
    @FXML
    private Menu mIncidents;
    @FXML
    private Menu mFiles;
    @FXML
    private Menu mUserInfo;
    @FXML
    private Menu mLogOut;
    
    private ObservableList<IncidentBean> incidents = null;
    private ObservableList<IncidentBean> incidentsCopy = null;
    
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root) {
        LOGGER.info("Initializing GUI003 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        stage = new Stage();
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Incidents");
        stage.setResizable(true);
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        mFiles.setOnAction((event) -> handleFiles(event));
        mUserInfo.setOnAction((event) -> handleInfo(event));
        mLogOut.setOnAction((event) -> handleLogOut(event));
        btnFilter.setOnAction((event) -> handleFilter(event));
        btnAddIncident.setOnAction((event) -> handleIncidentsEmpty(event));
        btnModifyIncident.setOnAction((event) -> handleIncidentsFull(event));
        btnDelete.setOnAction((event) -> handleDelete(event));
        btnCollectSignatures.setOnAction((event) -> handleCollectSignatures(event));
        tbIncidents.getSelectionModel().selectedItemProperty()
            .addListener(this::handleIncidentsTableSelectionChanged);
        //load the all data
        loadData();
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI003 stage");
    }
    
    /**
     * load all the incidents
     */
    private void loadData() {
        try {
            incidents = FXCollections
                .observableArrayList(incidentManager.findAllIncidents());
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                "Exception finding all incidents",ex.getMessage());
        }
        tCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tCDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tCLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tCType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tCEstate.setCellValueFactory(new PropertyValueFactory<>("estate"));
        tbIncidents.setItems(incidents);
    }
    
    /**
     * Set atributes to the controls that it need when the window is shown
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler");
        btnFilter.setMnemonicParsing(true);
        btnFilter.setText("_Filter");
        btnAddIncident.setMnemonicParsing(true);
        btnAddIncident.setText("_Add Incident");
        btnModifyIncident.setMnemonicParsing(true);
        btnModifyIncident.setText("_Modify Incidents");
        btnModifyIncident.setDisable(true);
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        btnDelete.setDisable(true);
        btnCollectSignatures.setMnemonicParsing(true);
        btnCollectSignatures.setText("_Collect Signatures");
        btnCollectSignatures.setDisable(true);
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * filter the list of incidents by the text of the textfield
     * @param event 
     */
    public void handleFilter(ActionEvent event){
        LOGGER.info("Beginning handleFilter");
        incidentsCopy = FXCollections.observableArrayList(incidents);
        if(!(txtFTitle.getText().trim().isEmpty())) {
            for(IncidentBean inc : incidentsCopy) {
                if(!(inc.getTitle().toLowerCase()
                    .contains((CharSequence)txtFTitle.getText().toLowerCase()))) {
                    incidents.remove(inc);
                }
            }
        }
        tbIncidents.getSelectionModel().clearSelection();
        tbIncidents.refresh();
        incidents = FXCollections.observableArrayList(incidentsCopy);
        LOGGER.info("Ending handleFilter");
        //TODO: El refresh solo funciona la primera vez
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        LOGGER.info("Beginning handleDelete");
        LOGGER.info("Ending handleDelete");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleCollectSignatures(ActionEvent event){
        LOGGER.info("Beginning handleCollectSignatures");
        LOGGER.info("Ending handleCollectSignatures");
       //TODO with MongoDB 
    }
    
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    public void handleIncidentsTableSelectionChanged(ObservableValue observable,
             Object oldValue,
             Object newValue){
        LOGGER.info("Beginning handleIncidentsTableSelectionChanged");
        if(newValue != null){
            btnModifyIncident.setDisable(false);
            btnDelete.setDisable(false);
            btnCollectSignatures.setDisable(false);
            incident = tbIncidents.getSelectionModel().getSelectedItem();
        }
        LOGGER.info("Ending handleIncidentsTableSelectionChanged");
    }
}
