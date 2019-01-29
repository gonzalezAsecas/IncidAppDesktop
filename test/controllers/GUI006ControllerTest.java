/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;


/**
 *
 * @author Lander Lluvia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GUI006ControllerTest extends ApplicationTest{
    //ToDo: checkbox todo lo que incluya su control
    
    private static final String OVERSIZED_TEXT="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  
    @Override
    public void start(Stage stage) throws Exception{
        new Application().start(stage);
    }
    
    @Test
    public void test1_InitialState(){
        System.out.println("Test1");
        verifyThat("#pfPassword", hasText(""));
        verifyThat("#btnUpdate", isEnabled());
        System.out.println("Ending Test1");
    }
    
    @Test
    public void test2_CheckFieldsMaxLength(){
        System.out.println("Test2");
        doubleClickOn("#tfFullName");
        eraseText(1);
        write(OVERSIZED_TEXT);
        verifyThat("Full name too long", isVisible());
        clickOn("Aceptar");
        
        doubleClickOn("#tfUsername");
        eraseText(1);
        write(OVERSIZED_TEXT);
        verifyThat("Username too long", isVisible());
        clickOn("Aceptar");
        
        clickOn("#pfPassword");
        write(OVERSIZED_TEXT);
        verifyThat("Password too long", isVisible());
        clickOn("Aceptar");
        
        doubleClickOn("#tfEmail");
        eraseText(1);
        doubleClickOn("#tfEmail");
        eraseText(1);
        write(OVERSIZED_TEXT);
        verifyThat("Email too long", isVisible());
        clickOn("Aceptar");
        
        doubleClickOn("#tfStreet");
        eraseText(1);
        write(OVERSIZED_TEXT);
        verifyThat("Street too long", isVisible());
        clickOn("Aceptar");
        
        doubleClickOn("#tfFullName");
        eraseText(1);
        
        doubleClickOn("#tfUsername");
        eraseText(1);
        
        clickOn("#pfPassword");
        eraseText(1);
        
        doubleClickOn("#tfEmail");
        eraseText(1);
        
        doubleClickOn("#tfStreet");
        eraseText(1);
        System.out.println("Ending Test2");
    }
    
    @Test
    public void test3_CheckButtonUpdate(){
        clickOn("#tfFullName");
        write("Fullname");
        clickOn("#tfUsername");
        write("Username");
        clickOn("#pfPassword");
        write("Password");
        clickOn("#tfEmail");
        write("email@email.com");
        clickOn("#tfStreet");
        write("Street");
        //Elegir de la combo box
        
        clickOn("#btnUpdate");
        verifyThat("#txtFConfirmPass", hasText(""));
        verifyThat("#btnConfirm", isEnabled());
        
        //Click en X de la ventana
    }
    
    @Test
    public void test4_CheckUpdateMaxLength(){
        clickOn("#btnUpdate");
        clickOn("#txtFConfirmPass");
        write(OVERSIZED_TEXT);
        //ToDo en el controller
        verifyThat("Password too long", isVisible());
        clickOn("Aceptar");
        
        //Click en X de la ventana
    }
    
    @Test
    public void test5_CheckUpdateEmpty(){
        clickOn("#btnUpdate");
        clickOn("#btnConfirm");
        verifyThat("You must fill the field!", isVisible());
        clickOn("Aceptar");
        
        //Click en X de la ventana
    }
    
    @Test
    public void test6_CheckWrongPass(){
        clickOn("#btnUpdate");
        clickOn("#txtFConfirmPass");
        write("wrongpass");
        clickOn("#btnConfirm");
        verifyThat("The password is wrong", isVisible());
        clickOn("Aceptar");
        
        //Click en X de la ventana
    }
    
    @Test
    public void test6_CheckSamePass(){
        clickOn("#btnUpdate");
        clickOn("#txtFConfirmPass");
        write("samepass");
        clickOn("#btnConfirm");
        verifyThat("The old password and the new password can't be the same", isVisible());
        clickOn("Aceptar");
        
        //Click en X de la ventana
    }
    
    @Test
    public void test7_CheckNewPass(){
        clickOn("#btnUpdate");
        clickOn("#txtFConfirmPass");
        write("newpass");
        clickOn("#btnConfirm");
        verifyThat("A confirmation email has been sent to you", isVisible());
        clickOn("Aceptar");
    }
}
