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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    protected boolean edit = false;
    public void setEdit(boolean bool){
        edit = bool;
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
        if(edit){
            txtFName.setText(th.getLocality());
            txtFEmail.setText(th.getEmail());
            txtFPhone.setText(th.getTelephoneNumber());
        }
        stage.setOnShowing(this::OnShowingHandlerTownHall);
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
        txtFName.textProperty().addListener(this::textChanged);
        txtFEmail.textProperty().addListener(this::textChanged);
        txtFPhone.textProperty().addListener(this::textChanged);
        
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
    
    public void textChanged(ObservableValue observable, String oldValue,
            String newValue){
        if(txtFName.getText().trim().isEmpty() | txtFEmail.getText().trim().isEmpty()
                | txtFPhone.getText().trim().isEmpty()){
            btnAccept.setDisable(true);
        }else{
            btnAccept.setDisable(false);
        }
        
        if(txtFName.getLength() == 256){
            LOGGER.log(Level.INFO, "Name too long");
            txtFName.setText(txtFName.getText().substring(0, 255));
            new Alert(AlertType.INFORMATION, "Name too long", ButtonType.OK).showAndWait();
        }

        if(txtFEmail.getLength() == 256){
            LOGGER.log(Level.INFO, "Email too long");
            txtFEmail.setText(txtFName.getText().substring(0, 255));
            new Alert(AlertType.INFORMATION, "Email too long", ButtonType.OK).showAndWait();
        }

        if(txtFPhone.getLength() == 13){
            LOGGER.log(Level.INFO, "Phone too long");
            txtFPhone.setText(txtFPhone.getText().substring(0, 12));
            new Alert(AlertType.INFORMATION, "Phone too long", ButtonType.OK).showAndWait();
        }
        
    }
    
    /**
     * Checks if the fields are filled, and
     * @param event 
     */
    public void handleAccept(ActionEvent event) {
        try{
            if(fieldsAreFilled()){
                //townHallImpl.townHallAlreadyExists();
                if(edit){
                    th.setLocality(txtFName.getText().trim());
                    th.setEmail(txtFEmail.getText().trim());
                    th.setTelephoneNumber(txtFPhone.getText().trim());
                    townHallImpl.editTownHall(th);
                    stage.close();
                }else{
                    th = new TownHallBean(txtFName.getText().trim(), txtFEmail.getText().trim(), txtFPhone.getText().trim());
                    townHallImpl.createTownHall(th);
                    stage.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "All the fields must have information", ButtonType.OK);
                alert.showAndWait();
            }
        }catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "Error creating a townhall");
        }catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "Error updating a townhall");
        }/*catch (TownhallExistsException ex){
            LOGGER.log(Level.SEVERE, "Townhall already exists");
        }*/
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
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK, ButtonType.CANCEL);
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
