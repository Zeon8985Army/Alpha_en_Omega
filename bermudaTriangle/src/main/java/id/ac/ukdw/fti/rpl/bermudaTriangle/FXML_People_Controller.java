package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
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

public class FXML_People_Controller implements Initializable{
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    @FXML
    private TableView<People> tabelPeople;

    @FXML
    private TableColumn<People, String> personLookup;

    @FXML
    private TableColumn<People, String> displayTitle;

    @FXML
    private TableColumn<People, String> gender;

    @FXML
    private TableColumn<People, String> birthPlace;

    @FXML
    private TableColumn<People, String> deathYear;

    @FXML
    private TableColumn<People, String> dictText;

    @FXML
    private TableColumn<People, String> verses;

    @FXML
    private TableColumn<People, String> birthYear;

    @FXML
    private Button btnToVerse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        peoples = Database.instance.getAllPeople();
        personLookup.setCellValueFactory(new PropertyValueFactory<People, String>("personLookup"));
        displayTitle.setCellValueFactory(new PropertyValueFactory<People, String>("displayTitle"));
        gender.setCellValueFactory(new PropertyValueFactory<People, String>("gender"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<People, String>("birthPlace"));
        deathYear.setCellValueFactory(new PropertyValueFactory<People, String>("deathYear"));
        dictText.setCellValueFactory(new PropertyValueFactory<People, String>("dictText"));
        verses.setCellValueFactory(new PropertyValueFactory<People, String>("verses"));

        tabelPeople.setItems(peoples);
    }
    @FXML
    public void btnToPlaces(ActionEvent event) throws IOException{
        Parent tablePeopleParent = FXMLLoader.load(getClass().getResource("tablePlaces.fxml"));
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
