/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.LogicFactory;
import interfaces.iTownHall;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import javabeans.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Lander Lluvia
 */
public class AdminGenericController {
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    protected Stage stage;
    /**
     * 
     * @param stage 
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    protected UserBean user;
    /**
     * 
     * @param user 
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
    
    protected iTownHall townHallImpl = LogicFactory.getiTownHall();
    
    protected TownHallBean townhall;
    /**
     * 
     * @param townhall 
     */
    public void setTownHall(TownHallBean townhall) {
        this.townhall = townhall;
    }
    
    /**
     * 
     * @param event 
     */
    public void handleTownHalls(ActionEvent event){
        LOGGER.info("Beginning handleTownHalls()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI007SDTH.fxml"));
        Parent root;
        try{
            root = (Parent)loader.load();
            Stage gui007Stage = new Stage();
            GUI007Controller controller = loader.getController();
            controller.setStage(gui007Stage);
            controller.setUser(user);
            controller.initStage(root);
            gui007Stage.show();
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleUsers()",
                    ex.getMessage());
        }    
        LOGGER.info("Ending handleUSers()");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleUsers(ActionEvent event) {
        LOGGER.info("Beginning handleUsers()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI008CRUDTH.fxml"));
        Parent root;
        try{
            root = (Parent)loader.load();
            Stage gui008Stage = new Stage();
            GUI008Controller controller = loader.getController();
            //Los metodos dan error ya que el controller GUI008 no esta hecho
            //controller.setStage(gui008Stage);
            //controller.setUser(user);
            //controller.initStage(root);
            gui008Stage.show();
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleUsers()",
                    ex.getMessage());
        }    
        LOGGER.info("Ending handleUSers()");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleInformation(ActionEvent event){
        LOGGER.info("Beginning handleInformation()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI006MD.fxml"));
        Parent root;
        try{
            root = (Parent)loader.load();
            Stage gui006Stage = new Stage();
            GUI006Controller controller = loader.getController();
            controller.setStage(gui006Stage);
            controller.setUser(user);
            controller.initStage(root);
            gui006Stage.show();
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleInformation()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleInformation()",
                    ex.getMessage());
        }    
        LOGGER.info("Ending handleInformation()");
    }
    
    /**
     * 
     * @param message
     * @return 
     */
    public ButtonType getAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK, ButtonType.CANCEL);
        return alert.showAndWait().get();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleLogOut(ActionEvent event){
        LOGGER.info("Begginning handleLogOut()");
        if(getAlert("Do you want to exit the application?").equals(ButtonType.OK)){
            stage.close();
        }
        LOGGER.info("Ending handleLogOut()");
    }
}
