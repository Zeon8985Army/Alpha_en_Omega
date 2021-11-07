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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_DetailPLC_Controller implements Initializable{
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
    private MenuItem BtnGoToSearchPLC;
    @FXML
    private MenuItem BtnGoToVersePLC;

    @FXML
    private ImageView imgPlace;

    @FXML
    private ListView<String> peopleHere;

    @FXML
    private ListView<String> versesPlace;

    @FXML
    private TextArea descriptionPlace;

    @FXML
    private TextField namePlace;
    @FXML
    private Button btnDetailVerse;
    @FXML
    private Text backLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    // Method untuk menu bar
    @FXML
    void goToSearchPLC(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("searchWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }
    @FXML
    void goToVersePLC(ActionEvent event) throws IOException {
        Parent searchWindow = FXMLLoader.load(getClass().getResource("verseWindow.fxml"));
        Scene searchPage = new Scene(searchWindow);
        Stage app_stage = (Stage) menuBar.getScene().getWindow();
        app_stage.setScene(searchPage);
        app_stage.show();
    }

    public void showDetailPlace(String name) {
        descriptionPlace.setWrapText(true);
        namePlace.setText(name);

        peoples = Database.instance.getAllPeople();
        places = Database.instance.getAllPlace();

        for (Places place : places) {
            if (name == place.getDisplayTitle()) {
                String[] listPeopleBornHere={""};
                String[] listPeopleDiedHere={""};
                String[] listPeopleHasBeenHere={""};

                if (place.getPeopleBorn() != null) {
                    if (place.getPeopleBorn().contains(",")){
                        listPeopleBornHere = place.getPeopleBorn().split(",");
                    }else{
                        listPeopleBornHere = place.getPeopleBorn().split(" ");
                    }
                } 

                if (place.getPeopleDied() != null) {
                    if (place.getPeopleDied().contains(",")){
                        listPeopleDiedHere = place.getPeopleDied().split(",");
                    }else{
                        listPeopleDiedHere = place.getPeopleDied().split(" ");
                    }
                }

                if (place.getHasBeenHere() != null) {
                    if (place.getHasBeenHere().contains(",")){
                        listPeopleHasBeenHere = place.getHasBeenHere().split(",");
                    }else{
                        listPeopleHasBeenHere = place.getHasBeenHere().split(" ");
                    }
                }

                ArrayList<String> listPeopleHereLookup = new ArrayList<>(Arrays.asList(listPeopleBornHere));
                ArrayList<String> listPeopleHereName = new ArrayList<>();
                listPeopleHereLookup.addAll(Arrays.asList(listPeopleDiedHere));
                listPeopleHereLookup.addAll(Arrays.asList(listPeopleHasBeenHere));
                
                listPeopleHereLookup.forEach((peopleLookup)->{
                    for (People people : peoples) {
                        if (peopleLookup.equals(people.getPersonLookup()) ) {
                            listPeopleHereName.add(people.getDisplayTitle());
                            break;
                        }
                    }
                });

                if (listPeopleHereName.size()!=0){
                    peopleHere.getItems().addAll(listPeopleHereName);
                }else{
                    peopleHere.getItems().add("No Data");
                }


                if (place.getVerses() != null) {
                    if (place.getVerses().contains(",")) {
                        String[] listVerse = place.getVerses().split(",");
                        versesPlace.getItems().addAll(listVerse);
                    } else {
                        versesPlace.getItems().add(place.getVerses());
                    }
                }
                
                if (place.getDictText()==null) {
                    descriptionPlace.setText("No Data");
                }else{
                    descriptionPlace.setText(place.getDictText());
                }            } else {
                continue;
            }
        }
    }

    @FXML
    void detailVerse(ActionEvent event) throws IOException {
        String selectedVerse = versesPlace.getSelectionModel().getSelectedItem();

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
