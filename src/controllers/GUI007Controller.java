/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.ReadException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.TownHallBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lander Lluvia
 */
public class GUI007Controller extends AdminGenericController{
    
    @FXML
    private Menu mTownhall;
    @FXML
    private Menu mUsers;
    @FXML
    private Menu mInformation;
    @FXML
    private Menu mLogOut;
    @FXML
    private TableColumn<?, ?> tcName;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcTelephone;
    @FXML
    private Button btnNewTownhall;
    @FXML
    private Button btnModifyTownhall;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView tableTownhalls;
    
    /**
     * Contains all the Townhalls to show them in the tableview
     */
    private ObservableList<TownHallBean> townhallData;
    
    /**
     * Set and initialize the stage and its properties
     * @param root 
     */
    public void initStage(Parent root) {
        LOGGER.info("Initializing GUI007 stage");
        try{
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Townhalls");
            stage.setResizable(true);
            townhallData = FXCollections.observableArrayList(townHallImpl.findAllTownHalls());
            tableTownhalls.setItems(townhallData);
            tcName.setCellValueFactory(new PropertyValueFactory<>("locality"));
            tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tcTelephone .setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            stage.setOnShowing(this::OnShowingHandler);
            mUsers.setOnAction((event) -> super.handleUsers(event));
            mInformation.setOnAction((event) -> super.handleInformation(event));
            mLogOut.setOnAction((event) -> super.handleLogOut(event));
            btnNewTownhall.setOnAction((event) -> handleNewTownhall(event));
            btnModifyTownhall.setOnAction((event) -> handleModifyTownhall(event));
            btnDelete.setOnAction((event) -> handleDelete(event));
            tableTownhalls.getSelectionModel().selectedItemProperty()
                    .addListener(this::handleTownhallsTable);
        } catch (ReadException ex) {
            Logger.getLogger(GUI007Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Set attributes to the controls that it need when the window is shown
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler()");
        mTownhall.setDisable(true);
        //Set the mnemonics
        btnNewTownhall.setMnemonicParsing(true);
        btnNewTownhall.setText("_New Townhall");
        btnModifyTownhall.setDisable(true);
        btnModifyTownhall.setMnemonicParsing(true);
        btnModifyTownhall.setText("_Modify Townhall");
        btnDelete.setDisable(true);
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        LOGGER.info("Ending OnShowingHandler()");
    }
    
    /**
     * Show a new modal window that allows to introduce the values of the new 
     * townhall
     * @param event 
     */
    public void handleNewTownhall(ActionEvent event){
        //TODO: como obtenemos y devolvemos los datos de la ventana?
        LOGGER.info("Begginning handleNewTownhall()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI009.fxml"));
        Parent root;
        try{
            root = (Parent)loader.load();
            Stage gui009Stage = new Stage();
            GUI009AddController controller = loader.getController();
            controller.setStage(gui009Stage);
            controller.initStage(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleUsers()",
                    ex.getMessage());
        }    
        LOGGER.info("Ending handleNewTownhall()");
    }
    
    /**
     * Show a new modal window that allows to modify the values of the selected
     * townhall
     * @param event 
     */
    public void handleModifyTownhall(ActionEvent event){
        //TODO: como obtenemos y devolvemos los datos de la ventana?
        LOGGER.info("Begginning handleModifyTownhall()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI009.fxml"));
        Parent root;
        try{
            TownHallBean selectedth = (TownHallBean)tableTownhalls.getSelectionModel().getSelectedItem();
            root = (Parent)loader.load();
            Stage gui009Stage = new Stage();
            GUI009UpdateController controller = loader.getController();
            controller.setTownhall(selectedth);
            controller.setStage(gui009Stage);
            controller.initStage(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleUsers()",
                    ex.getMessage());
        }    
        LOGGER.info("Ending handleNewTownhall()");
    }
    
    /**
     * Deletes the selected townhall
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        LOGGER.info("Begginning handleDelete()");
        tableTownhalls.getItems().remove(tableTownhalls.getSelectionModel().getSelectedItem());
        tableTownhalls.refresh();
        LOGGER.info("Ending handleDelete()");
    }
    
    /**
     * Enables and disables the buttons Modify townhall and Delete
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handleTownhallsTable(ObservableValue observable,
            Object oldValue, Object newValue) {
        if(newValue != null) {
            btnModifyTownhall.setDisable(false);
            btnDelete.setDisable(false);
        }else{
            btnModifyTownhall.setDisable(true);
            btnDelete.setDisable(true);
        }
    }
}
