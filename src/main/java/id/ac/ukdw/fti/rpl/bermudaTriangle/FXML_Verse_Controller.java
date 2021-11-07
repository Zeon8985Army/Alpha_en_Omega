package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Verse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXML_Verse_Controller implements Initializable {

    private ObservableList<Verse> verses = FXCollections.observableArrayList();

    @FXML
    private TableView<Verse> tabelVerse;

    @FXML
    private TableColumn<Verse, Integer> verseIdColumn;

    @FXML
    private TableColumn<Verse, String> verseColumn;

    @FXML
    private TableColumn<Verse, String> verseTextColumn;

    @FXML
    private TableColumn<Verse, String> PeopleColumn;

    @FXML
    private TableColumn<Verse, String> PlacesColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        verses = Database.instance.getAllVerse();
        verseIdColumn.setCellValueFactory(new PropertyValueFactory<Verse, Integer>("verseId"));
        verseColumn.setCellValueFactory(new PropertyValueFactory<Verse, String>("verse"));
        verseTextColumn.setCellValueFactory(new PropertyValueFactory<Verse, String>("verseText"));
        PeopleColumn.setCellValueFactory(new PropertyValueFactory<Verse, String>("people"));
        PlacesColumn.setCellValueFactory(new PropertyValueFactory<Verse, String>("places"));

        tabelVerse.setItems(verses);
    }

    public void btnToPeople(ActionEvent event) throws IOException {
        Parent tablePeopleParent = FXMLLoader.load(getClass().getResource("tablePeople.fxml"));
        Scene peoplePage = new Scene(tablePeopleParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(peoplePage);
        app_stage.show();
    }

    @FXML
    public void goToApp(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene peoplePage = new Scene(searchWindow);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(peoplePage);
        app_stage.show();
    }

}
