/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.ReadException;
import java.util.logging.Level;
import javabeans.Estate;
import javabeans.TypeBean;
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
    private Label lblTitle;
    @FXML
    private TextField txtFTitle;
    @FXML
    private Label lblDescription;
    @FXML
    private TextArea txtADescription;
    @FXML
    private Label lblComment;
    @FXML
    private TextArea txtAComment;
    @FXML
    private Label lblPhoto;
    @FXML
    private Button btnPhoto;
    @FXML
    private ImageView iVPhoto;
    @FXML
    private Label lblStreet;
    @FXML
    private TextField txtFStreet;
    @FXML
    private Label lblLocality;
    @FXML
    private TextField txtFLocality;
    @FXML
    private ComboBox<TypeBean> chcType;
    @FXML
    private ComboBox<Estate> chcEstate;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblType;
    @FXML
    private Label lblEstate;
    @FXML
    private Button btnSignature;
    @FXML
    private Label lblSignature;
     
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
        txtFLocality.textProperty().addListener(this::textChanged);
        txtFStreet.textProperty().addListener(this::textChanged);
        btnPhoto.setOnAction((event) -> handlePhoto(event));
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
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
            chcEstate.setItems(FXCollections.observableArrayList(Estate.values()));
            chcType.setValue(types.get(0));
            chcEstate.setValue(FXCollections.observableArrayList(Estate.values())
                .get(0));
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                "Exception finding all types",ex.getMessage());
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
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnAccept.setDisable(true);
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        if(incident != null) {
            txtFTitle.setText(incident.getTitle());
            txtADescription.setText(incident.getDescription());
            txtAComment.setText(incident.getComment());
            //Set de la photo
            txtFStreet.setText(incident.getLocation().getStreet());
            txtFLocality.setText(incident.getLocation().getTownHall().getLocality());
            chcType.setValue(incident.getType());
            chcEstate.setValue(incident.getEstate());
            /*lblSignature.setText(String.valueOf(incident.getUsers().size()));*/
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
             .trim().isEmpty() && !txtFLocality.getText().trim().isEmpty() && 
             !txtFStreet.getText().trim().isEmpty()) {
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
         
         if(txtFLocality.getLength() == 46){
            txtFLocality.setText(txtFLocality.getText().substring(0,45));
            new Alert(AlertType.INFORMATION,"Locality too long",ButtonType.OK).show();
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
    } 
    
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event){
        if(!txtFTitle.getText().trim().isEmpty() && !txtFStreet.getText().trim().isEmpty() && !txtFLocality.getText().trim().isEmpty() && !txtADescription.getText().trim().isEmpty()) {
            btnAccept.setDisable(false);
        } else {
            btnAccept.setDisable(true);
        }
        if(super.getAlert("Incident added/updated successfully")==ButtonType.OK){
            handleIncidents(event);
            stage.hide();
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void handleCancel(ActionEvent event){
        if(super.getAlert("Are your sure of this?")==ButtonType.OK){
            handleIncidents(event);
            stage.hide();
        }
    }
}
