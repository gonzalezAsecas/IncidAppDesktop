/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.ReadException;
import factories.LogicFactory;
import interfaces.iIncident;
import java.io.IOException;
import javabeans.Estate;
import javabeans.IncidentBean;
import javabeans.LocationBean;
import javabeans.TypeBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private TableColumn<?, ?> tCTitle;
    @FXML
    private TableColumn<?, ?> tCDescription;
    @FXML
    private TableColumn<?, ?> tCLocation;
    @FXML
    private TableColumn<?, ?> tCType;
    @FXML
    private TableColumn<?, ?> tCEstate;
    @FXML
    private TableColumn<?, ?> tCSignatures;
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
    
    /**
     * the implementation of the iIncident interface
     */
    //private iIncident incidentImpl = LogicFactory.getiIncident();
    
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root) {
        LOGGER.info("Initializing GUI003 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
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
        btnAddIncident.setOnAction((event) -> handleAddIncident(event));
        btnModifyIncident.setOnAction((event) -> handleModifyIncident(event));
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
        ObservableList<IncidentBean> incidents = 
            FXCollections.observableArrayList();
        TypeBean t = new TypeBean();
        t.setName("Apocalipsis");
        LocationBean l = new LocationBean();
        l.setStreet("Urreta");
        IncidentBean incident;
        for(int i = 1; i < 3; i++){
            incident = new IncidentBean();
            incident.setIdIncident(i);
            incident.setTitle("Incidencia" + i);
            incident.setDescription("Descripcion" + i);
            incident.setLocation(l);
            incident.setType(t);
            incident.setEstate(Estate.OUTSTANDING);
            incidents.add(incident);
        }
        
        //Rellenar table
        tCTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tCDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tCLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tCType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tCEstate.setCellValueFactory(new PropertyValueFactory<>("estate"));
        //tCSignatures.setCellValueFactory(new PropertyValueFactory<>(""));
        //incidents = incidentImpl.findAllIncidents();
        tbIncidents.setItems(incidents);
    }
    
    /**
     * Set atributes to the controls that it need when the window is shown
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler");
        mIncidents.setDisable(true);
        //Set the mnemonics
        btnFilter.setMnemonicParsing(true);
        btnFilter.setText("_Filter");
        btnAddIncident.setMnemonicParsing(true);
        btnAddIncident.setText("_Add Incident");
        btnModifyIncident.setMnemonicParsing(true);
        btnModifyIncident.setText("_Modify Incidents");
        btnModifyIncident.setDisable(false);
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        btnDelete.setDisable(false);
        btnCollectSignatures.setMnemonicParsing(true);
        btnCollectSignatures.setText("_Collect Signatures");
        btnCollectSignatures.setDisable(false);
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleFiles(ActionEvent event){
        LOGGER.info("Beginning handleFiles");
        super.handleFiles(event);
        LOGGER.info("Ending handleFiles");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleInfo(ActionEvent event){
        LOGGER.info("Beginning handleInfo");
        LOGGER.info("Ending handleInfo");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLogOut(ActionEvent event){
        LOGGER.info("Beginning handleLogOut");
        LOGGER.info("Ending handleLogOut");
    }
    
    /**
     * filter the list of incidents by the type and the estate and see if any 
     * of they contains the text of the textfield
     * @param event 
     */
    public void handleFilter(ActionEvent event){
        LOGGER.info("Beginning handleFilter");
        //Create the incident for the filtrate
        /*IncidentBean incident = new IncidentBean();
        //get the filters
        incident.setTitle(txtFTitle.getText());
        try {
            incidents = incidentImpl.findIncidentsbyFilter(incident);
            //reload the table view of the incidents
            loadIncidents();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "There is no incidents with that parameters", ex);
            super.getAlert("There is no incidents with that parameters.");
        }*/
        LOGGER.info("Ending handleFilter");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleAddIncident(ActionEvent event){
        LOGGER.info("Beginning handleAddIncident");
        super.handleIncidentsEmpty(event);
        LOGGER.info("Ending handleAddIncident");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleModifyIncident(ActionEvent event){
        LOGGER.info("Beginning handleModifyIncident");
        super.handleIncidentsFull(event);
        LOGGER.info("Ending handleModifyIncident");
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
        }else{
            btnModifyIncident.setDisable(true);
            btnDelete.setDisable(true);
            btnCollectSignatures.setDisable(true);
        }
        LOGGER.info("Ending handleIncidentsTableSelectionChanged");
    }
}
