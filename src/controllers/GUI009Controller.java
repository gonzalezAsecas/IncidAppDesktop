/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.CreateException;
import exceptions.UpdateException;
import factories.LogicFactory;
import interfaces.iTownHall;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lander Lluvia
 */
public class GUI009Controller {
    
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");

    @FXML
    protected TextField txtFName;
    @FXML
    protected TextField txtFEmail;
    @FXML
    protected TextField txtFPhone;
    @FXML
    protected Button btnAccept;
    @FXML
    protected Button btnCancel;
    
    protected Stage stage;
    
    /**
     * Setter of the stage
     * @param stage Stage of the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    protected TownHallBean th;
    public void setTownHall(TownHallBean th){
        this.th = th;
    }
    public TownHallBean getTownHall(){
        return th;
    }
    
    TownHallBean townhall;
    public void setTownhall(TownHallBean townhall){
        this.townhall = townhall;
    }
    
    protected boolean alreadyExist = false;
    public void setAlreadyExist(boolean bool){
        alreadyExist = bool;
    }
    protected iTownHall townHallImpl = LogicFactory.getiTownHall();
    
    
    /**
     * Set and initialize the stage and its properties
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI009 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        if(alreadyExist){
            txtFName.setText(townhall.getLocality());
            txtFEmail.setText(townhall.getEmail());
            txtFPhone.setText(townhall.getTelephoneNumber());
        }
        stage.setOnShowing(this::OnShowingHandlerTownHall);
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
        
    }
    
    /**
     * Set the buttons mnemonics
     * @param event 
     */
    public void OnShowingHandlerTownHall(WindowEvent event) {
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
    }
    
    /**
     * Checks if the fields are filleds, and
     * @param event 
     */
    public void handleAccept(ActionEvent event) {
        try{
            if(fieldsAreFilled()){
                th.setLocality(txtFName.getText().trim());
                th.setEmail(txtFEmail.getText().trim());
                th.setTelephoneNumber(txtFPhone.getText().trim());
                townHallImpl.townHallAlreadyExists();
                if(alreadyExist){
                    townHallImpl.editTownHall(th);
                }else{
                    townHallImpl.createTownHall(th);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "All the fields must have information", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "Error creating a townhall");
        } catch (UpdateException ex) {
            Logger.getLogger(GUI009Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Checks if the fields are filled
     * @return 
     */
    public boolean fieldsAreFilled(){
        boolean filled = true;
        if(txtFName.getText().trim().isEmpty() | txtFEmail.getText().trim().isEmpty() | txtFPhone.getText().trim().isEmpty()){
            filled = false;
        }
        return filled;
    }
        
    
    /**
     * 
     * @param message
     * @return 
     */
    public ButtonType getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK, ButtonType.CANCEL);
        return alert.showAndWait().get();
    }
    
    /**
     * 
     * @param event 
     */
    public void handleCancel(ActionEvent event) {
        if(getAlert("Are your sure of this?") == ButtonType.OK){
            stage.close();
        }
    }
    
}
