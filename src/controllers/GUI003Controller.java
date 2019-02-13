/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.DeleteException;
import exceptions.ReadException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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
    private Button btnReport;
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
        mIncidents.setOnAction((event) -> handleIncidents(event));
        mFiles.setOnAction((event) -> handleFiles(event));
        mUserInfo.setOnAction((event) -> handleInfo(event));
        mLogOut.setOnAction((event) -> handleLogOut(event));
        rbtnAll.setOnAction((event) -> handleFilter(event));
        rbtnMyZone.setOnAction((event) -> handleFilter(event));
        btnAddIncident.setOnAction((event) -> handleIncidents2(event,1));
        btnModifyIncident.setOnAction((event) -> handleIncidents2(event,2));
        btnDelete.setOnAction((event) -> handleDelete(event));
        btnReport.setOnAction((event) -> handleReport(event));
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
        mIncidents.setDisable(true);
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
     * filter the list of incidents by all or my zone
     * @param event 
     */
    public void handleFilter(ActionEvent event){
        LOGGER.info("Beginning handleFilter");
        if(rbtnMyZone.isSelected()) {
            try {
                incidentsMyZone = FXCollections
                    .observableArrayList(incidentManager.findIncidentsByUser(user));
            } catch (ReadException ex) {
                LOGGER.log(Level.SEVERE,
                    "Exception finding my zone incidents",ex.getMessage());
            }
            tbIncidents.setItems(incidentsMyZone);
        } else {
            try {
                incidents = FXCollections
                    .observableArrayList(incidentManager.findAllIncidents());
            } catch (ReadException ex) {
                LOGGER.log(Level.SEVERE,
                    "Exception finding all incidents",ex.getMessage());
            }
            tbIncidents.setItems(incidents);
        }
        tbIncidents.refresh();
        LOGGER.info("Ending handleFilter");
    }
    
    /**
     * delete an incident
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        LOGGER.info("Beginning handleDelete");
        if (getAlertConfirmation("Are you sure?").equals(ButtonType.OK)) {
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
     * do an incident report
     * @param event 
     */
    private void handleReport(ActionEvent event) {
        LOGGER.info("Beginning handleReport");
        try{ 
            JasperReport report = JasperCompileManager.compileReport("src/reports/incidentreport.jrxml");
            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<IncidentBean>)this.tbIncidents.getItems());
            Map<String,Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        }catch(JRException ex){
            LOGGER.log(Level.SEVERE, "An error ocurred in handleReport()",
                        ex.getMessage());
        }
        LOGGER.info("Ending handleReport");
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
