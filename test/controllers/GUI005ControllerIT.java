/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Jon Gonzalez
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GUI005ControllerIT extends ApplicationTest{
    
    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
    }

    /**
     * Test of initStage method, of class GUI005Controller.
     */
    @Test
    public void test1_InitStage() {
        verifyThat("#btnSearch", isEnabled());
        verifyThat("#btnMakeDirectory", isDisabled());
        verifyThat("#btnLoad", isDisabled());
        verifyThat("#btnDelete", isDisabled());
        verifyThat("#btnDownload", isDisabled());
        verifyThat("#txtFNameDirectory", isEnabled());
        verifyThat("#txtFSearch", isDisabled());
    }
    
    /**
     * 
     */
    @Test
    public void test2_handleTextChanged(){
        clickOn("#txtFNameDirectory");
        write("directory1");
        verifyThat("#btnMakeDirectory", isEnabled());
        eraseText(11);
        verifyThat("#btnMakeDirectory", isDisabled());
    }
    
    /**
     * Test of handleSearch method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void test3_HandleSearch() {
        clickOn("#btnSearch");
        verifyThat("Searching file for load in the FTP server", isVisible());//no vale
    }

    /**
     * Test of loadRoot and loadFiles method, of class GUI005Controller.
     */
    @Test
    public void test4_LoadRootAndLoadFiles() {
        verifyThat("ADT libro", isVisible());
        doubleClickOn("ADT libro");
        verifyThat("DFSFB", isVisible());
    }

    /**
     * Test of handleLoad method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleLoad() {
        clickOn("#btnSearch");
        doubleClickOn("Escritorio");
        clickOn("MongoDBEjercicio");
        clickOn("Abrir");
        clickOn("ADT libro");
        clickOn("#btnLoad");
        doubleClickOn("ADT libro");
        verifyThat("MongoDBEjercicio", isVisible()  );
    }

    /**
     * Test of handleDownload method, of class GUI005Controller.
     */
    @Test
    public void testHandleDownload() {
        doubleClickOn("ADT libro");
        clickOn("adt tema 0.pdf");
        clickOn("#btnDownload");
        verifyThat("File downloaded.", isVisible());
        push(KeyCode.ENTER);
    }

    /**
     * Test of handleMakeDir method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleMakeDir() {
        
    }

    /**
     * Test of handleDelete method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleDelete() {
        
    }

    /**
     * Test of handleIncidentsFTP method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleIncidentsFTP() {
        
    }

    /**
     * Test of handleInfoFTP method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleInfoFTP() {
        
    }

    /**
     * Test of handleLogOutFTP method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleLogOutFTP() {
        
    }

    /**
     * Test of handleClickFile method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void testHandleClickFile() {
        
    }
    
}
