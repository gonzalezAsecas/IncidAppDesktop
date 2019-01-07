/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Jon Gonzalez
 */
public class Application extends javafx.application.Application{
    
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOGGER.info("Launching the application");
        launch();
    }
    
    /**
     * The start method all the fx applications needs for work 
     * @param stage the stage of the aplication
     * @throws Exception 
     */
    @Override
    public void start(Stage stage){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxmls/GUI001LI.fxml"));
        Parent root;
        try {
            root = (Parent)loader.load();
            GUI001Controller controller = loader.getController();
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error has ocurred with the application", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
}
