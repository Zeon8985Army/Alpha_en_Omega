package id.ac.ukdw.fti.rpl.bermudaTriangle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
// import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FXML_Places_Controller {
    @FXML
    private TableView<?> tabelPeople;

    @FXML
    private TableColumn<?, ?> placeLookup;

    @FXML
    private TableColumn<?, ?> displayTitle;

    @FXML
    private TableColumn<?, ?> verses;

    @FXML
    private TableColumn<?, ?> dictText;

    @FXML
    private TableColumn<?, ?> peopleBorn;

    @FXML
    private TableColumn<?, ?> peopleDied;

    @FXML
    private TableColumn<?, ?> hasBeenHere;

    @FXML
    private TableColumn<?, ?> latitude;

    @FXML
    private TableColumn<?, ?> longtitude;

    @FXML
    private Button btnToPeople;

    @FXML
    private Button btnToVerse;

    public void initialize(URL location, ResourceBundle resources) {
    
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
    public void btnToVerse(ActionEvent event) throws IOException{
        Parent tablePeopleParent = FXMLLoader.load(getClass().getResource("tableverse.fxml"));
        Scene peoplePage = new Scene(tablePeopleParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(peoplePage);
        app_stage.show();
    }
}
