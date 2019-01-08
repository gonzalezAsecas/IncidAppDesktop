/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

/**
 *
 * @author Lander Lluvia
 */
public class GUI009AddController extends GUI009Controller{
    
    public void initStage(Parent root) {
        LOGGER.info("Initializing GUI009 Add stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::OnShowingHandler);
        super.btnAccept.setOnAction((event) -> super.handleAccept(event));
        stage.show();
        LOGGER.info("Ending the initializing of the GUI009 Add stage");
    }
    
    public void OnShowingHandler(WindowEvent event){
        super.OnShowingHandlerTownHall(event);
    }
}
