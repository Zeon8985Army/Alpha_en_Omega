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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_DetailP_Controller implements Initializable {
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();

    // untuk ke detail
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane detailAnchor;

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu searchMB;
    @FXML
    private MenuItem BtnGoToSearch;
    @FXML
    private MenuItem BtnGoToVerse;

    @FXML
    private ImageView imgPeople;

    @FXML
    private ListView<String> locationPeople;

    @FXML
    private ListView<String> versePeople;

    @FXML
    private TextArea descriptionPeople;

    @FXML
    private TextField namePeople;

    @FXML
    private TextField genderPeople;

    @FXML
    private TextField birthPeople;

    @FXML
    private Button btnDetailVerse;
    @FXML
    private Text backLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

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

    public void showName(String name) {
        descriptionPeople.setWrapText(true);
        namePeople.setText(name);

        peoples = Database.instance.getAllPeople();
        places = Database.instance.getAllPlace();

        for (People people : peoples) {
            if (name == people.getDisplayTitle()) {
                if (people.getGender() == null) {
                    genderPeople.setText("No Data");
                } else {
                    genderPeople.setText(people.getGender());
                }

                if (people.getBirthYear() == null) {
                    birthPeople.setText("No Data");
                } else {
                    birthPeople.setText(people.getBirthYear());
                }

                boolean tidakAdaPlace = true;
                for (Places place : places) {
                    String[] listPeopleHassBenHere = { "" };
                    String[] listPeopleBornHere = { "" };
                    String[] listPeopleDiedHere = { "" };

                    if (place.getHasBeenHere() != null) {
                        if (place.getHasBeenHere().contains(",")) {
                            listPeopleHassBenHere = place.getHasBeenHere().split(",");
                        } else {
                            listPeopleHassBenHere = place.getHasBeenHere().split(" ");
                        }
                    }

                    if (place.getPeopleBorn() != null) {
                        if (place.getPeopleBorn().contains(",")) {
                            listPeopleBornHere = place.getPeopleBorn().split(",");
                        } else {
                            listPeopleBornHere = place.getPeopleBorn().split(" ");
                        }
                    }

                    if (place.getPeopleDied() != null) {
                        if (place.getPeopleDied().contains(",")) {
                            listPeopleDiedHere = place.getPeopleDied().split(",");
                        } else {
                            listPeopleDiedHere = place.getPeopleDied().split(" ");
                        }
                    }

                    ArrayList<String> listPeopleHere = new ArrayList<>(Arrays.asList(listPeopleHassBenHere));
                    listPeopleHere.addAll(Arrays.asList(listPeopleBornHere));
                    listPeopleHere.addAll(Arrays.asList(listPeopleDiedHere));

                    if (listPeopleHere.contains(people.getPersonLookup())) {
                        locationPeople.getItems().add(place.getDisplayTitle());
                        tidakAdaPlace = false;
                        break;
                    }
                }

                if (tidakAdaPlace) {
                    locationPeople.getItems().add("No Data");
                }

                if (people.getVerses() != null) {
                    if (people.getVerses().contains(",")) {
                        String[] listVerse = people.getVerses().split(",");
                        for (String verse : listVerse) {
                            versePeople.getItems().add(verse);
                        }
                    } else {
                        versePeople.getItems().add(people.getVerses());
                    }
                }

                if (people.getDictText()==null) {
                    descriptionPeople.setText("No Data");
                }else{
                    descriptionPeople.setText(people.getDictText());
                }
            } else {
                continue;
            }
        }
    }
    @FXML
    void detailVerse(ActionEvent event) throws IOException {
        String selectedVerse = versePeople.getSelectionModel().getSelectedItem();

        if (selectedVerse!=null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("verseWindow.fxml"));
            root = loader.load();

            FXML_VerseWindow_Controller verseWindow = loader.getController();

            verseWindow.showDetailVerse(selectedVerse);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            backLog.setText("Please Select verse before go to detail verse...");
        }
        
    }
}
