/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import exceptions.UpdateException;
import factories.LogicFactory;
import interfaces.iUser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Lander Lluvia
 */
public class GUI006Controller {

    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    @FXML
    private Menu mIncident;
    @FXML
    private Menu mTownhall;
    @FXML
    private Menu mFile;
    @FXML
    private Menu mUserInformation;
    @FXML
    private Menu mLogOut;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfTownhall;
    @FXML
    private Button btnUpdate;
    
    protected iUser userImpl = LogicFactory.getiUser();
    
    protected Stage stage;
    /**
     * 
     * @param stage 
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    protected UserBean user;
    /**
     * 
     * @param user 
     */
    void setUser(UserBean user) {
        this.user = user;
    }
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI006 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User information");
        stage.setResizable(true);
        stage.setOnShowing(this::OnShowingHandler);
        tfFullName.setText(user.getFullName());
        tfUsername.setText(user.getLogin());
        pfPassword.setTooltip(new Tooltip("Use this field if you want to change your current password"));
        tfEmail.setText(user.getEmail());
        tfStreet.setText(user.getStreet());
        tfTownhall.setText(user.getTownHall().getLocality());
        btnUpdate.setOnAction((event) -> handleUpdate(event));
    }
    
    /**
     * 
     * @param event 
     */
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Beginning OnShowingHandler()");
        mUserInformation.setDisable(true);
        btnUpdate.setMnemonicParsing(true);
        btnUpdate.setText("_Update");
    }
    
    /**
     * 
     * @param event 
     */
    public void handleUpdate(ActionEvent event){
        LOGGER.info("Begginning handleUpdate()");
        try{
            if(fieldsAreFilled()){
                if(pfPassword.getText().length() !=0){
                    if(handlePassword()){
                        user.setFullName(tfFullName.getText());
                        user.setLogin(tfUsername.getText());
                        user.setPassword(cypherPass(pfPassword.getText()));
                        user.setEmail(tfEmail.getText());
                        user.setStreet(tfStreet.getText());
                        //user.setTownHall(tfTownhall.getText());
                        user.setLastPasswordChange(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                    }
                }else{
                    user.setFullName(tfFullName.getText());
                    user.setLogin(tfUsername.getText());
                    user.setEmail(tfEmail.getText());
                    user.setStreet(tfStreet.getText());
                    //user.setTownHall(tfTownhall.getText());
                    user.setLastPasswordChange(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                }
                userImpl.editUser(user);
            }
        }catch (UpdateException ex) {
            Logger.getLogger(GUI006Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    /**
     * 
     * @return 
     */
    public boolean fieldsAreFilled(){
        boolean filled = true;
        if(tfFullName.getText().isEmpty() | tfUsername.getText().isEmpty() | tfEmail.getText().isEmpty() | tfStreet.getText().isEmpty() | tfTownhall.getText().isEmpty()){
            filled = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "At least all the fields except password must have information", ButtonType.OK);
            alert.showAndWait();
        }
        return filled;
    }
    
    /**
     * 
     * @return 
     */
    public boolean handlePassword(){
        boolean pass = true;
        if(pfPassword.getText().length() <4){
            pass = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The password must have at least 4 characters.", ButtonType.OK);
            alert.showAndWait();
        }
        return pass;
    }
    
    /**
     * 
     * @param password
     * @return 
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
    
}
