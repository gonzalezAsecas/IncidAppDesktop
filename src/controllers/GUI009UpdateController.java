/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.GUI009Controller.LOGGER;
import exceptions.CreateException;
import exceptions.UpdateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Lander Lluvia
 */
public class GUI009UpdateController extends GUI009Controller{
    
    TownHallBean townhall;
    
    /**
     * 
     * @param townhall 
     */
    public void setTownhall(TownHallBean townhall){
        this.townhall = townhall;
    }
    
    /**
     * 
     * @param root 
     */
    @Override
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI009 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::OnShowingHandlerTownHall);
        txtFName.setText(townhall.getLocality());
        txtFEmail.setText(townhall.getEmail());
        txtFPhone.setText(townhall.getTelephoneNumber());
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
    }
    
    @Override
    public void handleAccept(ActionEvent event) {
        try{
            if(fieldsAreFilled()){
                th.setLocality(txtFName.getText().trim());
                th.setEmail(txtFEmail.getText().trim());
                th.setTelephoneNumber(txtFPhone.getText().trim());
                townHallImpl.townHallAlreadyExists();
                townHallImpl.editTownHall(th);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "All the fields must have information", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "Error updating a townhall");
        }
    }
}
