package id.ac.ukdw.fti.rpl.bermudaTriangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import id.ac.ukdw.fti.rpl.bermudaTriangle.database.Database;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXML_Search_Controller implements Initializable {
    // Menyimpan hasil query di database.java
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();

    // Lukas - Untuk menyimpan data yang telah ditampilkan di layar (mencegah
    // redudansi)
    ArrayList<String> peopleList = new ArrayList<>();
    ArrayList<String> placesList = new ArrayList<>();

    // untuk ke detail
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane searchAnchor;

    @FXML
    private TextField searchInput;

    @FXML
    private Button searchButton;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem BtnGoToSearch;
    @FXML
    private MenuItem BtnGoToVerse;

    @FXML
    private ListView<String> tableSearchPeople;

    @FXML
    private ListView<String> tableSearchPlaces;

    @FXML
    private Button detailItem;
    
    @FXML
    private Text backLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lukas - Panggil semua data pada people dan place
        peoples = Database.instance.getAllPeople();
        places = Database.instance.getAllPlace();

        // Lukas - Membaca setiap ObserableList dari Database dan melakukan pengecekan
        // redudansi data
        for (People people : peoples) {
            if (peopleList.contains(people.getDisplayTitle())) {
                continue;
            } else {
                peopleList.add(people.getDisplayTitle());
            }
        }
        for (Places place : places) {
            if (placesList.contains(place.getDisplayTitle())) {
                continue;
            } else {
                placesList.add(place.getDisplayTitle());
            }
        }

        tableSearchPeople.getItems().addAll(peopleList);
        tableSearchPlaces.getItems().addAll(placesList);
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

    // Btn Method
    @FXML
    void search(ActionEvent event) {
        // Bersihkan tampilan list View
        tableSearchPeople.getItems().clear();
        tableSearchPlaces.getItems().clear();

        // Melakukan proses pencarian
        tableSearchPeople.getItems().addAll(searchList(searchInput.getText(), peopleList));
        tableSearchPlaces.getItems().addAll(searchList(searchInput.getText(), placesList));

    }

    @FXML
    private void enterSearh(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            // Bersihkan tampilan list View
            tableSearchPeople.getItems().clear();
            tableSearchPlaces.getItems().clear();

            // Melakukan proses pencarian
            tableSearchPeople.getItems().addAll(searchList(searchInput.getText(), peopleList));
            tableSearchPlaces.getItems().addAll(searchList(searchInput.getText(), placesList));
        }
    }

    @FXML
    void clickItemPeople(MouseEvent event) {
        tableSearchPlaces.getSelectionModel().clearSelection();
    }

    @FXML
    void clickItemPlaces(MouseEvent event) {
        tableSearchPeople.getSelectionModel().clearSelection();
    }

    @FXML
    void detailItem(ActionEvent event) throws IOException {
        String namePeople = tableSearchPeople.getSelectionModel().getSelectedItem();
        String namePlace = tableSearchPlaces.getSelectionModel().getSelectedItem();

        if (namePeople != null || namePlace != null) {
            if (peopleList.contains(namePeople)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPeopleWindow.fxml"));
                root = loader.load();

                FXML_DetailP_Controller detailPeople = loader.getController();
                detailPeople.showName(namePeople);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (placesList.contains(namePlace)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPlaceWindow.fxml"));
                root = loader.load();

                FXML_DetailPLC_Controller detailPlace = loader.getController();
                detailPlace.showDetailPlace(namePlace);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            backLog.setText("Please choose place or people first before go to detail");
        }

    }

    // Method untuk keperluhan
    private List<String> searchList(String kataKunci, List<String> listOfString) {

        // proses pencarian
        List<String> listData = Arrays.asList(kataKunci.trim().split(" "));

        return listOfString.stream().filter(inputan -> {
            return listData.stream().allMatch(word -> inputan.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

}
