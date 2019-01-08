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
import javafx.scene.control.Button;
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
    
    protected Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected iTownHall townHallImpl = LogicFactory.getiTownHall();
    
    public void OnShowingHandlerTownHall(WindowEvent event) {
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
    }
    
    public void handleAccept(ActionEvent event) {
        try{
            TownHallBean townhall = new TownHallBean(txtFName.getText(), txtFEmail.getText(), txtFPhone.getText());
            townHallImpl.createTownHall(townhall);
        }catch (Exception e){
            e.printStackTrace();
        }
            
    }
}
