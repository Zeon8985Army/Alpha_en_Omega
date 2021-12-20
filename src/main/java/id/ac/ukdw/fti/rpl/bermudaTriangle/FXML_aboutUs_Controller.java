package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

// untuk fullscrenn
import java.awt.*;

public class FXML_aboutUs_Controller implements Initializable{
    
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem dashboard;
    @FXML
    private MenuItem BtnGoToSearch;
    @FXML
    private MenuItem Verse;
    @FXML
    private MenuItem BtnAboutUs;

    @FXML
    private Text objectName;

    @FXML
    private Slider slidder;
    
    @FXML
    private TextArea textAboutUs;

    // untuk fullscrenn
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    @FXML
    void goToAboutUs(ActionEvent event) throws IOException{
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @FXML
    void goToVerse(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getPrimary().getBounds();

        Parent searchWindow = FXMLLoader.load(getClass().getResource("detailObject.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.setX(bounds.getMinX());
        app_stage.setY(bounds.getMinY());
        app_stage.setWidth(size.getWidth());
        app_stage.setHeight(size.getHeight());
        app_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void checkSlider(MouseEvent event) {
        textAboutUs.setStyle("-fx-font-size: " + slidder.getValue());
    }
}   
