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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author Lander Lluvia
 */
public class GUI006_2Controller {
    
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
    @FXML
    private TextField txtFConfirmPass;
    @FXML
    private Button btnConfirm;
    
    protected iUser userImpl = LogicFactory.getiUser();
    
    protected Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    protected UserBean user;
    public void setUser(UserBean user){
        this.user = user;
    }
    
    protected String newPass;
    public void setNewPass(String newPass){
        this.newPass = newPass;
    }
    
    public void initStage(Parent root){
        LOGGER.info("Initializing GUI006_2 stage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::OnShowingHandler);
        btnConfirm.setOnAction((event) -> handleConfirm(event));
    }
    
    public void OnShowingHandler(WindowEvent event){
        LOGGER.info("Begginning OnShowingHandler()");
        btnConfirm.setMnemonicParsing(true);
        btnConfirm.setText("_Confirm");
        LOGGER.info("Ending OnShowingHandler()");
    }
    
    public void handleConfirm(ActionEvent event){
        LOGGER.info("Begginning handleConfirm()");
        if(txtFConfirmPass.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You must fill the field!", ButtonType.OK);
            alert.showAndWait();
        }else{
            byte[] passCypher = cypherPass(txtFConfirmPass.getText().trim());
            try{
                userImpl.findUserToConfirmPassword(user, passCypher);
                if(newPass.equals(txtFConfirmPass.getText().trim())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "The old password and the new password can't be the same", ButtonType.OK);
                    alert.showAndWait();
                }else{
                    user.setPassword(cypherPass(txtFConfirmPass.getText().trim()));
                    userImpl.editUser(user);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "A confirmation email has been sent to you", ButtonType.OK);
                    alert.showAndWait();
                    stage.close();
                }
            }catch(ClientErrorException ex){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "The password is wrong", ButtonType.OK);
                alert.showAndWait();
            } catch (UpdateException ex) {
                Logger.getLogger(GUI006_2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private byte[] cypherPass(String password) {
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
            LOGGER.info("Ending cypherPass");
            return cipher.doFinal(password.getBytes());
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Exception reading the file", ex);
        } catch (IOException | NoSuchAlgorithmException | 
                InvalidKeySpecException | NoSuchPaddingException | 
                InvalidKeyException | IllegalBlockSizeException | 
                BadPaddingException ex) {
            LOGGER.log(Level.SEVERE, "Exception excrypting the password", ex);
        }
        return null;
    }
}
