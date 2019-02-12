/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.util.List;
import javabeans.FTPFileTV;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
        login();
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
        login();
        TreeView<FTPFileTV> tree = lookup("tree").query();
        TreeItem<FTPFileTV> item = tree.getTreeItem(0);
        //VerifyThat(item, isNotNull);
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
        login();
    }

    /**
     * Test of handleDelete method, of class GUI005Controller.
     */
    @Test
    public void test8_HandleDelete() {
        login();
    }

    /**
     * Test of handleIncidentsFTP method, of class GUI005Controller.
     */
    @Test
    public void test9_HandleIncidentsFTP() {
        login();
    }

    /**
     * Test of handleInfoFTP method, of class GUI005Controller.
     */
    @Test
    public void test91_HandleInfoFTP() {
        login();
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
