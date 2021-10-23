package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.net.URL;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Verse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {

    private ObservableList<Verse> verses = FXCollections.observableArrayList();

    @FXML
    private TableView<Verse> tabelVerse;

    @FXML
    private TableColumn<Verse, Integer> verseIdColumn;

    @FXML
    private TableColumn<Verse, String> verseColumn;

    @FXML
    private TableColumn<Verse, String> verseTextColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        verses = Database.instance.getAllVerse();
        verseIdColumn.setCellValueFactory(new PropertyValueFactory<Verse, Integer>("verseId"));
        verseColumn.setCellValueFactory(new PropertyValueFactory<Verse, String>("verse"));
        verseTextColumn.setCellValueFactory(new PropertyValueFactory<Verse,String>("verseText"));
        tabelVerse.setItems(verses);
    }
}
