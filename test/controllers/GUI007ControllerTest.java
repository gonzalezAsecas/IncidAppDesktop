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
public class GUI007ControllerTest extends ApplicationTest {
    
    private static final String OVERSIZED_TEXT="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+
                                               "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override 
    public void start(Stage stage) throws Exception{
        new Application().start(stage);
    }
    
    /**
     * 
     */
    @Test
    public void test1_InitialState(){
        System.out.println("Test1");
        verifyThat("#btnNewTownhall", isEnabled());
        verifyThat("#btnModifyTownhall", isDisabled());
        verifyThat("#btnDelete", isDisabled());
        verifyThat("#btnReport", isEnabled());
        verifyThat("#tableView", isVisible());
        verifyThat("#tcName", isVisible());
        verifyThat("#tcEmail", isVisible());
        verifyThat("#tcTelephone", isVisible());
        System.out.println("End Test1");
    }
    
    /**
     * 
     */
    @Test
    public void test2_CheckAddAndCancel(){
        System.out.println("Test2");
        clickOn("#btnNewTownhall");
        verifyThat("#txtFName", hasText(""));
        verifyThat("#txtFEmail", hasText(""));
        verifyThat("#txtFPhone", hasText(""));
        verifyThat("#btnAccept", isEnabled());
        verifyThat("#btnCancel", isEnabled());
        clickOn("#btnCancel");
        System.out.println("End Test2");
    }
    
    /**
     * 
     */
    @Test
    public void test3_CheckFieldsMaxLength(){
        System.out.println("Test3");
        clickOn("#btnNewTownhall");
        clickOn("#txtFName");
        write(OVERSIZED_TEXT);
        verifyThat("Name too long", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFEmail");
        write(OVERSIZED_TEXT);
        verifyThat("Email too long", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFPhone");
        write("1010101010101");
        verifyThat("Phone too long", isVisible());
        clickOn("Aceptar");
        clickOn("#btnCancel");
        System.out.println("Ending Test3");
    }
    
    /**
     * 
     */
    @Test
    public void test4_CheckFieldsMustBeFilled(){
        System.out.println("Test4");
        clickOn("#btnNewTownhall");
        
        clickOn("#txtFName");
        write("mustbefilled");
        clickOn("#btnAccept");
        verifyThat("All the fields must have information", isVisible());
        clickOn("Aceptar");
        doubleClickOn("#txtFName");
        eraseText(1);
        
        clickOn("#txtFEmail");
        write("mustbefilled");
        clickOn("#btnAccept");
        verifyThat("All the fields must have information", isVisible());
        clickOn("Aceptar");
        doubleClickOn("#txtFEmail");
        eraseText(1);
        
        clickOn("#txtFPhone");
        write("101010101");
        clickOn("#btnAccept");
        verifyThat("All the fields must have information", isVisible());
        clickOn("Aceptar");
        
        clickOn("#txtFName");
        write("must be filled");
        clickOn("#txtFEmail");
        write("@.");
        clickOn("#btnAccept");
        verifyThat("The email must have the format: email@email.example", isVisible());
        clickOn("Aceptar");
        
        clickOn("#btnCancel");
        System.out.println("Ending Test4");
    }
    
    /**
     * 
     */
    @Test
    public void test5_AddTownhall(){
        System.out.println("Test5");
        TableView table = lookup("#tableView").queryTableView();
        int rowCount = table.getItems().size();
        clickOn("#btnNewTownhall");
        clickOn("#txtFName");
        write("testTownHall");
        clickOn("#txtFEmail");
        write("email@email.com");
        clickOn("#txtFPhone");
        write("101010101"); 
        clickOn("#btnAccept");
        assertEquals("The townhall has not been added",rowCount+1,table.getItems().size());
        System.out.println("Ending Test5");
    }
    
    /**
     * 
     */
    @Test
    public void test6_CheckTable(){
        System.out.println("Test6");
        TableView table = lookup("#tableView").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        verifyThat("#btnModifyTownhall", isEnabled());
        verifyThat("#btnDelete", isEnabled());
        System.out.println("Ending Test6");
    }
    
    /**
     * 
     */
    @Test
    public void test7_ModifyUser(){
        System.out.println("Test7");
        TableView table = lookup("#tableView").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#btnModifyTownhall");
        
        doubleClickOn("#txtFName");
        write("testTownHall");
        doubleClickOn("#txtFEmail");
        eraseText(1);
        doubleClickOn("#txtFEmail");
        eraseText(1);
        doubleClickOn("#txtFEmail");
        eraseText(1);
        doubleClickOn("#txtFEmail");
        eraseText(1);
        doubleClickOn("#txtFEmail");
        eraseText(1);
        write("email@email.com");
        doubleClickOn("#txtFPhone");
        write("666666"); 
        clickOn("#btnAccept");
        System.out.println("Ending Test7");
    }
    
    /**
     * 
     */
    @Test
    public void test8_CancelDeleteUser(){
        System.out.println("Test8");
        TableView table = lookup("#tableView").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                    table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#btnDelete");
        verifyThat("Delete the selected row?", isVisible());
        clickOn("Cancelar");
        System.out.println("Ending Test8");
    }
    
    /**
     * 
     */
    @Test
    public void test9_DeleteUser(){
        System.out.println("Test9");
        TableView table = lookup("#tableView").queryTableView();
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                    table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#btnDelete");
        verifyThat("Delete the selected row?", isVisible());
        clickOn("Aceptar");
        assertEquals("The user has not been deleted",rowCount-1,table.getItems().size());
        System.out.println("Ending Test9");
    }
}
