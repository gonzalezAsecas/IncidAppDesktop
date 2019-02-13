/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factories.FTPFactory;
import interfaces.iFTP;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.FTPFileTV;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
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
    
    protected static final Logger LOGGER = Logger.getLogger("incidappdesktop");
    
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
        login();
        verifyThat("#btnSearch", isEnabled());
        verifyThat("#btnMakeDirectory", isDisabled());
        verifyThat("#btnLoad", isDisabled());
        verifyThat("#btnDelete", isDisabled());
        verifyThat("#btnDownload", isDisabled());
        verifyThat("#txtFNameDirectory", isEnabled());
        verifyThat("#txtFSearch", isDisabled());
        verifyThat("#txtFServer", isDisabled());
        verifyThat("#txtFDirectory", isDisabled());
    }
    
    /**
     * 
     */
    @Test
    public void test2_handleTextChanged(){
        login();
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
        verifyThat("Searching file for load in the FTP server", isVisible());
    }

    /**
     * Test of loadRoot and loadFiles method, of class GUI005Controller.
     */
    @Test
    public void test4_LoadRootAndLoadFiles() {
        try {
            login();
            iFTP ftp = FTPFactory.getiFTP();
            TreeView<FTPFileTV> tree = lookup("tree").query();
            TreeItem<FTPFileTV> item = tree.getTreeItem(0).getChildren().get(0);
            FTPFileTV[] files = ftp.showFiles("/");
            assertEquals(item, files[0]);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
    }

    /**
     * Test of handleLoad method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void test5_HandleLoad() {
        GUI005Controller controller = new GUI005Controller();
        controller.setFile(new File("hello.txt"));
        controller.setDirPath("/");
        controller.handleLoad(new ActionEvent());
    }

    /**
     * Test of handleDownload method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void test6_HandleDownload() {
        clickOn("#txtFUser");
        write("jonth");
        clickOn("#pwPassword");
        write("1234");
        clickOn("mFiles");
        TreeView<FTPFileTV> tree = lookup("tree").query();
        TreeItem<FTPFileTV> item;
        List<TreeItem<FTPFileTV>> items;
        tree.getSelectionModel().select(0);
        item = (TreeItem) tree.getSelectionModel().getSelectedItem();
        items = item.getChildren();
        for(TreeItem<FTPFileTV> it : items){
            if(!it.getValue().isDirectory()){
                tree.getSelectionModel().select(it);
                break;
            }
        }
        verifyThat("#btnDownload", isEnabled());
        clickOn("#btnDownload");
        //doubleClickOn("ADT libro");
        //clickOn("adt tema 0.pdf");
        //clickOn("#btnDownload");
        //verifyThat("File downloaded.", isVisible());
        //push(KeyCode.ENTER);
    }

    /**
     * Test of handleMakeDir method, of class GUI005Controller.
     */
    @Test
    public void test7_HandleMakeDir() {
        try {
            Boolean is = false;
            login();
            clickOn("#txtFNameDirectory");
            write("testDir");
            verifyThat("#btnMakeDirectory", isEnabled());
            clickOn("#btnMakeDirectory");
            sleep(200);
            TreeView<FTPFileTV> tree = lookup("tree").query();
            List<TreeItem<FTPFileTV>> items = tree.getTreeItem(0).getChildren();
            FTPFileTV file = new FTPFileTV();
            for(TreeItem<FTPFileTV> item: items){
                if(item.getValue().getName().equals("testDir")){
                    file = item.getValue();
                    assertEquals(item.getValue().getName(), "testDir");
                    is=true;
                    break;
                }
            }
            if(!is){
                assertEquals(tree.getTreeItem(0).getValue(),file);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "GUI005ControllerIT: An error have ocurred testing the making directory", ex);
        }
    }

    /**
     * Test of handleDelete method, of class GUI005Controller.
     */
    @Test
    public void test8_HandleDelete() {
        try {
            Boolean is = false;
            login();
            TreeView<FTPFileTV> tree = lookup("tree").query();
            List<TreeItem<FTPFileTV>> items = tree.getTreeItem(0).getChildren();
            for(TreeItem<FTPFileTV> item: items){
                if(!item.getValue().isDirectory()){
                    tree.getSelectionModel().select(tree.getRoot().getChildren().get(0));
                    clickOn("#btnDelete");
                    verifyThat("You are going to delete this file, it´s ok?", isVisible());
                    push(KeyCode.ENTER);
                    is = true ;
                    break;
                }
            }
            if(!is){
                verifyThat("#btnDelete", isDisabled());
            }else{
                for(TreeItem<FTPFileTV> item: items){
                    if(item.getValue().getName().equals("testDir")){
                        assertEquals(item.getValue().getName(), "testDir");
                        is=true;
                        break;
                    }
                }
                if(is){
                    verifyThat("#btnDelete", isDisabled());
                }
            }
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "GUI005ControllerIT: An error have ocurred testing the making directory", ex);
        }
    }

    /**
     * Test of handleIncidentsFTP method, of class GUI005Controller.
     */
    @Test
    public void test9_HandleIncidentsFTP() {
        login();
        clickOn("#mIncidents");
        clickOn("#mIIncidentList");
        verifyThat("#btnModifyIncident", isDisabled());
    }

    /**
     * Test of handleInfoFTP method, of class GUI005Controller.
     */
    @Test
    public void test91_HandleInfoFTP() {
        login();
        clickOn("#mUserInfo");
        clickOn("#mISetting");
        verifyThat("#btnUpdate", isEnabled());
    }

    /**
     * Test of handleLogOutFTP method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void test92_HandleLogOutFTP() {
        
    }

    /**
     * Test of handleClickFile method, of class GUI005Controller.
     */
    @Ignore
    @Test
    public void test93_HandleClickFile() {
        login();
    }
    
    public void login(){
        clickOn("#txtFUser");
        write("jonth");
        clickOn("#pwPassword");
        write("1234");
        clickOn("#btnLogIn");
        clickOn("#mFiles");
        clickOn("#mFTP");
    }
}
