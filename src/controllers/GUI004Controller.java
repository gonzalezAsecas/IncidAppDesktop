/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.CreateException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javabeans.Estate;
import javabeans.IncidentBean;
import javabeans.LocationBean;
import javabeans.TownHallBean;
import javabeans.TypeBean;
import javabeans.UserBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gorka Redondo
 */
public class GUI004Controller extends THUserGenericController {
    @FXML
    private TextField txtFTitle;
    @FXML
    private TextArea txtADescription;
    @FXML
    private TextArea txtAComment;
    @FXML
    private Button btnPhoto;
    @FXML
    private ImageView iVPhoto;
    @FXML
    private TextField txtFStreet;
    @FXML
    private ComboBox<TownHallBean> chcLocality;
    @FXML
    private ComboBox<TypeBean> chcType;
    @FXML
    private ComboBox<Estate> chcEstate;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblSignature;
    @FXML
    private Button btnSignature;
     
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI004 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        stage = new Stage();
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Modify incident");
        stage.setResizable(true);
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        txtFTitle.textProperty().addListener(this::textChanged);
        txtADescription.textProperty().addListener(this::textChanged);
        txtAComment.textProperty().addListener(this::textChanged);
        txtFStreet.textProperty().addListener(this::textChanged);
        btnPhoto.setOnAction((event) -> handlePhoto(event));
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
        btnSignature.setOnAction((event) -> handleSignature(event));
        //load the all data
        loadData();
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI004 stage");
    }
    
    public void loadData() {
        try {
            ObservableList<TypeBean> types = FXCollections
                .observableArrayList(typeManager.findAllTypes());
            chcType.setItems(types);
            chcType.setValue(types.get(0));
            chcEstate.setItems(FXCollections.observableArrayList(Estate.values()));
            chcEstate.setValue(FXCollections.observableArrayList(Estate.values())
                .get(0));
            ObservableList<TownHallBean> ths = FXCollections
                .observableArrayList(townHallManager.findAllTownHalls());
            chcLocality.setItems(ths);
            chcLocality.setValue(ths.get(0));
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                "Exception finding all types, estates or localitys",
                ex.getMessage());
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event) {
        LOGGER.info("Beginning OnShowingHandler");
        btnSignature.setMnemonicParsing(true);
        btnSignature.setText("_Sign it");
        btnSignature.setDisable(true);
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnAccept.setDisable(true);
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        txtAComment.setDisable(true);
        lblSignature.setText("0");
        if(incident != null) {
            btnSignature.setDisable(false);
            btnAccept.setDisable(false);
            txtAComment.setDisable(false);
            txtFTitle.setText(incident.getTitle());
            txtADescription.setText(incident.getDescription());
            txtAComment.setText(incident.getComment());
            //Set de la photo
            txtFStreet.setText(incident.getLocation().getStreet());
            chcLocality.setValue(incident.getLocation().getTownHall());
            chcType.setValue(incident.getType());
            chcEstate.setValue(incident.getEstate());
            if(incident.getUsers() != null) {
                lblSignature.setText(String.valueOf(incident.getUsers().size()));
            }
        }
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * Text changed of the required fields
     * @param observable
     * @param oldValue
     * @param newValue 
     */
     private void textChanged(ObservableValue observable, String oldValue,
            String newValue) {
         if(!txtFTitle.getText().trim().isEmpty() && !txtADescription.getText()
             .trim().isEmpty() && !txtFStreet.getText().trim().isEmpty()) {
            btnAccept.setDisable(false);
         }else {
            btnAccept.setDisable(true);
         }
         
         if(txtFTitle.getLength() == 91){
            txtFTitle.setText(txtFTitle.getText().substring(0,90));
            new Alert(AlertType.INFORMATION,"Title too long",ButtonType.OK).show();
         }
         
         if(txtADescription.getLength() == 601){
            txtADescription.setText(txtADescription.getText().substring(0,600));
            new Alert(AlertType.INFORMATION,"Description too long",ButtonType.OK).show();
         }
         
         if(txtAComment.getLength() == 1001){
            txtAComment.setText(txtAComment.getText().substring(0,1000));
            new Alert(AlertType.INFORMATION,"Comment too long",ButtonType.OK).show();
         }
         
         if(txtFStreet.getLength() == 46){
            txtFStreet.setText(txtFStreet.getText().substring(0,45));
            new Alert(AlertType.INFORMATION,"Street too long",ButtonType.OK).show();
         }
     }
     
     
    /**
     * 
     * @param event 
     */
    public void handlePhoto(ActionEvent event){
        //TODO
    } 
    
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event){
        LOGGER.info("Beginning handleAccept");
        boolean isInsert = true;
        IncidentBean incidentInStage = new IncidentBean();
        if(incident != null) {
            isInsert = false;
            incidentInStage = incident;
        } 
        //Insert or update an incident
        incidentInStage.setTitle(txtFTitle.getText());
        incidentInStage.setDescription(txtADescription.getText());
        incidentInStage.setComment("");
        Date date = new Date();
        incidentInStage.setCreateDate(date);
        incidentInStage.setEndDate(date);
        incidentInStage.setEstate(chcEstate.getSelectionModel().getSelectedItem());
        incidentInStage.setUser(user);
        incidentInStage.setPhoto(null);
        //Type
        TypeBean typeInStage = new TypeBean();
        try {
            typeInStage = typeManager.findTypeByName(chcType.getSelectionModel().getSelectedItem());
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,"Exception finding type",ex.getMessage());
        }
        incidentInStage.setType(typeInStage);
        //Location
        LocationBean locationInStage = new LocationBean();
        locationInStage.setStreet(txtFStreet.getText());
        locationInStage.setTownHall(chcLocality.getSelectionModel().getSelectedItem());
        LocationBean location = null;
        boolean continuar = true;
        try {
            location = locationManager.findLocationByStreet(locationInStage);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,"Exception finding location",ex.getMessage());
        }
        if(location == null) {
            if (getAlertConfirmation("This street does not exist in " 
                + chcLocality.getSelectionModel().getSelectedItem()
                .getLocality() + ", if you continue you will create a new "
                + "one. Do you want to continue?").equals(ButtonType.OK)) {
                try {
                    locationManager.createLocation(locationInStage);
                } catch (CreateException ex) {
                    LOGGER.log(Level.SEVERE,
                        "Exception creating location",ex.getMessage());
                }
                try {
                    location = locationManager.findLocationByStreet(locationInStage);
                } catch (ReadException ex) {
                    LOGGER.log(Level.SEVERE,"Exception finding location",ex.getMessage());
                }
            } else {
                continuar = false;
            }
        }
        if(continuar) {
            incidentInStage.setLocation(location);
            if(isInsert) {
                try {
                    incidentManager.createIncident(incidentInStage);
                    new Alert(AlertType.INFORMATION,"Incident added successfully"
                        ,ButtonType.OK).showAndWait();
                    handleIncidents(event);
                    stage.hide();
                } catch (CreateException ex) {
                    LOGGER.log(Level.SEVERE,
                        "IncidentRestFul: Exception adding the incident.", ex.getMessage());
                }
            } else {
                if(!txtAComment.getText().trim().isEmpty()) {
                    incidentInStage.setComment(txtAComment.getText());
                }
                try {
                    incidentManager.editIncident(incidentInStage);
                    new Alert(AlertType.INFORMATION,"Incident updated successfully"
                        ,ButtonType.OK).showAndWait();
                    handleIncidents(event);
                    stage.hide();
                } catch (UpdateException ex) {
                    LOGGER.log(Level.SEVERE,
                        "IncidentRestFul: Exception editting the incident.", ex.getMessage());
                }
            }
            
        }
        LOGGER.info("Ending handleAccept");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleCancel(ActionEvent event){
        LOGGER.info("Beginning handleCancel");
        handleIncidents(event);
        stage.hide();
        LOGGER.info("Ending handleCancel");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleSignature(ActionEvent event) {
        LOGGER.info("Beginning handleSignature");
        List<UserBean> users = null;
        boolean hay = false;
        int x = 1;
        if(incident.getUsers() == null) {
            users = new ArrayList<>();
            users.add(user);
        }else {
            for(UserBean u : incident.getUsers()){
                if(u.getId().toString().equalsIgnoreCase(user.getId().toString())) {
                    hay = true;
                    new Alert(AlertType.INFORMATION,"You have already signatured "
                        + "this incident",ButtonType.OK).show();
                    break;
                }
            }
            if(!hay){
                users = incident.getUsers();
                users.add(user);
                x = users.size();
            }
        }
        if(!hay){
            incident.setUsers(users);
            try {
                incidentManager.editIncident(incident);
                lblSignature.setText(String.valueOf(x));
                LOGGER.info("Incident signatured successfuly");
                new Alert(AlertType.INFORMATION,"Incident signatured successfuly"
                    ,ButtonType.OK).show();
            } catch (UpdateException ex) {
                LOGGER.log(Level.SEVERE,"IncidentRestFul: Exception "
                    + "editting the incident.", ex.getMessage());
            }
        }
        LOGGER.info("Ending handleSignature");
    }
}
