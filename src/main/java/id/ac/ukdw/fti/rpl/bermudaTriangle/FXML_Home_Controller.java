package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Places;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// untuk map
import id.ac.ukdw.fti.rpl.bermudaTriangle.GoogleMapView;
import id.ac.ukdw.fti.rpl.bermudaTriangle.MapComponentInitializedListener;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.GoogleMap;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.InfoWindow;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.InfoWindowOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.LatLong;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapOptions;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MapTypeIdEnum;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.Marker;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.MarkerOptions;

public class FXML_Home_Controller implements Initializable, MapComponentInitializedListener {
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem BtnGoToSearch;

    @FXML
    private MenuItem Verse;

    @FXML
    private Slider slidder;

    @FXML
    private Text objectName;

    @FXML
    private TextArea description;

    @FXML
    private GoogleMapView mapID;

    @FXML
    private ListView<String> listVerse;

    @FXML
    private Button detailVerse;
    @FXML
    private Text backLog;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void mapInitialized() {

    }

    // MenuBar Method
    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }

    @FXML
    void goToVerse(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }
    @FXML
    void checkSlider(MouseEvent event) {

    }

    @FXML
    void showVerse(ActionEvent event) throws IOException{
        
    }

    
}
