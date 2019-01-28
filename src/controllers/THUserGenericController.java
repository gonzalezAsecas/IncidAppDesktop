/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * This is the generic controller that contains the general methods and 
 * properties of the all the controllers
 * @author Jon Gonzalez
 * @version 1.0
 */
public class THUserGenericController {
    
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    /**
     * The stage for the scene
     */
    protected Stage stage;
    
    /**
     * The user that is login or logged in the application
     */
    protected UserBean user;
    
    /**
     * The setter of the stage
     * @param stage The stage of the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * the setter for the town hall user
     * @param user the object for the user
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
    
    /**
     * The method for get a customized alert sending the message for the user
     * @param message the message that the user is going to read 
     * @return the type of the button clicked
     */
    public ButtonType getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK, ButtonType.CANCEL);
        return alert.showAndWait().get();
    }
    
    /**
     * Load the GUI003 xml and pass the control to it controller 
     * @param event
     */
    public void handleIncidents(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI003SDI.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage stage=new Stage();
            //Load de controller
            GUI003Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI003Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI003Controller.");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error loading GUI003Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI003Controller.");
        }
    }
    
    /**
     * Load the GUI005 xml and pass the control to it controller 
     * @param event
     */
    public void handleFiles(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI005CRUDF.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage stage=new Stage();
            //Load de controller
            GUI005Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI005Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI005Controller.");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error loading GUI005Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI005Controller.");
        }
    }
    
    /**
     * Load the GUI006 xml and pass the control to it controller 
     * @param event
     */
    public void handleInfo(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI006.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage stage=new Stage();
            //Load de controller
            GUI006Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI006Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI006Controller.");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error loading GUI006Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI006Controller.");
        }
    }
    
    /**
     * exit the application if the user click ok button
     * @param event 
     */
    public void handleLogOut(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit the application?", ButtonType.OK, ButtonType.CANCEL);
        if(alert.showAndWait().get().equals(ButtonType.OK)){
            stage.close();
        }
    }
}
