/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.GUI009Controller.LOGGER;
import javabeans.TownHallBean;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
}
