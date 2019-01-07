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
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Lander Lluvia
 */
public class GUI007Controller {

    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
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
    
    protected Stage stage;
    
    protected iTownHall townHallImpl = LogicFactory.getiTownHall();
    
    protected TownHallBean townhall;

    public void setTownHall(TownHallBean townhall) {
        this.townhall = townhall;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void initStage(Parent root) {
        LOGGER.info("Initializing GUI007 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Townhalls");
        stage.setResizable(true);
        stage.setOnShowing(this::OnShowingHandler);
        mUsers.setOnAction();
        mInformation.setOnAction();
        mLogOut.setOnAction();
        btnNewTownhall.setOnAction();
        btnModifyTownhall.setOnAction();
        btnDelete.setOnAction();
        //TODO: cargas los datos de la tabla
        
    }
    
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler");
        mTownhall.setDisable(true);
        //Set the mnemonics
        btnNewTownhall.setMnemonicParsing(true);
        btnNewTownhall.setText("_New Townhall");
        btnModifyTownhall.setMnemonicParsing(true);
        btnModifyTownhall.setText("_Modify Townhall");
        btnDelete.setMnemonicParsing(true);
        btnDelete.setText("_Delete");
        //load the all data
        //loadData(); ¿? ver loadData GUI003Controller
        LOGGER.info("Ending OnShowingHandler");
    }
    
}
