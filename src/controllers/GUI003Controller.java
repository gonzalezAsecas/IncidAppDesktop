/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.DeleteException;
import exceptions.ReadException;
import java.util.logging.Level;
import javabeans.IncidentBean;
import javabeans.UserBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private RadioButton rbtnAll;
    @FXML
    private RadioButton rbtnMyZone;
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
    private Button btnAddIncident;
    @FXML
    private Button btnModifyIncident;
    @FXML
    private Button btnDelete;
    @FXML
    private Menu mIncidents;
    @FXML
    private Menu mFiles;
    @FXML
    private Menu mUserInfo;
    @FXML
    private Menu mLogOut;
    
    private ObservableList<IncidentBean> incidents = null;
    private ObservableList<IncidentBean> incidentsMyZone = null;
    
    private UserBean userr;
    
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
        rbtnAll.setOnAction((event) -> handleFilter(event));
        rbtnMyZone.setOnAction((event) -> handleFilter(event));
        btnAddIncident.setOnAction((event) -> handleIncidentsEmpty(event));
        btnModifyIncident.setOnAction((event) -> handleIncidentsFull(event));
        btnDelete.setOnAction((event) -> handleDelete(event));
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
        UserBean userr = new UserBean();
        try {
            incidents = FXCollections
                .observableArrayList(incidentManager.findAllIncidents());
            incidentsMyZone = FXCollections
                .observableArrayList(incidentManager.findIncidentsByUser(user));
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                "Exception finding all or my zone incidents",ex.getMessage());
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
        btnAddIncident.setMnemonicParsing(true);
        btnAddIncident.setText("_Add Incident");
        btnModifyIncident.setMnemonicParsing(true);
        btnModifyIncident.setText("_Modify Incidents");
        btnModifyIncident.setDisable(true);
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        btnDelete.setDisable(true);
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * filter the list of incidents by the text of the textfield
     * @param event 
     */
    public void handleFilter(ActionEvent event){
        LOGGER.info("Beginning handleFilter");
        if(rbtnMyZone.isSelected()) {
            tbIncidents.setItems(incidentsMyZone);
        } else {
            tbIncidents.setItems(incidents);
        }
        tbIncidents.refresh();
        LOGGER.info("Ending handleFilter");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        LOGGER.info("Beginning handleDelete");
        if (getAlert("Are you sure?").equals(ButtonType.OK)) {
            try {
                incidentManager.removeIncident(incident);
                tbIncidents.getItems().remove(incident);
                tbIncidents.refresh();
            } catch (DeleteException ex) {
                LOGGER.log(Level.SEVERE,
                    "Exception deleting incident",ex.getMessage());
            }
        }
        LOGGER.info("Ending handleDelete");
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
            incident = tbIncidents.getSelectionModel().getSelectedItem();
        } else {
            btnModifyIncident.setDisable(true);
            btnDelete.setDisable(true);
            incident = null;
        }
        LOGGER.info("Ending handleIncidentsTableSelectionChanged");
    }
}
