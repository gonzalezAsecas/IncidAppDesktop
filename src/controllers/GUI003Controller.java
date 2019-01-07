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
import interfaces.iType;
import java.util.List;
import java.util.logging.Level;
import javabeans.Estate;
import javabeans.IncidentBean;
import javabeans.TypeBean;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

/**
 * GUI001 FXML Controller class, in this window you can see the list of 
 * incidents, add one incident and see or modify the incident that is selected
 * @author Jon Gonzalez
 * @version 1.0
 */
public class GUI003Controller extends THUserGenericController{

    @FXML
    private TextField txtFTitle;
    @FXML
    private ChoiceBox<TypeBean> chcType;
    @FXML
    private ChoiceBox<Estate> chcEstate;
    @FXML
    private TableView<IncidentBean> tableView;
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
     * the implementation of the iType interface
     */
    private iType typeImpl = LogicFactory.getiType();
    
    /**
     * the implementation f the iIncident interface
     */
    private iIncident incidentImpl = LogicFactory.getiIncident();
    
    /**
     * the list of incidents
     */
    private List<IncidentBean> incidents;
    
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
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI003 stage");
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
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        btnCollectSignatures.setMnemonicParsing(true);
        btnCollectSignatures.setText("_Collect Signatures");
        //load the all data
        loadData();
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * load all the incidents, the types and the estates
     * TO DO
     */
    private void loadData() {
        TypeBean typeOne= new TypeBean();
        typeOne.setIncidents(null);
        typeOne.setName("All");
        List<TypeBean> types = null;
        try {
            types = typeImpl.findAllTypes();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "There is no types.", ex);
        }
        types.add(typeOne);
        for(int i=0; types.size() >= i; i++){
            chcType.getItems().add(types.get(i));
        }
        //TODO estates
        //Revisar esto
        try{
            incidents = incidentImpl.findAllIncidents();
            loadIncidents();
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "There is no incidents", ex);
            super.getAlert("There is no incidents.");
        }
        
    }
    
    /**
     * load the incidents of the list in the table view
     * TO DO
     */
    private void loadIncidents() {
            tableView.setItems((ObservableList<IncidentBean>) incidents);//se puede parsear?
            tCTitle = new TableColumn("Title");
            tCTitle.setCellValueFactory(new PropertyValueFactory("title"));
            tCDescription = new TableColumn("Description");
            tCDescription.setCellValueFactory(new PropertyValueFactory("description"));
            tCLocation = new TableColumn("Location");
            tCLocation.setCellValueFactory(new PropertyValueFactory("location"));
            tCType = new TableColumn("Type");
            tCType.setCellValueFactory(new PropertyValueFactory("type"));
            tCEstate = new TableColumn("Estate");
            tCEstate.setCellValueFactory(new PropertyValueFactory("estate"));
            tCSignatures = new TableColumn("Signature");
            tCSignatures.setCellValueFactory(new PropertyValueFactory("signatures"));
    }
    
    /**
     * filter the list of incidents by the type and the estate and see if any 
     * of they contains the text of the textfield
     * @param event 
     */
    public void handleFilter(ActionEvent event){
        LOGGER.info("Beginning handleFilter");
        //Create the incident for the filtrate
        IncidentBean incident = new IncidentBean();
        //get the filters
        incident.setTitle(txtFTitle.getText());
        incident.setType(chcType.getValue());
        incident.setEstate(chcEstate.getValue());
        try {
            incidents = incidentImpl.findIncidentsbyFilter(incident);
            //reload the table view of the incidents
            loadIncidents();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "There is no incidents with that parameters", ex);
            super.getAlert("There is no incidents with that parameters.");
        }
        LOGGER.info("Ending handleFilter");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleAddIncident(ActionEvent event){
        LOGGER.info("Beginning handleAddIncident");
        
        LOGGER.info("Ending handleAddIncident");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleModifyIncident(ActionEvent event){
        LOGGER.info("Beginning handleModifyIncident");
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
}
