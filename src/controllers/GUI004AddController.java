/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.CreateException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jon Gonzalez
 */
public class GUI004AddController extends IncidentGenericController{

    
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI003 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Add incident");
        stage.setResizable(true);
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        super.btnPhoto.setOnAction((event) -> super.handlePhoto(event));
        super.btnAccept.setOnAction((event) -> this.handleAccept(event));
        super.btnCancel.setOnAction((event) -> super.handleCancel(event));
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI003 stage");
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        super.OnShowingHandlerIncident(event);
        btnSignature.setDisable(true);
        txtAComment.setDisable(true);
    }
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event){
        try {
            incident.setTitle(txtFTitle.getText());
            incident.setDescription(txtADescription.getText());
            incident.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            incident.setEstate(chcEstate.getValue());
            //Location, mirar como guardarlas
            //
            super.incidentImpl.createIncident(incident);
        } catch (CreateException ex) {
            //hacerle un why
            LOGGER.log(Level.SEVERE, "An error have ocurred adding the incident", ex);
        }
    }
}
