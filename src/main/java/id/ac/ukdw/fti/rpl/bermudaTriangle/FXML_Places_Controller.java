package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Places;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXML_Places_Controller implements Initializable {
    private ObservableList<Places> places = FXCollections.observableArrayList();

    @FXML
    private TableView<Places> tabelPlace;

    @FXML
    private TableColumn<Places, String> placeLookup;

    @FXML
    private TableColumn<Places, String> displayTitle;

    @FXML
    private TableColumn<Places, String> verses;

    @FXML
    private TableColumn<Places, String> dictText;

    @FXML
    private TableColumn<Places, String> peopleBorn;

    @FXML
    private TableColumn<Places, String> peopleDied;

    @FXML
    private TableColumn<Places, String> hasBeenHere;

    @FXML
    private TableColumn<Places, String> latitude;

    @FXML
    private TableColumn<Places, String> longtitude;

    @FXML
    private Button btnToPeople;

    @FXML
    private Button btnToVerse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        places = Database.instance.getAllPlace();
        placeLookup.setCellValueFactory(new PropertyValueFactory<Places, String>("placeLookup"));
        displayTitle.setCellValueFactory(new PropertyValueFactory<Places, String>("displayTitle"));
        verses.setCellValueFactory(new PropertyValueFactory<Places, String>("verses"));
        dictText.setCellValueFactory(new PropertyValueFactory<Places, String>("dictText"));
        peopleBorn.setCellValueFactory(new PropertyValueFactory<Places, String>("peopleBorn"));
        peopleDied.setCellValueFactory(new PropertyValueFactory<Places, String>("peopleDied"));
        hasBeenHere.setCellValueFactory(new PropertyValueFactory<Places, String>("hasBeenHere"));
        latitude.setCellValueFactory(new PropertyValueFactory<Places, String>("latitude"));
        longtitude.setCellValueFactory(new PropertyValueFactory<Places, String>("longtitude"));

        tabelPlace.setItems(places);
    }

    @FXML
    public void btnToPeople(ActionEvent event) throws IOException {
        Parent tablePeopleParent = FXMLLoader.load(getClass().getResource("tablePeople.fxml"));
        Scene peoplePage = new Scene(tablePeopleParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(peoplePage);
        app_stage.show();
    }

    @FXML
    public void btnToVerse(ActionEvent event) throws IOException {
        Parent tablePeopleParent = FXMLLoader.load(getClass().getResource("tableverse.fxml"));
        Scene peoplePage = new Scene(tablePeopleParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(peoplePage);
        app_stage.show();
    }
}
