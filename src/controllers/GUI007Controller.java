/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import implementations.LocationImplementation;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.LocationBean;
import javabeans.TownHallBean;
import javabeans.UserBean;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Lander Lluvia
 */
public class GUI007Controller extends AdminGenericController {
    
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu mTownhall;
    @FXML
    private Menu mUsers;
    @FXML
    private Menu mInformation;
    @FXML
    private Menu mLogOut;
    @FXML
    private MenuItem miTownhalls;
    @FXML
    private MenuItem miUsers;
    @FXML
    private MenuItem miInformation;
    @FXML
    private MenuItem miLogOut;
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
    private Button btnReport;
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
            miTownhalls.setOnAction((event) -> super.handleTownHalls(event));
            miUsers.setOnAction((event) -> super.handleUsers(event));
            miInformation.setOnAction((event) -> super.handleInformation(event));
            miLogOut.setOnAction((event) -> super.handleLogOut(event));
            townhallData = FXCollections.observableArrayList(townHallImpl.findAllTownHalls());
            tableTownhalls.setItems(townhallData);
            tcName.setCellValueFactory(new PropertyValueFactory<>("locality"));
            tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tcTelephone .setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            stage.setOnShowing(this::OnShowingHandler);
            btnNewTownhall.setOnAction((event) -> handleNewTownhall(event));
            btnModifyTownhall.setOnAction((event) -> handleModifyTownhall(event));
            btnDelete.setOnAction((event) -> handleDelete(event));
            btnReport.setOnAction((event) -> handleReport(event));
            tableTownhalls.getSelectionModel().selectedItemProperty()
                    .addListener(this::handleTownhallsTable);
            stage.show();
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
        btnReport.setMnemonicParsing(true);
        btnReport.setText("Print _report");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI009AMTH.fxml"));
        Parent root;
        try{
            root = (Parent)loader.load();
            Stage gui009Stage = new Stage();
            GUI009Controller controller = loader.getController();
            controller.setStage(gui009Stage);
            controller.initStage(root);
            gui009Stage.initModality(Modality.APPLICATION_MODAL);
            gui009Stage.setResizable(false);
            gui009Stage.showAndWait();
            TownHallBean th = controller.getTownHall();
            tableTownhalls.getItems().add(th);
            tableTownhalls.refresh();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            ex.printStackTrace();
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
        LOGGER.info("Begginning handleModifyTownhall()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/GUI009AMTH.fxml"));
        Parent root;
        try{
            TownHallBean selectedth = (TownHallBean)tableTownhalls.getSelectionModel().getSelectedItem();
            root = (Parent)loader.load();
            Stage gui009Stage = new Stage();
            GUI009Controller controller = loader.getController();
            controller.setTownHall(selectedth);
            controller.setStage(gui009Stage);
            controller.setEdit(true);
            controller.initStage(root);
            gui009Stage.initModality(Modality.APPLICATION_MODAL);
            gui009Stage.setResizable(false);
            gui009Stage.showAndWait();
            TownHallBean th = controller.getTownHall();
            selectedth = th;
            tableTownhalls.refresh();
            
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in handleUsers()",
                    ex.getMessage());
        }catch(Exception ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleUsers()",
                    ex.getMessage());
            ex.printStackTrace();
        }    
        LOGGER.info("Ending handleNewTownhall()");
    }
    
    /**
     * Deletes the selected townhall
     * @param event 
     */
    public void handleDelete(ActionEvent event){
        LOGGER.info("Begginning handleDelete()");
        Alert alert = null;
        try{
            alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Delete the selected row?", ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                TownHallBean selectedth = (TownHallBean)tableTownhalls.getSelectionModel().getSelectedItem();
                List<UserBean> users = userImpl.findAllUsers();
                for(int i = 0; i < users.size(); i++){
                    UserBean user = users.get(i);
                    if(user.getTH().equals(selectedth)){
                        user.setTH(null);
                        userImpl.editUser(user);
                    }
                }
                Collection<LocationBean> locations = locationImpl.findAllLocations();
                for(LocationBean location : locations){
                    if(location.getTownHall().equals(selectedth)){
                        location.setTownHall(null);
                        locationImpl.editLocation(location);
                    }
                }
                townHallImpl.removeTownHall(selectedth);
                tableTownhalls.getItems().remove(tableTownhalls.getSelectionModel().getSelectedItem());
                tableTownhalls.refresh();
            }
        } catch (DeleteException ex) {
            LOGGER.log(Level.SEVERE, "An error ocurred in handleDelete()",
                    ex.getMessage());
        } catch (ReadException ex) {
            Logger.getLogger(GUI007Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UpdateException ex) {
            Logger.getLogger(GUI007Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    private void handleReport(ActionEvent event) {
        try{
            JasperReport report = JasperCompileManager.compileReport("src/reports/townhallreport.jrxml");
            JRBeanCollectionDataSource dataItems = 
                    new JRBeanCollectionDataSource((Collection<TownHallBean>)this.tableTownhalls.getItems());
            Map<String,Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(GUI007Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
