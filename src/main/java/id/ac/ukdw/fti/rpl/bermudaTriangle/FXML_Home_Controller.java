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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    // atribute non scene Builder
    private GoogleMap map;
    private String nameObject="Bethlehem";
    private String typeObject="place";

    // untuk ke detail
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapID.addMapInitializedListener(this);
    }

    @Override
    public void mapInitialized() {
        description.setWrapText(true);
        places = Database.instance.getAllPlace();
        peoples = Database.instance.getAllPeople();

        if (typeObject.equals("place")) {
            // Database, getValue from Bethlehem
            double latitude=0;
            double longtitude=0;
            String namePlace="No Object";
            String peopleInHere="No Data";
            String descriptionTextDict="No Data";

            for (Places place : places) {
                if (place.getDisplayTitle().equals(nameObject)) {
                    if (place.getLatitude() != null && place.getLongitude()!= null) {
                        latitude=Double.parseDouble(place.getLatitude());
                        longtitude=Double.parseDouble(place.getLongitude());
                    }
                    if (place.getDictText()!= null) {
                        descriptionTextDict=place.getDictText();
                    }
                    namePlace=place.getDisplayTitle();

                    // People in hero (in string type)
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
                    listPeopleHereLookup.addAll(Arrays.asList(listPeopleDiedHere));
                    listPeopleHereLookup.addAll(Arrays.asList(listPeopleHasBeenHere));

                    StringBuilder sb = new StringBuilder();
                    for (String name : listPeopleHereLookup){
                        sb.append(name);
                        if (name.equals("")){
                            break;
                        }
                        sb.append(",");
                    }
                    peopleInHere=sb.toString();
                    if (peopleInHere.equals("")) {
                        peopleInHere="No Data";
                    }

                    // Verse
                    if (place.getVerses() != null) {
                        if (place.getVerses().contains(",")) {
                            String[] listVerseTampung = place.getVerses().split(",");
                            listVerse.getItems().addAll(listVerseTampung);
                        } else {
                            listVerse.getItems().add(place.getVerses());
                        }
                    }

                    break;
                }
            }

            // Map
            LatLong bethlehem = new LatLong(latitude, longtitude);

            MapOptions mapOptions = new MapOptions();
            mapOptions.center(new LatLong(latitude, longtitude))
                    .mapType(MapTypeIdEnum.TERRAIN)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(12);

            map = mapID.createMap(mapOptions);

            //Add markers to the map
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(bethlehem);
            
            Marker bethlehemMarker = new Marker(markerOptions1);
            
            map.addMarker( bethlehemMarker );
            
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<span>"+namePlace+"</span>");

            InfoWindow bethlehemInfoWindow = new InfoWindow(infoWindowOptions);
            bethlehemInfoWindow.open(map, bethlehemMarker);

            // ObjectName
            objectName.setText(namePlace);

            // Description 
            description.setText(descriptionTextDict+"\n\n"+"People in here : "+peopleInHere);

            // Verse --ada pada bagian database
        }else{
            String descriptionTextDict="No Data";
            
           

        }    
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
        description.setStyle("-fx-font-size: " + slidder.getValue());
        listVerse.setStyle("-fx-font-size: " + slidder.getValue());

    }

    @FXML
    void showVerse(ActionEvent event) throws IOException{
        String selectedVerse = listVerse.getSelectionModel().getSelectedItem();

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

    public void showDetail(String nameObjSearch, String typeObjectPara) {
        nameObject = nameObjSearch;
        typeObject = typeObjectPara;
    }
}
