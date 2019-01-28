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
        
        
        System.out.println("Ending Test2");
    }
}
