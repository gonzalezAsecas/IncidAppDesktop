/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.LogicFactory;
import interfaces.iIncident;
import interfaces.iType;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
     * The getter of the stage
     * @return stage The stage of the application
     */
    public Stage getStage() {
        return stage;
    }
    /**
     * The setter of the stage
     * @param stage The stage of the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    /**
     * The business logic object
     */
    protected iIncident incidentManager = LogicFactory.getiIncident();
    /**
     * The getter of the incidentManager
     * @return incidentManager The incidentManager of the application
     */
    public iIncident getIncidentManager() {
        return incidentManager;
    }
    /**
     * The setter of the incidentManager
     * @param incidentManager The incidentManager of the application
     */
    public void setIncidentManager(iIncident incidentManager) {
        this.incidentManager = incidentManager;
    }
    
    
    /**
     * The business logic object
     */
    protected iType typeManager = LogicFactory.getiType();
    /**
     * The getter of the typeManager
     * @return typeManager The typeManager of the application
     */
    public iType getTypeManager() {
        return typeManager;
    }
    /**
     * The setter of the typeManager
     * @param typeManager The typeManager of the application
     */
    public void setTypeManager(iType typeManager) {
        this.typeManager = typeManager;
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
    /*public void handleIncidents(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxmls/GUI003SDI.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui003Stage = new Stage();
            //Load de controller
            GUI003Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(gui003Stage);
            //Pass the user to the next window
            //controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI003Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI003Controller.");
        }catch(Exception ex){
            ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "An error loading GUI003Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI003Controller.");
        }
    }*/
    
    /**
     * Load the GUI004 xml and pass the control to it controller 
     * @param event
     */
    /*public void handleIncidentsEmpty(ActionEvent event) {
        //Create the loader for the xml
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxmls/GUI004SAMI.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui004Stage = new Stage();
            //Load de controller
            GUI004AddController controller = loader.getController();
            //Set the new stage
            controller.setStage(gui004Stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI004Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI004Controller.");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error loading GUI004Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI004Controller.");
        }
    }*/
    
    /**
     * Load the GUI004 xml and pass the control to it controller 
     * @param event
     */
    /*public void handleIncidentsFull(ActionEvent event) {
        //Create the loader for the xml
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxmls/GUI004SAMI.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui004Stage = new Stage();
            //Load de controller
            GUI004UpdateController controller = loader.getController();
            //Set the new stage
            controller.setStage(gui004Stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the incident to the next window
            controller.setIncident(incident);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "An input-output error loading GUI004Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI004Controller.");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error loading GUI004Controller.", 
                    ex.getMessage());
            this.getAlert("A error have ocurred loading the GUI004Controller.");
        }
    }*/
    
    /**
     * Load the GUI005 xml and pass the control to it controller 
     * @param event
     */
    /*public void handleFiles(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI005CRUDF.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui005Stage = new Stage();
            //Load de controller
            GUI005Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(gui005Stage);
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
    }*/
    
    /**
     * Load the GUI006 xml and pass the control to it controller 
     * @param event
     */
    /*public void handleInfo(ActionEvent event){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI006.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui006Stage = new Stage();
            //Load de controller
            GUI006Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(gui006Stage);
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
    }*/
    
    /**
     * exit the application if the user click ok button
     * @param event 
     */
    public void handleLogOut(ActionEvent event){
        if(getAlert("Do you want to exit the application?").equals(ButtonType.OK)){
            stage.close();
        }
    }
}
