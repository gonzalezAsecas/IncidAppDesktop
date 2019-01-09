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
    
    public void setTownhall(TownHallBean townhall){
        this.townhall = townhall;
    }
    
    @Override
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI009 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::OnShowingHandlerTownHall);
        btnAccept.setOnAction((event) -> handleAccept(event));
    }
}
