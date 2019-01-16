/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.ReadException;
import factories.LogicFactory;
import interfaces.iUser;
import javabeans.UserBean;
import java.io.IOException;
import java.util.logging.Level;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * GUI001 FXML Controller class, in this window you can connect to the main 
 * application of the admin and the town hall user or go to the sign up window. 
 * The application begin in this window
 * @author Jon Gonzalez
 * @version 1.0
 */
public class GUI001Controller extends THUserGenericController{

    @FXML
    private Label lblUser;
    @FXML
    private Label lblPass;
    @FXML
    private TextField txtFUser;
    @FXML
    private PasswordField pwPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Hyperlink hlPasswordForget;
    
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI001 stage");
        //Ceate the scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Login");
        stage.setResizable(false);
        //Set window's event handlers and the textfield's promptext
        stage.setOnShowing(this::handleWindowShowing);
        btnLogIn.setOnAction(this::handleLogIn);
        txtFUser.textProperty().addListener(this::handleTextChanged);
        txtFUser.setPromptText("Set the username...");
        pwPassword.textProperty().addListener(this::handleTextChanged);
        pwPassword.setPromptText("Set the password...");
        hlPasswordForget.setOnAction(this::handleRecoverPassword);
        //Show the LogIn window
        stage.show();
        LOGGER.info("Ending the initialization of the GUI001 stage");
    }
    
    /**
     * Set atributes to the controls that it need in the window showing event
     * @param event
     */
    public void handleWindowShowing(WindowEvent event){
        LOGGER.info("Beginning handleWindowShowing");
        //Set the mnemonic parse
        btnLogIn.setMnemonicParsing(true);
        hlPasswordForget.setMnemonicParsing(true);
        //Set the mnemonic character and the text
        btnLogIn.setText("_Login");
        hlPasswordForget.setText("_Click here");
        /*
        stage.addEventHandler(KeyEvent.KEY_PRESSED, ev ->{
            if(ev.getCode()==KeyCode.ENTER){
                logIn();
            }
        });
        */
        LOGGER.info("Ending handleWindowShowing");
    }
    
    /**
     * Verify if there are empty fields and modify button login's setDisable 
     * method. If there are any field empty, disable the login button, else 
     * enable it. Also verify that the person who is writting don't write 
     * more than the maximum of characters.
     * @param observable
     * @param oldvalue
     * @param newvalue 
     */
    public void handleTextChanged(ObservableValue observable, 
            String oldvalue, String newvalue){
        //The verification of the emptiness of the fields
        if(txtFUser.getText().trim().isEmpty() || 
                pwPassword.getText().trim().isEmpty()){
            btnLogIn.setDisable(true);
        }else{
            btnLogIn.setDisable(false);
        }
        //The control of the maximum of characters
        if(txtFUser.getText().length()>20){
            String login = txtFUser.getText();
            login = login.substring(0, login.length()-1);
            txtFUser.setText(login);
            super.getAlert(
                    "The login can´t had more than twenty characters.");
            txtFUser.requestFocus();
        }else if(pwPassword.getText().length()>16){
            String password= pwPassword.getText();
            password = password.substring(0, password.length()-1);
            pwPassword.setText(password);
            super.getAlert( 
                    "The password can´t had more than sixteen characters.");
            pwPassword.requestFocus();
        }
    }
    
    /**
     * Verify that the textfields are filled and send to the server to verify 
     * that the login exist and the password isn't the same. If the login don't 
     * exist or the login exist but the password is the same that he has, 
     * notify to the user.
     * @param event
     */
    public void handleRecoverPassword(ActionEvent event){
        LOGGER.info("Beginning handleRecoverPassword");
        //Verify that the login textfield isn't empty
        if(txtFUser.getText().isEmpty()){
            super.getAlert("You need to put the login in the user field");
            txtFUser.requestFocus();
            //Verify that the password textfield isn't empty
        }else if(pwPassword.getText().isEmpty()){
            super.getAlert("You need to put the new password for change it");
            pwPassword.requestFocus();
        }else{
            //get the implementation of the iUser interface
            iUser iuser= LogicFactory.getiUser();
            //get the user wrote
            user.setLogin(txtFUser.getText());
            //get the password wrote
            user.setPassword(pwPassword.getText());//Encriptar
            try {
                //send the password and the login for the password change
                iuser.findUserToChangePassword(user);
            } catch (ReadException ex) {
                //Run when the login don't exist
                /*if(ex.getWhy().equals("login")){
                    LOGGER.log(Level.SEVERE,"The user don´t exist",ex);
                    super.getAlert("This user don´t exist.");
                    txtFUser.requestFocus();
                    lblUser.setTextFill(Color.web("#ff0000"));
                    lblPass.setTextFill(Color.web("#237bf7"));
                //Run when the login is correct and the password is the same
                //that is trying to change
                }else if(ex.getWhy().equals("password")){
                    LOGGER.log(Level.SEVERE, "GUI001Controller: Exception with the password", ex);
                    lblUser.setTextFill(Color.web("#237bf7"));
                    lblPass.setTextFill(Color.web("#ff0000"));
                    pwPassword.requestFocus();
                    super.getAlert("The password is the same.");
                }*/
            }
        }
        LOGGER.info("Ending handleRecoverPassword");
    }
    
    /**
     * Send the user that it's write in the text fields to the logic a receive
     * the answer in form of or the user with all the information or 
     * an exception saying the problem 
     * @param event
     */
    public void handleLogIn(ActionEvent event) {
        LOGGER.info("Beginning handleLogIn");
        iUser iuser= LogicFactory.getiUser();
        //Get the username and the password
        user=new UserBean();
        user.setLogin(txtFUser.getText());
        user.setPassword(pwPassword.getText());// TODO: Sacar el hash!!!!
        try{
            //Send the user to compare the infomation with the database 
            //information and receive the user that it's login, 
            //with all infomation
            user = iuser.findUserbyLogin(user);
            switch (user.getPrivilege()) {
                //if the user is an admin do this
                case ADMIN:
                    adminLogin(user);
                    break;
                //else if the user is a town hall user do this
                case TOWNHALLUSER:
                    townHallUserLogin(user);
                    break;
                //else if the user is a simple user do this
                case USER:
                    super.getAlert("You don't have permissions for enter in this application.");
                    break;
            }
        }catch(ReadException e1){
            //Run when the login isn't in the database
            /*if(e1.getWhy().equals("login")){
                LOGGER.log(Level.SEVERE, "GUI001Controller: Exception with the login", e1);
                lblUser.setTextFill(Color.web("#ff0000"));
                lblPass.setTextFill(Color.web("#237bf7"));
                txtFUser.requestFocus();
                super.getAlert("The user doesn't exist.");
            //Run when the login is correct but the password no
            }else if(e1.getWhy().equals("password")){
                LOGGER.log(Level.SEVERE, "GUI001Controller: Exception with the password", e1);
                lblUser.setTextFill(Color.web("#237bf7"));
                lblPass.setTextFill(Color.web("#ff0000"));
                pwPassword.requestFocus();
                super.getAlert("The password is wrong.");
            }*/
        }
        LOGGER.info("Ending handleLogIn");
    }
    
    /**
     * Load the GUI007 xml and pass the control to it controller 
     * @param user the user that is going to login in the application
     */
    public void adminLogin(UserBean user){
        LOGGER.info("Beginning adminLogin");
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI007.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui007Stage=new Stage();
            //Load de controller to the admin application main window
            GUI007Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(gui007Stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in the logOut loader.", 
                    ex.getMessage());
            super.getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            super.getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }
        
        LOGGER.info("Ending adminLogin");
    }
    
    /**
     * Load the GUI003 xml and pass the control to it controller
     * @param user the user that is going to login in the application
     */
    public void townHallUserLogin(UserBean user){
        LOGGER.info("Beginning townHallUserLogin");
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/fxmls/GUI003.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage gui003Stage=new Stage();
            //Load de controller to the town hall user application main window
            GUI003Controller controller = loader.getController();
            //Set the new stage
            controller.setStage(gui003Stage);
            //Pass the user to the next window
            controller.setUser(user);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, "An input-output error in the logOut loader.", 
                    ex.getMessage());
            super.getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            super.getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }
        LOGGER.info("Ending townHallUserLogin");
    }
}
