/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.LogicFactory;
import interfaces.iIncident;
import interfaces.iType;
import javabeans.Estate;
import javabeans.IncidentBean;
import javabeans.TypeBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * @author Jon Gonzalez
 */
public class IncidentGenericController extends THUserGenericController{
    
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
    
    protected iType typeImpl = LogicFactory.getiType();
    
    protected iIncident incidentImpl = LogicFactory.getiIncident();
    
    protected IncidentBean incident;

    public void setIncident(IncidentBean incident) {
        this.incident = incident;
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandlerIncident(WindowEvent event){
        btnSignature.setMnemonicParsing(true);
        btnSignature.setText("_Sign it");
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        txtFTitle.setText(incident.getTitle());
        txtADescription.setText(incident.getDescription());
        txtAComment.setText(incident.getComment());
        //Set de la photo
        txtFStreet.setText(incident.getLocation().getStreet() + ", " +incident.getLocation().getTownHall().getLocality() );
        //cargar type y setear el que sea
        //cargar estate y setear el que sea
        lblSignature.setText(String.valueOf(incident.getUsers().size()));
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
    public void handleCancel(ActionEvent event){
        if(super.getAlert("Are your sure of this?")==ButtonType.OK){
            stage.close();
        }
    }
}