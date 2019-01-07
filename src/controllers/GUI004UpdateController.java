/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jon Gonzalez
 */
public class GUI004UpdateController extends IncidentGenericController{
     
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
        stage.setTitle("Modify incident");
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
    }
    
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event){
        
    }
}
