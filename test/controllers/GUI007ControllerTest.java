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
public class GUI007ControllerTest extends ApplicationTest {
    //ToDo: focus cuando sale ventana
    
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
        verifyThat("#btnNewTownhall", isEnabled());
        verifyThat("#btnModifyTownhall", isDisabled());
        verifyThat("#btnDelete", isDisabled());
        verifyThat("#btnReport", isEnabled());
        verifyThat("#tableTownhalls", isVisible());
        verifyThat("#tcName", isVisible());
        verifyThat("#tcEmail", isVisible());
        verifyThat("#tcTelephone", isVisible());
    }
    
    /**
     * 
     */
    @Test
    public void test2_CheckAddAndCancel(){
        clickOn("#btnNewTownhall");
        verifyThat("#txtFName", hasText(""));
        verifyThat("#txtFEmail", hasText(""));
        verifyThat("#txtFPhone", hasText(""));
        verifyThat("#btnAccept", isEnabled());
        verifyThat("#btnCancel", isEnabled());
        clickOn("#btnCancel");
    }
    
    /**
     * 
     */
    @Test
    public void test3_CheckFieldsMaxLength(){
        clickOn("#btnNewTownhall");
        clickOn("#txtFName");
        write("OVERSIZED_TEXT");
        verifyThat("Name too long", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFEmail");
        write("OVERSIZED_TEXT");
        verifyThat("Email too long", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFPhone");
        write("1010101010");
        verifyThat("Phone too long", isVisible());
        clickOn("Aceptar");
        clickOn("#btnCancel");
    }
    
    /**
     * 
     */
    @Test
    public void test4_CheckFieldsMustBeFilled(){
        clickOn("#btnNewTownhall");
        
        clickOn("#txtFName");
        write("must be filled");
        clickOn("#btnAccept");
        verifyThat("All the fields must have information", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFName");
        eraseText(15);
        
        clickOn("#txtFEmail");
        write("must be filled");
        clickOn("#btnAccept");
        verifyThat("All the fields must have information", isVisible());
        clickOn("Aceptar");
        clickOn("#txtFEmail");
        eraseText(15);
        
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
    }
    
    /**
     * 
     */
    @Test
    public void test5_AddTownhall(){
        TableView table = lookup("#tableTownhalls").queryTableView();
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                    table.getItems().size(),0);
        clickOn("#btnNewTownhall");
        clickOn("#txtFName");
        write("testTownHall");
        clickOn("#txtFEmail");
        write("email@email.com");
        clickOn("#txtFPhone");
        write("101010101"); 
        clickOn("#btnAccept");
        assertEquals("The townhall has not been added",rowCount+1,table.getItems().size());
    }
    
    /**
     * 
     */
    @Test
    public void test6_CheckTable(){
        TableView table = lookup("#tableTownhalls").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        verifyThat("#btnModifyTownhall", isEnabled());
        verifyThat("#btnDelete", isEnabled());
    }
    
    /**
     * 
     */
    @Test
    public void test7_ModifyUser(){
        TableView table = lookup("#tableTownhalls").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                        table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#btnModifyTownhall");
        
        doubleClickOn("#txtFName");
        write("testTownHall");
        clickOn("#txtFEmail");
        write("email@email.com");
        clickOn("#txtFPhone");
        write("101010101"); 
        clickOn("#btnAccept");
    }
    
    /**
     * 
     */
    @Test
    public void test8_CancelDeleteUser(){
        TableView table = lookup("#tableTownhalls").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                    table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#delete");
        verifyThat("Delete the selected row?", isVisible());
        clickOn("Cancel");;
    }
    
    /**
     * 
     */
    @Test
    public void test9_DeleteUser(){
        TableView table = lookup("#tableTownhalls").queryTableView();
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                    table.getItems().size(),0);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ",row);
        clickOn(row);
        clickOn("#delete");
        verifyThat("Delete the selected row?", isVisible());
        clickOn("OK");
        assertEquals("The user has not been deleted",rowCount-1,table.getItems().size());
    }
}
