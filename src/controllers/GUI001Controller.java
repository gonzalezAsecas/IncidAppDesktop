/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.CreateException;
import exceptions.ReadException;
import factories.LogicFactory;
import interfaces.iUser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javabeans.UserBean;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.Privilege;
import javabeans.Status;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * GUI001 FXML Controller class, in this window you can connect to the main 
 * application of the admin and the town hall user or go to the sign up window. 
 * The application begin in this window
 * @author Jon Gonzalez
 * @version 1.0
 */
public class GUI001Controller{

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
    
    iUser iuser= LogicFactory.getiUser();
    
    /**
     * The logger for the desktop app
     */
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    /**
     * The stage for the scene
     */
    protected Stage stage;
    
    /**
     * The user that is login or logged in the application
     */
    protected UserBean user;
    
    /**
     * The setter of the stage
     * @param stage The stage of the application
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * the setter for the user
     * @param user the object for the user
     */
    public void setUser(UserBean user) {
        this.user = user;
    }
    
    /**
     * The method for get a customized alert sending the message for the user
     * @param message the message that the user is going to read 
     * @return the type of the button clicked
     */
    public ButtonType getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK, ButtonType.CANCEL);
        return alert.showAndWait().get();
    }
    
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
        makeUserInShow();
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
            getAlert("The login can´t had more than twenty characters.");
            txtFUser.requestFocus();
        }else if(pwPassword.getText().length()>16){
            String password= pwPassword.getText();
            password = password.substring(0, password.length()-1);
            pwPassword.setText(password);
            getAlert("The password can´t had more than sixteen characters.");
            pwPassword.requestFocus();
        }
    }
    
    /**
     * Verify that the textfield is filled and send to the server to verify 
     * that the login exist and the password isn't the same. If the login don't 
     * exist or the login exist but the password is the same that he has, 
     * notify to the user.
     * @param event
     */
    public void handleRecoverPassword(ActionEvent event){
        LOGGER.info("Beginning handleRecoverPassword");
        //Verify that the login textfield isn't empty
        if(txtFUser.getText().isEmpty()){
            getAlert("You need to put the login in the user field");
            txtFUser.requestFocus();
        }else{
            //get the implementation of the iUser interface
            iUser iuser= LogicFactory.getiUser();
            //get the user wrote
            user.setLogin(txtFUser.getText());
            try {
                //send the login for the password change
                iuser.findUserToChangePassword(user);
            } catch (ReadException ex) {
                LOGGER.log(Level.SEVERE,"The user don´t exist",ex);
                getAlert("This user don´t exist.");
                txtFUser.requestFocus();
                lblUser.setTextFill(Color.web("#ff0000"));
                lblPass.setTextFill(Color.web("#237bf7"));
            }
        }
        LOGGER.info("Ending handleRecoverPassword");
    }
    
    /**
     * Send the user that it's write in the text fields to the logic a receive
     * the answer in form of the user with all the information or 
     * an exception saying the problem 
     * @param event
     */
    public void handleLogIn(ActionEvent event) {
        LOGGER.info("Beginning handleLogIn");
        //Get the username and the password
        user=new UserBean();
        user.setLogin(txtFUser.getText());
        user.setPassword(cypherPass(pwPassword.getText()));
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
                    getAlert("You don't have permissions for enter "
                            + "in this application.");
                    break;
            }
        }catch(ReadException e1){
            //Run when the login isn't in the database
            /*if(e1.getWhy().equals("login")){
                LOGGER.log(Level.SEVERE,
                        "GUI001Controller: Exception with the login", e1);
                lblUser.setTextFill(Color.web("#ff0000"));
                lblPass.setTextFill(Color.web("#237bf7"));
                txtFUser.requestFocus();
                getAlert("The user doesn't exist.");
            //Run when the login is correct but the password no
            }else if(e1.getWhy().equals("password")){
                LOGGER.log(Level.SEVERE,
                        "GUI001Controller: Exception with the password", e1);
                lblUser.setTextFill(Color.web("#237bf7"));
                lblPass.setTextFill(Color.web("#ff0000"));
                pwPassword.requestFocus();
                getAlert("The password is wrong.");
            }*/
        }catch(Exception e3){
            LOGGER.log(Level.SEVERE, e3.getMessage(), e3);
            lblUser.setTextFill(Color.web("#237bf7"));
            lblPass.setTextFill(Color.web("#237bf7"));
            getAlert("An error with the program has ocurred.");
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
            LOGGER.log(Level.SEVERE,
                    "An input-output error in the logOut loader.", ex.getMessage());
            getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            getAlert("A error have ocurred in the login.");
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
            getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            getAlert("A error have ocurred in the login.");
            txtFUser.requestFocus();
        }
        LOGGER.info("Ending townHallUserLogin");
    }
    
    /**
     * Take the password in raw and return it chyper with rsa algorithm
     * @param password the password in raw that is going to be encrypted
     * @return the password encrypted
     */
    private String cypherPass(String password) {
        LOGGER.info("Beginning cypherPass");
        FileInputStream fispublic;
        byte[] key;
        KeyFactory keyFactory;
        PublicKey publicKey;
        Cipher cipher;
        try{
            //open the stream for read the public key file 
            fispublic = new FileInputStream("public.key");
            //set the size for the byte array
            key = new byte[fispublic.available()];
            //read the file
            fispublic.read(key);
            //close the stream
            fispublic.close();
            //instance the factory with the rsa algorithm
            keyFactory = KeyFactory.getInstance("RSA");
            //generate the public key from the information of the file
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(key));
            //instance the cipher object with the algorithm rsa
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //intialize the cipher object for the encrypt mode with the public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            password = new String(cipher.doFinal(password.getBytes()));
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Exception reading the file", ex);
        } catch (IOException | NoSuchAlgorithmException | 
                InvalidKeySpecException | NoSuchPaddingException | 
                InvalidKeyException | IllegalBlockSizeException | 
                BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, "Exception excrypting the password", ex);
        }
        LOGGER.info("Ending cypherPass");
        return password;
    }
    
    /**
     * 
     */
    private void makeUserInShow() {
        LOGGER.info("making the user.");
        UserBean us = new UserBean();
        us.setEmail("jonasecas97@gmail.com");
        us.setLogin("jon");
        us.setPassword(cypherPass("1234"));
        us.setFullName("Jon Gonzalez");
        us.setPrivilege(Privilege.TOWNHALLUSER);
        us.setStatus(Status.ENABLED);
        try {
            if(iuser.findUserbyLogin(us)!=null){
                throw new ReadException();
            }else{
                iuser.createUser(us);
            }
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "An error have ocurred with the creation of the user.", ex);
        } catch ( ReadException ex){
                getAlert("The user exist.");
        }
        LOGGER.info("finishing making the user");
    }
}
