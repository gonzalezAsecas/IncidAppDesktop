/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import javabeans.Estate;
import javabeans.TypeBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gorka Redondo
 */
public class GUI004UpdateController extends THUserGenericController {
    
    @FXML
    protected Label lblTitle;
    @FXML
    protected TextField txtFTitle;
    @FXML
    protected Label lblDescription;
    @FXML
    protected TextArea txtADescription;
    @FXML
    protected Label lblComment;
    @FXML
    protected TextArea txtAComment;
    @FXML
    protected Label lblPhoto;
    @FXML
    protected Button btnPhoto;
    @FXML
    protected ImageView iVPhoto;
    @FXML
    protected Label lblStreet;
    @FXML
    protected TextField txtFStreet;
    @FXML
    protected ComboBox<TypeBean> chcType;
    @FXML
    protected ComboBox<Estate> chcEstate;
    @FXML
    protected Button btnAccept;
    @FXML
    protected Button btnCancel;
    @FXML
    protected Label lblType;
    @FXML
    protected Label lblEstate;
    @FXML
    protected Button btnSignature;
    @FXML
    protected Label lblSignature;
    
    /**
     * 
     */
    //protected iType typeImpl = LogicFactory.getiType();
    
    /**
     * 
     */
    //protected iIncident incidentImpl = LogicFactory.getiIncident();
     
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI004 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Modify incident");
        stage.setResizable(true);
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        btnPhoto.setOnAction(this::handlePhoto);
        btnAccept.setOnAction(this::handleAccept);
        btnCancel.setOnAction(this::handleCancel);
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI003 stage");
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event) {
        btnSignature.setMnemonicParsing(true);
        btnSignature.setText("_Sign it");
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        //txtFTitle.setText(incident.getTitle());
        //txtADescription.setText(incident.getDescription());
        //txtAComment.setText(incident.getComment());
        //Set de la photo
        //txtFStreet.setText(incident.getLocation().getStreet() + ", " +incident.getLocation().getTownHall().getLocality() );
        //cargar type y setear el que sea
        //cargar estate y setear el que sea
        //lblSignature.setText(String.valueOf(incident.getUsers().size()));
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
        //super.incidentImpl.editIncident(incident);
    }
    
    /**
     * 
     * @param event 
     */
    public void handleCancel(ActionEvent event){
        if(super.getAlert("Are your sure of this?")==ButtonType.OK){
            stage.hide();
        }
    }
}
