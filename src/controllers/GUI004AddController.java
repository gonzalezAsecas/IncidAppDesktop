/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.THUserGenericController.LOGGER;
import exceptions.CreateException;
import javabeans.Estate;
import javabeans.TypeBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;


/**
 *
 * @author Gorka Redondo
 */
public class GUI004AddController extends THUserGenericController {
    
    @FXML
    private TextField txtFTitle;
    @FXML
    private TextArea txtADescription;
    @FXML
    private TextArea txtAComment;
    @FXML
    private Button btnPhoto;
    @FXML
    private ImageView iVPhoto;
    @FXML
    private TextField txtFStreet;
    @FXML
    private ComboBox<TypeBean> chcType;
    @FXML
    private ComboBox<Estate> chcEstate;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignature;
    
    /**
     * 
     */
    //protected iType typeImpl = LogicFactory.getiType();
    
    /**
     * 
     */
    //protected iIncident incidentImpl = LogicFactory.getiIncident();
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI004 stage");
        //Create the scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Add incident");
        stage.setResizable(true);
        //Set the window's event handlers handle
        stage.setOnShowing(this::OnShowingHandler);
        btnPhoto.setOnAction((event) -> this.handlePhoto(event));
        btnAccept.setOnAction((event) -> this.handleAccept(event));
        btnCancel.setOnAction((event) -> this.handleCancel(event));
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI004 stage");
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event) {
        LOGGER.info("Beginning OnShowingHandler");
        btnSignature.setMnemonicParsing(true);
        btnSignature.setText("_Sign it");
        btnSignature.setDisable(true);
        btnAccept.setMnemonicParsing(true);
        btnAccept.setText("_Accept");
        btnCancel.setMnemonicParsing(true);
        btnCancel.setText("_Cancel");
        txtAComment.setDisable(true);
        LOGGER.info("Ending OnShowingHandler");
    }
    
    /**
     * 
     * @param event 
     */
    public void handlePhoto(ActionEvent event){
    }
    
    /**
     * 
     * @param event 
     */
    public void handleAccept(ActionEvent event){
    } 
    
    /**
     * 
     * @param event 
     */
    public void handleCancel(ActionEvent event){
        if(super.getAlert("Are your sure of this?")==ButtonType.OK){
            stage.close();
        }
    }
}
