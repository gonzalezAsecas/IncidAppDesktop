/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.LogicFactory;
import interfaces.iTownHall;
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
     * 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected iTownHall townHallImpl = LogicFactory.getiTownHall();
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI009 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::OnShowingHandlerTownHall);
        btnAccept.setOnAction((event) -> handleAccept(event));
        btnCancel.setOnAction((event) -> handleCancel(event));
        
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandlerTownHall(WindowEvent event) {
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event) {
        try{
            TownHallBean townhall = new TownHallBean(txtFName.getText(), txtFEmail.getText(), txtFPhone.getText());
            if(fieldsAreFilled()){
                //townHallAlreadyExists(townhall);
                townHallImpl.createTownHall(townhall);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "All the fields must have information", ButtonType.OK);
                alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //public boolean townHallAlreadyExists(TownHallBean townhall){
        //TODO: con que dato debemos comparar?
        //Obtenemos en una variable todos los townhall y los comparamos
        //con el actual?
    //}
    
    public boolean fieldsAreFilled(){
        boolean filled = true;
        if(txtFName.getText().isEmpty() | txtFEmail.getText().isEmpty() | txtFPhone.getText().isEmpty()){
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
